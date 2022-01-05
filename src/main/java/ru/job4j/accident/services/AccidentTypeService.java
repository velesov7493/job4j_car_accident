package ru.job4j.accident.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.models.AccidentType;
import ru.job4j.accident.repositories.MemAccidentTypeRepository;

import java.util.Collection;

@Service
public class AccidentTypeService {

	private final MemAccidentTypeRepository repository;

	@Autowired
	public AccidentTypeService(MemAccidentTypeRepository repo) {
		repository = repo;
	}

	public Collection<AccidentType> findAll() {
		return repository.findAll();
	}

	public AccidentType findById(int id) {
		return repository.findById(id);
	}

	public void save(AccidentType value) {
		repository.save(value);
	}

	public void deleteById(int id) {
		repository.deleteById(id);
	}
}
