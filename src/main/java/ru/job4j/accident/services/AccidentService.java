package ru.job4j.accident.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.models.Accident;
import ru.job4j.accident.models.AccidentType;
import ru.job4j.accident.models.Rule;
import ru.job4j.accident.repositories.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AccidentService {

	private final AccidentRepository accidents;
	private final AccidentTypeRepository types;
	private final RuleRepository rules;
	private final AccidentStateRepository stats;
	private final UserRepository users;

	@Autowired
	public AccidentService(
		AccidentRepository accidentsRepo,
		AccidentStateRepository statsRepo,
		AccidentTypeRepository typesRepo,
		RuleRepository rulesRepo,
		UserRepository usersRepo
	) {
		accidents = accidentsRepo;
		stats = statsRepo;
		types = typesRepo;
		rules = rulesRepo;
		users = usersRepo;
	}

	private void fillAccidentRulesByIds(Accident value, String[] ruleIds) {
		Set<Rule> selectedRules = new HashSet<>();
		for (String sRuleId : ruleIds) {
			Optional<Rule> optRule = rules.findById(Integer.parseInt(sRuleId));
			optRule.ifPresent(selectedRules::add);
		}
		value.setRules(selectedRules);
	}

	public Collection<Accident> findAllAccidents() {
		return accidents.findAll();
	}

	public Accident findAccidentById(int id) {
		return accidents.findById(id).orElse(null);
	}

	//TODO убрать установку автора по умолчанию при создании подсистемы авторизации
	public void saveAccident(Accident value, String[] ruleIds, int typeId) {
		if (value.getAuthor() == null) {
			value.setAuthor(users.getOne(1));
		}
		if (value.getState() == null) {
			value.setState(stats.getOne(1));
		}
		if (typeId != 0) {
			value.setType(types.getOne(typeId));
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
		return types.findById(id).orElse(null);
	}

	public Collection<Rule> findAllRules() {
		return rules.findAll();
	}

	public Rule findRuleById(int id) {
		return rules.findById(id).orElse(null);
	}
}