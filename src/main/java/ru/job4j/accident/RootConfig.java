package ru.job4j.accident;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
	"ru.job4j.accident.repositories",
	"ru.job4j.accident.services",
	"ru.job4j.accident.controllers",
	"ru.job4j.accident.config"
})
public class RootConfig { }
