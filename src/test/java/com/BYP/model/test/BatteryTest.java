package com.BYP.BYP.test;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import jakarta.persistence.EntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;



import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

import com.BYP.DAO.BatteryRepository;
import com.BYP.model.Battery;
import com.BYP.DAO.StationRepository;

@RunWith(SpringRunner.class)
// required to create automatically all the necessary classes
@SpringBootTest
public class BatteryTest{
	
	@Autowired
	private BatteryRepository batteryRepository;
	@Autowired
	private StationRepository stationRepository;

	//TODO must be added in BatteryTest.java
	// testing battery's voltage update
	@Test
	public void testBatteryUpdateVoltage(){
		Battery battery = batteryRepository.getByID(1).get();
		battery.setVoltage(5.0f);
		float delta = 0.01f;
		float expectedVoltage = 5.0f;
		assertEquals((float) battery.getVoltage(), expectedVoltage, delta);
	}

	// testing battery's temperature update
	@Test
	public void testBatteryUpdateTemperature(){
		Battery battery = batteryRepository.getByID(1).get();
		battery.setTemperature(70.0f);
		float delta = 0.01f;
		float expectedTemperature = 70.0f;
		assertEquals((float) battery.getTemperature(), expectedTemperature, delta);
	}

	// testing battery's assignment update
	/*@Test
	public void testBatteryUpdateAssign(){
		Battery battery = batteryRepository.getByID(1).get();
		battery.updateAssign(stationRepository.getByID(1).get());
		assertEquals(battery.getAssignedStation().getLocation(), stationRepository.getByID(1).getLocation());
	}*/

}
