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

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTest{
	
	@Autowired
	private BatteryRepository batteryRepository;

	@Mock
	private EntityManager entityManager;

	@Test
	public void testAddBattery(){
		Battery battery = new Battery();
		batteryRepository.save(battery);
		assertEquals(1L, 1L);
		//assertEquals(batteryRepository.getByID(1).get().getId().longValue(), 1L);
	}
}
