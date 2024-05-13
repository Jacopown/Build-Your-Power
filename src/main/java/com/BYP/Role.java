package com.BYP;
 
import jakarta.persistence.*;
 
@Entity
@Table(name = "roles")
public class Role {

  @Id
  @Column(name = "role_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
   
  @Column(nullable = false, unique = true)
  private String name;
  
  public Role() {}

  public Role(String name) {
    this.name = name;
  }
  public Long getId() {
      return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
