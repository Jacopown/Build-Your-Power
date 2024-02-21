package com.BYP.BYP;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class BatteryController {

  private final BatteryRepository batteryRepository;

  public BatteryController(BatteryRepository batteryRepository) {
    this.batteryRepository = batteryRepository;
  }

  @GetMapping("/batteries")
  public Iterable<Battery> findAllBatteries() {
    return this.batteryRepository.findAll();
  }

  @PostMapping("/batteries")
  public Battery addOneEmployee(@RequestBody Battery battery) {
    return this.batteryRepository.save(battery);
  }
}


