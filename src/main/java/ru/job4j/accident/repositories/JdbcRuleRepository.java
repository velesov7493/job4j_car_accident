package ru.job4j.accident.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.models.Rule;

import java.util.Collection;

@Repository
public class JdbcRuleRepository {

	private final JdbcTemplate jdbc;

	private final RowMapper<Rule> rowMapper = (resultSet, row) -> {
		Rule rule = new Rule();
		rule.setId(resultSet.getInt("id"));
		rule.setName(resultSet.getString("name"));
		return rule;
	};

	public JdbcRuleRepository(JdbcTemplate template) {
		jdbc = template;
	}

	public Collection<Rule> findAll() {
		String query = "SELECT * FROM tz_rules";
		return jdbc.query(query, rowMapper);
	}

	public Rule findById(int id) {
		String query = "SELECT * FROM tz_rules WHERE id=?";
		return jdbc.queryForObject(query, rowMapper, id);
	}

	public Collection<Rule> findAllByAccidentId(int accidentId) {
		String query =
			"SELECT r.id, r.name FROM tz_rules AS r "
			+ "INNER JOIN tr_accidents_rules AS ar"
			+ "  ON ar.id_rule=r.id AND ar.id_accident=?";
		return jdbc.query(query, rowMapper, accidentId);
	}
}
