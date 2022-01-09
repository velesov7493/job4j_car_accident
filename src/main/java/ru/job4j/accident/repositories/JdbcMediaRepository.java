package ru.job4j.accident.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.models.Media;

import java.util.Collection;

@Repository
public class JdbcMediaRepository {

	private final JdbcTemplate jdbc;

	private final RowMapper<Media> rowMapper = (resultSet, row) -> {
		Media media = new Media();
		media.setId(resultSet.getInt("id"));
		media.setMimeType(resultSet.getString("mimeType"));
		media.setIsVideo(resultSet.getShort("is_video"));
		return media;
	};

	public JdbcMediaRepository(JdbcTemplate template) {
		jdbc = template;
	}

	public Collection<Media> findAll() {
		String query = "SELECT * FROM tj_media";
		return jdbc.query(query, rowMapper);
	}

	public Collection<Media> findAllByAccidentId(int accidentId) {
		String query = "SELECT * FROM tj_media WHERE id_accident=?";
		return jdbc.query(query, rowMapper, accidentId);
	}

	public Collection<Media> findAllVideo() {
		String query = "SELECT * FROM tj_media WHERE is_video=1";
		return jdbc.query(query, rowMapper);
	}

	public Media findById(int id) {
		String query = "SELECT * FROM tj_media WHERE id=?";
		return jdbc.queryForObject(query, rowMapper, id);
	}
}
