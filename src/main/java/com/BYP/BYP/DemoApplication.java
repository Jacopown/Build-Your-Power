package com.BYP.BYP;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.BYP.DAO.BatteryRepository;
import com.BYP.DAO.StationRepository;
import com.BYP.entity.Battery;
import com.BYP.entity.Station;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.BYP")
@EntityScan("com.BYP.entity")
@EnableJpaRepositories("com.BYP.repository")
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  //creating automatic entities
  @Bean
  public CommandLineRunner demo(StationRepository stationRepository, BatteryRepository batteryRepository) {
	  return(args) -> {
		  //creating stations
		  Station station1 = new Station("First Floor");
		  Station station2 = new Station("Second Floor");
		  stationRepository.save(station1);
		  stationRepository.save(station2);

		  //creating batteries
		  Battery battery1 = new Battery(Battery.BatteryStatus.AVAILABLE, station1, 12.0f, 25.0f);
		  Battery battery2 = new Battery(Battery.BatteryStatus.UNAVAILABLE, station1, 12.0f, 25.0f);
		  Battery battery3 = new Battery(Battery.BatteryStatus.DAMAGED, station2, 12.0f, 25.0f);
		  batteryRepository.save(battery1);
		  batteryRepository.save(battery2);
		  batteryRepository.save(battery3);
	  };
  }

}
