package ru.job4j.accident.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.accident.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
