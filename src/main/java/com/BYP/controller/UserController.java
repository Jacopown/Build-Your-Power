package com.BYP.controller;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import java.util.NoSuchElementException;

import com.BYP.entity.User;
import com.BYP.repository.UserRepository;

@Controller
public class UserController {

  private final UserRepository userRepository;

  @Autowired // for automatic dependency injection (reduces the need for getters and setters)
  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /*@GetMapping("/users")
  public Iterable<User> findAllUsers() {
    return this.userRepository.findAll();
  }*/

  // creating a new user using a form
  @RequestMapping(value = "/addUser", method = RequestMethod.POST) // note: value represents the URL path
  public String addUser(@ModelAttribute User user) {
    
	//save the user into the database
	userRepository.save(user);

	//return the page where it says "User added successfully"
    	return "userAdded"; // this is only a temporary solution
  }

  @RequestMapping("/addUserForm")
  public String addUserForm() {
    return "addUser";
  }

  // getting user info (querying by id)
  @GetMapping("/users/{id}")
  public ModelAndView getById(@PathVariable Integer id) {
    try {
      // getting user by id
      User user = userRepository.findById(id).get();
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
