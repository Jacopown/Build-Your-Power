package com.BYP.controller;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import java.util.NoSuchElementException;
import org.springframework.ui.Model;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.BYP.model.User;
import com.BYP.Role;
import com.BYP.DAO.UserRepository;
import com.BYP.DAO.RoleRepository;

import java.security.Principal;

@Controller
public class UserController {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  @Autowired // for automatic dependency injection (reduces the need for getters and setters)
  public UserController(UserRepository userRepository, RoleRepository roleRepository) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
  }

  @GetMapping("/signup")
  public String addUserForm(Model model) {
      model.addAttribute("user", new User());
      return "signup_form";
  }

  @PostMapping("/process_register")
  public String processRegister(@ModelAttribute User user) {
      BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
      String encodedPassword = passwordEncoder.encode(user.getPassword());
      Role userRole = roleRepository.findByName("ROLE_USER").get();
      user.setPassword(encodedPassword);
      user.setRole(userRole);
      userRepository.save(user);
      return "registration_success";
  }
  
  @GetMapping("/userView")
  public String userView(Model model, Principal principal) {
    String email = principal.getName();
    User user = userRepository.findByEmail(email).get();
    model.addAttribute("user", user);
    return "userView";
  }

  @GetMapping("/login")
  public String login() {
    return "login_form";
  }

  // getting user info (querying by id)
  @GetMapping("/users/{id}")
  public ModelAndView getById(@PathVariable Integer id) {
    try {
      // getting user by id
      User user = userRepository.getByID(id).get();
      //generating the view for the data
      ModelAndView mav = new ModelAndView();
      mav.setViewName("userView");
      mav.addObject("user", user);
      return mav;
    } catch (NoSuchElementException e) {
      //throws an error if the user is not found
      ModelAndView mav = new ModelAndView();
      mav.setViewName("genericError");
      mav.addObject("error", "User not found");
      return mav;
    }
  }
}
