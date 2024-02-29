package com.BYP.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "batteries")
public class Battery {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  public enum BatteryStatus{ 
    AVAILABLE,
    UNAVAILABLE,
    DAMAGED,
    UNREACHABLE
  }
  //adding relation between battery and station
  @ManyToOne
  @JoinColumn(name = "station_id")
  private Station station;

  private BatteryStatus status;
  private Float voltage;
  private Float temperature;
  
  // Hibernate expects entities to have a no-arg constructor,
  // though it does not necessarily have to be public.
  private Battery() {}
  
  public Battery(BatteryStatus status, Station station, Float voltage, Float temperature) {
    this.status = status;
    this.station = station;
    this.voltage = voltage;
    this.temperature = temperature;
  }

  public Integer getId() {
    return this.id;
  }
  
  public BatteryStatus getStatus() {
    return this.status;
  }

  public void setStatus(BatteryStatus status) {
    this.status = status;
  } 
  public Station getStation() {
    return this.station;
  }

  public Float getVoltage() {
    return this.voltage;
  }

  public void setVoltage(Float voltage) {
    this.voltage = voltage;
  }

  public Float getTemperature() {
    return this.temperature;
  }

}

