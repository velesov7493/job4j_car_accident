package ru.job4j.accident.repositories;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.models.AccidentType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MemAccidentTypeRepository {

	private Map<Integer, AccidentType> records = new HashMap<>();
	private AtomicInteger generator = new AtomicInteger(0);

	public MemAccidentTypeRepository() {
		add(AccidentType.of("Две машины"));
		add(AccidentType.of("Машина и пешеход"));
		add(AccidentType.of("Машина и велосипед"));
	}

	private void add(AccidentType value) {
		value.setId(generator.incrementAndGet());
		records.putIfAbsent(value.getId(), value);
	}

	private void update(int id, AccidentType value) {
		value.setId(id);
		records.put(id, value);
	}

	public Collection<AccidentType> findAll() {
		return records.values();
	}

	public AccidentType findById(int id) {
		return records.get(id);
	}

	public void save(AccidentType value) {
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