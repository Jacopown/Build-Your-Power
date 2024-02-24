package com.BYP.controller;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.BYP.entity.Battery;
import com.BYP.repository.BatteryRepository;

@Controller
public class BatteryController {

  private final BatteryRepository batteryRepository;

  @Autowired
  public BatteryController(BatteryRepository batteryRepository) {
    this.batteryRepository = batteryRepository;
  }

  /*@GetMapping("/batteries")
  public Iterable<Battery> findAllBatteries() {
    return this.batteryRepository.findAll();
  }

  @PostMapping("/batteries")
  public Battery addOneEmployee(@RequestBody Battery battery) {
    return this.batteryRepository.save(battery);
  }*/
}


