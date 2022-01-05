package ru.job4j.accident.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.models.Accident;
import ru.job4j.accident.models.AccidentType;
import ru.job4j.accident.services.AccidentService;
import ru.job4j.accident.services.AccidentTypeService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AccidentController {

	private final AccidentService service;
	private final AccidentTypeService typeService;

	public AccidentController(AccidentService s, AccidentTypeService ts) {
		service = s;
		typeService = ts;
	}

	@GetMapping("/")
	public String accidentList(Model model) {
		model.addAttribute("accidents", service.findAll());
		return "accident/list";
	}

	@GetMapping("/edit/{id}")
	public String accidentEdit(Model model, @PathVariable(name = "id") int accidentId) {
		model.addAttribute("types", typeService.findAll());
		model.addAttribute("accident", service.findById(accidentId));
		return "accident/edit";
	}

	@GetMapping("/create")
	public String accidentCreate(Model model) {
		model.addAttribute("types", typeService.findAll());
		return "accident/edit";
	}

	@PostMapping("/save")
	public String accidentSave(@ModelAttribute Accident accident) {
		service.save(accident);
		return "redirect:/";
	}
}
