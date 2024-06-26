package com.BYP.controller;

import com.BYP.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;

import com.BYP.DAO.UserRepository;

@Controller
public class HomePageController {

  @Autowired // for automatic dependency injection (reduces the need for getters and setters)
  public UserRepository userRepository;

	@Value("${spring.application.name}")
	String appName;

	@GetMapping("")
	public String showHomepage(Model model) {
		return "homepage";
	}

  @GetMapping("/userHomepage")
  public String showUserHomepage(Model model) {
      return "user_homepage";
  }
}
