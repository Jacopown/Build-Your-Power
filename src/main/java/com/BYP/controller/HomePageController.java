package com.BYP.BYP;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;

@Controller
public class HomePageController {

	@Value("${spring.application.name}")
	String appName;

	@GetMapping("/")
	public String homePage(Model model) {
		model.addAttribute("appName", appName);
		return "home";
	}
}
