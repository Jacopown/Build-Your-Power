package com.BYP.BYP;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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

  // getting user info
  /*I@GetMapping("/users/{id}")
  public */
}
