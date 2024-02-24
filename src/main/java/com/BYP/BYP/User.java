package com.BYP.BYP;
import jakarta.persistence.*;


@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String username;
  private String email;
  private Boolean isSuperUser;
  
  
  // Hibernate expects entities to have a no-arg constructor,
  // though it does not necessarily have to be public.
  private User() {}
  
  public User(String username, String email, Boolean isSuperUser) {
    this.username = username;
    this.email = email;
    this.isSuperUser = isSuperUser;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
  
  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Boolean getIsSuperUser() {
    return this.isSuperUser;
  }

  public void setIsSuperUser(Boolean isSuperUser) {
    this.isSuperUser = isSuperUser;
  }

}

