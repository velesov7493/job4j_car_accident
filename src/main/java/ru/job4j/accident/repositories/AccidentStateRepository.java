package ru.job4j.accident.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.accident.models.AccidentState;

public interface AccidentStateRepository extends JpaRepository<AccidentState, Integer> { }