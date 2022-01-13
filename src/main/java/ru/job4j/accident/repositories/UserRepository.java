package ru.job4j.accident.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.accident.models.User;

public interface UserRepository extends JpaRepository<User, Integer> { }