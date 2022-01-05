package ru.job4j.accident.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.models.Accident;
import ru.job4j.accident.repositories.MemAccidentRepository;
import ru.job4j.accident.repositories.MemAccidentTypeRepository;

import java.util.Collection;

@Service
public class AccidentService {

	private final MemAccidentRepository repository;

	@Autowired
	public AccidentService(MemAccidentRepository repo, MemAccidentTypeRepository typeRepo) {
		repository = repo;
		repository.save(Accident.of(
			"ДТП-1",
			"Столкновение Lexus MC 300 (гос. № м963мм/37) "
				+ "и ВАЗ-2107 (гос. № м963мм/37)",
			"г. Иваново, перекресток ул. Лежневской и ул. 10 Августа",
			typeRepo.findById(1)
		));
		repository.save(Accident.of(
			"ДТП-2",
			"Столкновение Ford Focus (гос. № т710хя/37) "
				+ "и Ferrari 360 Spider (гос. № м667ку/37)",
			"г. Иваново, перекресток ул. Лежневской и пр. Строителей",
			typeRepo.findById(1)
		));
		repository.save(Accident.of(
			"ДТП-3",
			"Ford Focus (гос. № т710хя/37) сбит пешеход на переходе",
			"г. Иваново, перекресток ул. Ташкентской и пр. Строителей",
			typeRepo.findById(2)
		));
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