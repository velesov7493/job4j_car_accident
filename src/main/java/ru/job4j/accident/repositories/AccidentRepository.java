package ru.job4j.accident.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.models.Accident;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccidentRepository extends JpaRepository<Accident, Integer> {

    @Override
    @Query(
            "SELECT DISTINCT a FROM Accident a "
                    + "LEFT JOIN FETCH a.type "
                    + "JOIN FETCH a.author "
                    + "LEFT JOIN FETCH a.inspector "
                    + "JOIN FETCH a.state "
                    + "LEFT JOIN FETCH a.rules "
                    + "LEFT JOIN FETCH a.attachments "
                    + "WHERE a.id = ?1"
    )
    Optional<Accident> findById(Integer id);

    @Override
    @Query(
            "FROM Accident a "
                    + "JOIN FETCH a.state "
                    + "LEFT JOIN FETCH a.type "
                    + "ORDER BY a.created DESC, a.state.id"
    )
    List<Accident> findAll();
}