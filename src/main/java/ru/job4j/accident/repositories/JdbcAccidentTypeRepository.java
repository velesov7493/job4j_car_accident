package ru.job4j.accident.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.models.AccidentType;

import java.util.Collection;

@Repository
public class JdbcAccidentTypeRepository {

	private final JdbcTemplate jdbc;

	private final RowMapper<AccidentType> rowMapper = (resultSet, row) -> {
		AccidentType type = new AccidentType();
		type.setId(resultSet.getInt("id"));
		type.setName(resultSet.getString("name"));
		return type;
	};

	public JdbcAccidentTypeRepository(JdbcTemplate template) {
		jdbc = template;
	}

	public Collection<AccidentType> findAll() {
		String query = "SELECT * FROM tz_accident_types";
		return jdbc.query(query, rowMapper);
	}

	public AccidentType findById(int id) {
		String query = "SELECT * FROM tz_accident_types WHERE id=?";
		return jdbc.queryForObject(query, rowMapper, id);
	}
}
