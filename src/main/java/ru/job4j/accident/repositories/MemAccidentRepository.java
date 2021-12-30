package ru.job4j.accident.repositories;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.models.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class MemAccidentRepository {

	private Map<Integer, Accident> records;
	private int generator;

	public MemAccidentRepository() {
		records = new HashMap<>();
		generator = 1;
		records.put(generator++, Accident.of(
			"ДТП-1",
			"Столкновение Lexus MC 300 (гос. № м963мм/37) "
			+ "и ВАЗ-2107 (гос. № м963мм/37)",
			"г. Иваново, перекресток ул. Лежневской и ул. 10 Августа"
		));
		records.put(generator++, Accident.of(
			"ДТП-2",
			"Столкновение Ford Focus (гос. № т710хя/37) "
			+ "и Ferrari 360 Spider (гос. № м667ку/37)",
			"г. Иваново, перекресток ул. Лежневской и пр. Строителей"
		));
		records.put(generator++, Accident.of(
			"ДТП-3",
			"Ford Focus (гос. № т710хя/37) сбит пешеход на переходе",
			"г. Иваново, перекресток ул. Ташкентской и пр. Строителей"
		));
	}

	public void addAccident(Accident value) {
		value.setId(generator++);
		records.putIfAbsent(value.getId(), value);
	}

	public Collection<Accident> findAll() {
		return records.values();
	}

	public Accident findAccidentById(int id) {
		return records.get(id);
	}

	public void updateAccident(int id, Accident value) {
		value.setId(id);
		records.put(id, value);
	}

	public void deleteAccidentById(int id) {
		records.remove(id);
	}
}