package com.BYP.BYP;

import com.BYP.Role;
import com.BYP.model.User;
import com.BYP.DAO.RoleRepository;
import com.BYP.DAO.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DefaultInitializer implements ApplicationListener<ContextRefreshedEvent> {

  @Autowired
  private RoleRepository roleRepository; 

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    if (roleRepository.existsByName("ROLE_ADMIN") == false ) { // Check if any roles exist
      createRole("ROLE_ADMIN");
    }
    if (roleRepository.existsByName("ROLE_USER") == false ) { // Check if any roles exist
      createRole("ROLE_USER");
    }
    if (userRepository.existsByEmail("admin@admin.com") == false) {
      createAdminUser();
    }
  }

  private void createRole(String name){
    roleRepository.save(new Role("ROLE_USER"));
    roleRepository.save(new Role("ROLE_ADMIN")); // Add other default roles as needed
  }

  private void createAdminUser() {
    Role adminRole = roleRepository.findByName("ROLE_ADMIN").get();
    // Role adminRole = new Role("ROLE_ADMIN");
    User adminUser = new User("admin@admin.com", passwordEncoder.encode("password"), "Admin", "Admin", adminRole);
    userRepository.save(adminUser);
  }
}
