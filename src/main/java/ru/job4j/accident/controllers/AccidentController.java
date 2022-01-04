package ru.job4j.accident.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
		model.addAttribute("accident", service.findById(accidentId));
		return "accident/edit";
	}

	@GetMapping("/update")
	public String accidentUpdate(@RequestParam("id") int id, Model model) {
		model.addAttribute("accident", service.findById(id));
		return "accident/edit";
	}

	@GetMapping("/create")
	public String accidentCreate(Model model) {
		return "accident/edit";
	}

	@PostMapping("/save")
	public String accidentSave(@ModelAttribute Accident accident) {
		service.save(accident);
		return "redirect:/";
	}
}
