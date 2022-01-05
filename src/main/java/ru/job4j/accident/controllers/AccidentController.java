package ru.job4j.accident.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.models.Accident;
import ru.job4j.accident.services.AccidentService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccidentController {

	private final AccidentService service;

	@Autowired
	public AccidentController(AccidentService s) {
		service = s;
	}

	@GetMapping("/")
	public String accidentList(Model model) {
		model.addAttribute("accidents", service.findAllAccidents());
		return "accident/list";
	}

	@GetMapping("/edit/{id}")
	public String accidentEdit(Model model, @PathVariable(name = "id") int accidentId) {
		model.addAttribute("types", service.findAllAccidentTypes());
		model.addAttribute("rules", service.findAllRules());
		model.addAttribute("accident", service.findAccidentById(accidentId));
		return "accident/edit";
	}

	@GetMapping("/create")
	public String accidentCreate(Model model) {
		model.addAttribute("types", service.findAllAccidentTypes());
		model.addAttribute("rules", service.findAllRules());
		return "accident/edit";
	}

	@PostMapping("/save")
	public String accidentSave(@ModelAttribute Accident accident, HttpServletRequest req) {
		service.saveAccident(accident, req.getParameterValues("ruleIds"));
		return "redirect:/";
	}
}
