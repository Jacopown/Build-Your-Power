package com.BYP.model;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;


@Entity
@Table(name = "stations")
public class Station {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String location;
  
  //adding relation between battery and station
  @OneToMany(mappedBy = "station", cascade = CascadeType.ALL)// cascande annotation ensures that operations like save, update, delete perfomed on Station
                                                             // will be cascaded to the related batteries
  private List<Battery> batteries = new ArrayList<>();
  
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

