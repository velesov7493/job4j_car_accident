package ru.job4j.accident.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.models.AccidentState;

import java.util.Collection;

@Repository
public class JdbcStateRepository {

	private final JdbcTemplate jdbc;

	private final RowMapper<AccidentState> rowMapper = (resultSet, row) -> {
		AccidentState state = new AccidentState();
		state.setId(resultSet.getInt("id"));
		state.setName(resultSet.getString("name"));
		return state;
	};

	public JdbcStateRepository(JdbcTemplate template) {
		jdbc = template;
	}

	public Collection<AccidentState> findAll() {
		String query = "SELECT * FROM tz_accident_stats";
		return jdbc.query(query, rowMapper);
	}

	public AccidentState findById(int id) {
		String query = "SELECT * FROM tz_accident_stats WHERE id=?";
		return jdbc.queryForObject(query, rowMapper, id);
	}
}
