package ru.job4j.accident.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.job4j.accident.models.User;
import ru.job4j.accident.repositories.RoleRepository;
import ru.job4j.accident.repositories.UserRepository;

@Service
public class AuthService {

    private static final Logger LOG = LoggerFactory.getLogger(AuthService.class);

    private final RoleRepository roles;
    private final UserRepository users;

    public AuthService(RoleRepository rolesRepo, UserRepository usersRepo) {
        roles = rolesRepo;
        users = usersRepo;
    }

    public boolean saveUser(User value) {
        boolean result = false;
        try {
            value.setRole(roles.getOne(1));
            result = users.save(value) != null;
        } catch (Throwable ex) {
            LOG.error("Ошибка сохранения пользователя: ", ex);
        }
        return result;
    }
}
