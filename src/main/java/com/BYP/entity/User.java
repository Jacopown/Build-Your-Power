package com.BYP.entity;
import jakarta.persistence.*;


@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 45)
  private String email;

  @Column(nullable = false, unique = true, length = 20)
  private String username;

  @Column(nullable = false, length = 64)
  private String password;
  
  @Column(nullable = false, length = 20)
  private String firstName;

  @Column(nullable = false, length = 20)
  private String lastName;

  private Boolean isSuperUser;
  
  
  // Hibernate expects entities to have a no-arg constructor,
  // though it does not necessarily have to be public.
  private User() {}
  
  public User(String username, String email, String lastName, Boolean isSuperUser) {
    this.username = username;
    this.email = email;
    this.isSuperUser = isSuperUser;
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

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return this.username;
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

  public String lastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  public Boolean getIsSuperUser() {
    return this.isSuperUser;
  }

  public void setIsSuperUser(Boolean isSuperUser) {
    this.isSuperUser = isSuperUser;
  }

}

