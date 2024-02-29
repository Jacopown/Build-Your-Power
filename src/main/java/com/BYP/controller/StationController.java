package com.BYP.controller;

import jakarta.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import com.BYP.entity.Station;
import com.BYP.DAO.StationRepository;

@Controller
public class StationController {

  private final StationRepository stationRepository;

  @Autowired
  public StationController(StationRepository stationRepository) {
    this.stationRepository = stationRepository;
  }

  @GetMapping("/stations")
  public Iterable<Station> getAllStations() {
    return stationRepository.getAll();
  }

  /*@PostMapping("/stations")
  public Station addOneEmployee(@ModelAttribute Station station) {
    return stationRepository.save(station);
  }*/
}


