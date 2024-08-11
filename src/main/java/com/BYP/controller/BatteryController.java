package com.BYP.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;

import com.BYP.DAO.BatteryRepository;

@Controller
public class BatteryController {

  private final BatteryRepository batteryRepository;

  public BatteryController(BatteryRepository batteryRepository) {
    this.batteryRepository = batteryRepository;
  }

  @GetMapping("/batteries")
  public ModelAndView getAllBatteries() {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("BatteryTableTemplate");
    modelAndView.addObject("batteries", this.batteryRepository.getAll());
    return modelAndView;
  }

  /*
   * @PostMapping("/batteries")
   * public Battery addOneEmployee(@RequestBody Battery battery) {
   * return this.batteryRepository.save(battery);
   * }
   */
}
