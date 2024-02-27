package com.BYP.entity;
import jakarta.persistence.*;


@Entity
@Table(name = "stations")
public class Station {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String location;
  
  
  // Hibernate expects entities to have a no-arg constructor,
  // though it does not necessarily have to be public.
  private Station() {}
  
  public Station(String location){
    this.location = location;
  }

  public Integer getId() {
    return this.id;
  }
  
  public String getLocation() {
    return this.location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

}

