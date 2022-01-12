package ru.job4j.accident.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.models.Accident;
import ru.job4j.accident.models.AccidentType;
import ru.job4j.accident.models.Rule;
import ru.job4j.accident.repositories.*;

import java.util.Collection;

@Service
public class AccidentService {

	private final HbmAccidentRepository accidents;
	private final HbmAccidentTypeRepository types;
	private final HbmRuleRepository rules;

	@Autowired
	public AccidentService(
		HbmAccidentRepository accidentsRepo,
		HbmAccidentTypeRepository typesRepo,
		HbmRuleRepository rulesRepo
	) {
		accidents = accidentsRepo;
		types = typesRepo;
		rules = rulesRepo;
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

	public Collection<Rule> findAllRules() {
		return rules.findAll();
	}

	public Rule findRuleById(int id) {
		return rules.findById(id);
	}
}