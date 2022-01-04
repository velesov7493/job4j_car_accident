package ru.job4j.accident.repositories;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.models.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MemAccidentRepository {

	private Map<Integer, Accident> records = new HashMap<>();
	private AtomicInteger generator = new AtomicInteger(0);

	public MemAccidentRepository() {
		add(Accident.of(
			"ДТП-1",
			"Столкновение Lexus MC 300 (гос. № м963мм/37) "
			+ "и ВАЗ-2107 (гос. № м963мм/37)",
			"г. Иваново, перекресток ул. Лежневской и ул. 10 Августа"
		));
		add(Accident.of(
			"ДТП-2",
			"Столкновение Ford Focus (гос. № т710хя/37) "
			+ "и Ferrari 360 Spider (гос. № м667ку/37)",
			"г. Иваново, перекресток ул. Лежневской и пр. Строителей"
		));
		add(Accident.of(
			"ДТП-3",
			"Ford Focus (гос. № т710хя/37) сбит пешеход на переходе",
			"г. Иваново, перекресток ул. Ташкентской и пр. Строителей"
		));
	}

	private void add(Accident value) {
		value.setId(generator.incrementAndGet());
		records.putIfAbsent(value.getId(), value);
	}

	private void update(int id, Accident value) {
		value.setId(id);
		records.put(id, value);
	}

	public Collection<Accident> findAll() {
		return records.values();
	}

	public Accident findById(int id) {
		return records.get(id);
	}

	public void save(Accident value) {
		if (value.getId() == 0) {
			add(value);
		} else {
			update(value.getId(), value);
		}
	}

	public void deleteById(int id) {
		records.remove(id);
	}
}