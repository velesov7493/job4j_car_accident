package ru.job4j.accident.repositories;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.models.Rule;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MemRuleRepository {

	private Map<Integer, Rule> records = new HashMap<>();
	private AtomicInteger generator = new AtomicInteger(0);

	public MemRuleRepository() {
		add(Rule.of("Статья 1"));
		add(Rule.of("Статья 2"));
		add(Rule.of("Статья 3"));
	}

	private void add(Rule value) {
		value.setId(generator.incrementAndGet());
		records.putIfAbsent(value.getId(), value);
	}

	private void update(int id, Rule value) {
		value.setId(id);
		records.put(id, value);
	}

	public Collection<Rule> findAll() {
		return records.values();
	}

	public Rule findById(int id) {
		return records.get(id);
	}

	public void save(Rule value) {
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
