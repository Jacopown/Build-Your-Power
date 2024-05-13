package com.BYP.model;

import java.util.*;
import jakarta.persistence.*;
import com.BYP.Role;


@Entity
@Table(name = "users")
public class User {

  @Id
  @Column(name = "user_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 45)
  private String email;

  @Column(nullable = false, length = 64)
  private String password;
  
  @Column(nullable = false, length = 20)
  private String firstName;

  @Column(nullable = false, length = 20)
  private String lastName;

  // @ManyToOne(cascade = CascadeType.PERSIST)  
  @ManyToOne()  
  @JoinColumn(name = "role_id")
  private Role role; 
  
  // Hibernate expects entities to have a no-arg constructor,
  // though it does not necessarily have to be public.
  public User() {}
  
  public User(String email, String password, String firstName, String lastName, Role role) {
    this.email = email;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.role = role;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }
  
  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public Role getRole() {
    return this.role;
  }
}

