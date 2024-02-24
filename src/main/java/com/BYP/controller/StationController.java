package com.BYP.controller;

import jakarta.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import com.BYP.entity.Station;
import com.BYP.repository.StationRepository;

@Controller
public class StationController {

  private final StationRepository stationRepository;

  @Autowired
  public StationController(StationRepository stationRepository) {
    this.stationRepository = stationRepository;
  }

  @GetMapping("/stations")
  public Iterable<Station> findAllStations() {
    return stationRepository.findAll();
  }

  @PostMapping("/stations")
  public Station addOneEmployee(@RequestBody Station station) {
    return stationRepository.save(station);
  }
}


