package ru.job4j.accident.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.models.Accident;
import ru.job4j.accident.models.AccidentType;
import ru.job4j.accident.models.Rule;
import ru.job4j.accident.repositories.MemAccidentRepository;
import ru.job4j.accident.repositories.MemAccidentTypeRepository;
import ru.job4j.accident.repositories.MemRuleRepository;

import java.util.Collection;

@Service
public class AccidentService {

	private final MemAccidentRepository accidents;
	private final MemAccidentTypeRepository types;
	private final MemRuleRepository rules;

	@Autowired
	public AccidentService(
		MemAccidentRepository accidentsRepo,
		MemAccidentTypeRepository typesRepo,
		MemRuleRepository rulesRepo
	) {
		accidents = accidentsRepo;
		types = typesRepo;
		rules = rulesRepo;

		Accident accident = Accident.of(
			"ДТП-1",
			"Столкновение Lexus MC 300 (гос. № м963мм/37) "
				+ "и ВАЗ-2107 (гос. № м963мм/37)",
			"г. Иваново, перекресток ул. Лежневской и ул. 10 Августа"
		);
		accident.setType(types.findById(1));
		accident.addRule(rules.findById(1));
		accidents.save(accident);

		accident = Accident.of(
			"ДТП-2",
			"Столкновение Ford Focus (гос. № т710хя/37) "
				+ "и Ferrari 360 Spider (гос. № м667ку/37)",
			"г. Иваново, перекресток ул. Лежневской и пр. Строителей"
		);
		accident.setType(types.findById(1));
		accident.addRule(rules.findById(2));
		accidents.save(accident);

		accident = Accident.of(
			"ДТП-3",
			"Ford Focus (гос. № т710хя/37) сбит пешеход на переходе",
			"г. Иваново, перекресток ул. Ташкентской и пр. Строителей"
		);
		accident.setType(types.findById(2));
		accident.addRule(rules.findById(1));
		accident.addRule(rules.findById(3));
		accidents.save(accident);
	}

	private void fillAccidentRulesByIds(Accident value, String[] ruleIds) {
		for (String sRuleId : ruleIds) {
			value.addRule(rules.findById(Integer.parseInt(sRuleId)));
		}
	}

	public Collection<Accident> findAllAccidents() {
		return accidents.findAll();
	}

	public Accident findAccidentById(int id) {
		return accidents.findById(id);
	}

	public void saveAccident(Accident value, String[] ruleIds) {
		int typeId = value.getType().getId();
		if (typeId != 0) {
			value.setType(types.findById(typeId));
		}
		if (ruleIds != null && ruleIds.length > 0) {
			fillAccidentRulesByIds(value, ruleIds);
		}
		accidents.save(value);
	}

	public void deleteAccidentById(int id) {
		accidents.deleteById(id);
	}

	public Collection<AccidentType> findAllAccidentTypes() {
		return types.findAll();
	}

	public AccidentType findAccidentTypeById(int id) {
		return types.findById(id);
	}

	public void saveAccidentType(AccidentType value) {
		types.save(value);
	}

	public void deleteAccidentTypeById(int id) {
		types.deleteById(id);
	}

	public Collection<Rule> findAllRules() {
		return rules.findAll();
	}

	public Rule findRuleById(int id) {
		return rules.findById(id);
	}

	public void saveRule(Rule value) {
		rules.save(value);
	}

	public void deleteRuleById(int id) {
		rules.deleteById(id);
	}
}