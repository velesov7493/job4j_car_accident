package ru.job4j.accident.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.models.Accident;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public class JdbcAccidentRepository {

	private final JdbcTemplate jdbc;
	private JdbcUserRepository users;
	private JdbcStateRepository stats;
	private JdbcRuleRepository rules;
	private JdbcAccidentTypeRepository types;
	private JdbcMediaRepository media;

	private final RowMapper<Accident> accidentRowMapper = (resultSet, rowNum) -> {
		Accident a = new Accident();
		a.setId(resultSet.getInt("id"));
		a.setName(resultSet.getString("name"));
		a.setText(resultSet.getString("description"));
		a.setAddress(resultSet.getString("address"));
		a.setSolution(resultSet.getString("solution"));
		a.setActor1Number(resultSet.getString("actor1number"));
		a.setActor2Number(resultSet.getString("actor2number"));
		a.setCreated(new Date(resultSet.getTimestamp("created").getTime()));
		if (users != null) {
			a.setAuthor(users.findById(resultSet.getInt("id_author")));
			int inspectorId = resultSet.getInt("id_inspector");
			if (inspectorId != 0) {
				a.setInspector(users.findById(inspectorId));
			}
		}
		if (stats != null) {
			a.setState(stats.findById(resultSet.getInt("id_state")));
		}
		if (rules != null) {
			a.setRules(rules.findAllByAccidentId(a.getId()));
		}
		if (types != null) {
			a.setType(types.findById(resultSet.getInt("id_type")));
		}
		if (media != null) {
			a.setAttachments(media.findAllByAccidentId(a.getId()));
		}
		return a;
	};

	@Autowired
	public JdbcAccidentRepository(
		JdbcTemplate template, JdbcUserRepository usersRepo,
		JdbcAccidentTypeRepository typesRepo, JdbcStateRepository statsRepo,
		JdbcRuleRepository rulesRepo, JdbcMediaRepository mediaRepo
	) {
		jdbc = template;
		users = usersRepo;
		stats = statsRepo;
		rules = rulesRepo;
		types = typesRepo;
		media = mediaRepo;
	}

	private void add(Accident value) {
		String query =
			"INSERT INTO tj_accidents "
			+ "(id_type, id_author, name, actor1number, "
			+ "actor2number, address, description) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		jdbc.update(
			query, value.getType().getId(), value.getAuthor().getId(),
			value.getName(), value.getActor1Number(), value.getActor2Number(),
			value.getAddress(), value.getText()
		);
	}

	private void update(int id, Accident value) {
		value.setId(id);
		String query =
			"UPDATE tj_accidents SET "
			+ "id_type=?, id_state=?, id_author=?, "
			+ "id_inspector=?, name=?, actor1number=?, "
			+ "actor2number=?, address=?, description=?, solution=? "
			+ "WHERE id=?";
		jdbc.update(
			query, value.getType() != null ? value.getType().getId() : null,
			value.getState().getId(), value.getAuthor().getId(),
			value.getInspector() != null ? value.getInspector().getId() : null,
			value.getName(), value.getActor1Number(), value.getActor2Number(),
			value.getAddress(), value.getText(), value.getSolution()
		);
	}

	public Collection<Accident> findAll() {
		String query = "SELECT * FROM tj_accidents";
		List<Accident> result = jdbc.query(query, accidentRowMapper);
		return result;
	}

	public Accident findById(int id) {
		String query = "SELECT * FROM tj_accidents WHERE id=?";
		return jdbc.queryForObject(query, accidentRowMapper, id);
	}

	public void save(Accident value) {
		if (value.getId() == 0) {
			add(value);
		} else {
			update(value.getId(), value);
		}
	}

	public void deleteById(int id) {
		String query = "DELETE FROM tj_accidents WHERE id=?";
		jdbc.update(query, id);
	}
}
