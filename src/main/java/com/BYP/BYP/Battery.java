package com.BYP.BYP;

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
  private BatteryStatus status;
  @OneToOne
  private Station station;
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
  
  public Station getStation() {
    return this.station;
  }

  public Float getVoltage() {
    return this.voltage;
  }

  public Float getTemperature() {
    return this.temperature;
  }

}

