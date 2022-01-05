package ru.job4j.accident.repositories;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.models.Accident;
import ru.job4j.accident.models.AccidentType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MemAccidentRepository {

	private Map<Integer, Accident> records = new HashMap<>();
	private AtomicInteger generator = new AtomicInteger(0);

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