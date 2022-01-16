package ru.job4j.accident.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.models.Rule;

@Repository
public interface RuleRepository extends JpaRepository<Rule, Integer> {
}
