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

	// testing battery delete
	// FIXME
	/*
	@Test
	public void testDeleteBattery(){
		Battery battery = batteryRepository.getByID(1).get();
		Integer batteryId = battery.getId();
		batteryRepository.merge(battery);
		batteryRepository.delete(battery);
		Optional<Battery> deletedBattery = batteryRepository.getByID(batteryId);
		assertFalse(deletedBattery.isPresent());
	}
	*/

}
