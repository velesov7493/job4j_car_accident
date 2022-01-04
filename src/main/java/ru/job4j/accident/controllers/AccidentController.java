package ru.job4j.accident.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.models.Accident;
import ru.job4j.accident.services.AccidentService;

@Controller
public class AccidentController {

	private final AccidentService service;

	public AccidentController(AccidentService s) {
		service = s;
	}

	@GetMapping("/")
	public String accidentList(Model model) {
		model.addAttribute("accidents", service.findAll());
		return "accident/list";
	}

	@GetMapping("/edit/{id}")
	public String accidentEdit(Model model, @PathVariable(name = "id") int accidentId) {
		model.addAttribute("accident", service.findAccidentById(accidentId));
		return "accident/edit";
	}

	@GetMapping("/create")
	public String accidentCreate(Model model) {
		return "accident/edit";
	}

	@PostMapping("/save")
	public String accidentSave(@ModelAttribute Accident accident) {
		if (accident.getId() == 0) {
			service.addAccident(accident);
		} else {
			service.updateAccident(accident.getId(), accident);
		}
		return "redirect:/";
	}
}
