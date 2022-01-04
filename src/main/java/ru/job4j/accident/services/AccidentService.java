package ru.job4j.accident.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.models.Accident;
import ru.job4j.accident.repositories.MemAccidentRepository;

import java.util.Collection;

@Service
public class AccidentService {

	private final MemAccidentRepository repository;

	@Autowired
	public AccidentService(MemAccidentRepository repo) {
		repository = repo;
	}

	public Collection<Accident> findAll() {
		return repository.findAll();
	}

	public Accident findById(int id) {
		return repository.findById(id);
	}

	public void save(Accident value) {
		repository.save(value);
	}

	public void deleteById(int id) {
		repository.deleteById(id);
	}
}
