package ru.job4j.accident.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

	@GetMapping("/")
	public String index(Model model) {
		List<String> items = List.of(
			"Роли пользователей: обычный пользователь (водитель), автоинспектор",
			"Статусы заявок о нарушениях: принята, отклонена, завершена",
			"Полномочия пользователя: "
			+ "просмотр и редактирование (ограниченное) "
			+ "заявок о нарушениях от своего имени, "
			+ "добавление заявок",
			"Полномочия инспектора: просмотр всех принятых заявок, "
			+ "отклонить или завершить любую принятую заявку"
		);
		model.addAttribute("itemList", items);
		return "index";
	}
}
