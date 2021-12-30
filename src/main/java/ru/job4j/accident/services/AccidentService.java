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

	public void addAccident(Accident value) {
		repository.addAccident(value);
	}

	public Collection<Accident> findAll() {
		return repository.findAll();
	}

	public Accident findAccidentById(int id) {
		return repository.findAccidentById(id);
	}

	public void updateAccident(int id, Accident value) {
		repository.updateAccident(id, value);
	}

	public void deleteAccidentById(int id) {
		repository.deleteAccidentById(id);
	}
}
