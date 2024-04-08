package com.BYP.controller;

import com.BYP.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.BYP.DAO.UserRepository;

@Controller
public class HomePageController {

  @Autowired // for automatic dependency injection (reduces the need for getters and setters)
  public UserRepository userRepository;

	@Value("${spring.application.name}")
	String appName;

	@GetMapping("")
	public String homePage(Model model) {
		return "home";
	}

  @GetMapping("/register")
  public String showRegistrationForm(Model model) {
      model.addAttribute("user", new User());
      return "signup_form";
  }
  @PostMapping("/process_register")
  public String processRegister(User user) {
      BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
      String encodedPassword = passwordEncoder.encode(user.getPassword());
      user.setPassword(encodedPassword);
       
      userRepository.save(user);
       
      return "registration_success";
  }
  @PostMapping("/user_view")
  public String showUserView(Model model){
    return "user_view";
  } 
	// @GetMapping("/register")
	// public String showSignUpForm(Model model) {
	// 	model.addAttribute("user", new User());
	// 	return "signup_form";
	// }
	//
 //  @PostMapping("/signup")
 //  public String submitSignUpForm(User user) {
 //    userRepository.save(user);
 //    return "registration_success";
 //  }
}
