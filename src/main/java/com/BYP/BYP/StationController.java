package com.BYP.BYP;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class StationController {

  private final StationRepository stationRepository;

  public StationController(StationRepository stationRepository) {
    this.stationRepository = stationRepository;
  }

  @GetMapping("/stations")
  public Iterable<Station> findAllStations() {
    return this.stationRepository.findAll();
  }

  @PostMapping("/stations")
  public Station addOneEmployee(@RequestBody Station station) {
    return this.stationRepository.save(station);
  }
}


