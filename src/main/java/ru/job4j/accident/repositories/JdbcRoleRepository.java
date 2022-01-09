package ru.job4j.accident.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.models.Role;

import java.util.Collection;

@Repository
public class JdbcRoleRepository {

	private final JdbcTemplate jdbc;

	private final RowMapper<Role> rowMapper = (resultSet, row) -> {
		Role role = new Role();
		role.setId(resultSet.getInt("id"));
		role.setName(resultSet.getString("name"));
		return role;
	};

	public JdbcRoleRepository(JdbcTemplate template) {
		jdbc = template;
	}

	public Collection<Role> findAll() {
		String query = "SELECT * FROM tz_roles";
		return jdbc.query(query, rowMapper);
	}

	public Role findById(int id) {
		String query = "SELECT * FROM tz_roles WHERE id=?";
		return jdbc.queryForObject(query, rowMapper, id);
	}
}
