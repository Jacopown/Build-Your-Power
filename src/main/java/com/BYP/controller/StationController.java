package com.BYP.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;

import com.BYP.DAO.StationRepository;

@Controller
public class StationController {

  private final StationRepository stationRepository;

  public StationController(StationRepository stationRepository) {
    this.stationRepository = stationRepository;
  }

  @GetMapping("/stations")
  public ModelAndView getAllStations() {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("StationTableTemplate");
    modelAndView.addObject("stations", stationRepository.getAll());
    return modelAndView;
  }

  /*
   * @PostMapping("/stations")
   * public Station addOneEmployee(@ModelAttribute Station station) {
   * return stationRepository.save(station);
   * }
   */
}
