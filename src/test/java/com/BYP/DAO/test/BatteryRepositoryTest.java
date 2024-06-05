package com.BYP.BYP.test;

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
import static org.junit.Assert.assertEquals;

import com.BYP.DAO.BatteryRepository;
import com.BYP.model.Battery;
import com.BYP.DAO.StationRepository;

@RunWith(SpringRunner.class)
// required to create automatically all the necessary classes
@SpringBootTest
public class BatteryRepositoryTest{
	
	@Autowired
	private BatteryRepository batteryRepository;
	@Autowired
	private StationRepository stationRepository;

	// required in order to create an entityManager object
	@Mock
	private EntityManager entityManager;

	// testing creation of a battery
	@Test
	public void testAddBattery(){
		Battery battery = new Battery();
		batteryRepository.save(battery);
		assertEquals(batteryRepository.getByID(battery.getId()).get().getId().longValue(), battery.getId().longValue());
	}

	// testing battery's status update
	@Test
	public void testBatteryUpdateStatus(){
		Battery battery = batteryRepository.getByID(1).get();
		batteryRepository.update(battery, new String[]{"DAMAGED"});
		String expectedStatus = "DAMAGED";
		assertEquals(battery.getStatus().name(), expectedStatus);
	}

	// testing battery's voltage update
	@Test
	public void testBatteryUpdateVoltage(){
		Battery battery = batteryRepository.getByID(1).get();
		battery.setVoltage(5.0f);
		float delta = 0.01f;
		assertEquals((float) battery.getVoltage(), 5.0f, delta);//TODO add label for numbers
	}

	// testing battery's temperature update
	@Test
	public void testBatteryUpdateTemperature(){
		Battery battery = batteryRepository.getByID(1).get();
		battery.setTemperature(70.0f);
		float delta = 0.01f;
		assertEquals((float) battery.getTemperature(), 70.0f, delta);//TODO also here
	}

	// testing battery's assignment update
	/*@Test
	public void testBatteryUpdateAssign(){
		Battery battery = batteryRepository.getByID(1).get();
		battery.updateAssign(stationRepository.getByID(1).get());
		assertEquals(battery.getAssignedStation().getLocation(), stationRepository.getByID(1).getLocation());
	}*/
}
