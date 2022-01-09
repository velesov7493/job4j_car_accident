package ru.job4j.accident.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.models.User;

import java.util.Collection;

@Repository
public class JdbcUserRepository {

	private final JdbcTemplate jdbc;
	private JdbcRoleRepository roles;

	private final RowMapper<User> rowMapper = (resultSet, row) -> {
		User user = new User();
		user.setId(resultSet.getInt("id"));
		user.setName(resultSet.getString("name"));
		user.setEmail(resultSet.getString("email"));
		user.setPassword(resultSet.getString("pass"));
		user.setPhone(resultSet.getString("phone"));
		if (roles != null) {
			user.setRole(roles.findById(resultSet.getInt("id_role")));
		}
		return user;
	};

	public JdbcUserRepository(JdbcTemplate template, JdbcRoleRepository rolesRepo) {
		jdbc = template;
		roles = rolesRepo;
	}

	public Collection<User> findAll() {
		String query = "SELECT * FROM tz_users";
		return jdbc.query(query, rowMapper);
	}

	public User findById(int id) {
		String query = "SELECT * FROM tz_users WHERE id=?";
		return jdbc.queryForObject(query, rowMapper, id);
	}

	public User findByEmailAndPassword(String email, String password) {
		String query = "SELECT * FROM tz_users WHERE email=? AND pass=?";
		return jdbc.queryForObject(query, rowMapper, email, password);
	}
}
