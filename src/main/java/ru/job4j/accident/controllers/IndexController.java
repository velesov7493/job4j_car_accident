package ru.job4j.accident.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.services.AccidentService;

@Controller
public class IndexController {

	private final AccidentService service;

	public IndexController(AccidentService s) {
		service = s;
	}

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("accidents", service.findAll());
		return "index";
	}
}
