package com.BYP.BYP.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import org.mockito.Mock;
import jakarta.persistence.EntityManager;

import com.BYP.DAO.daoInterface;
import com.BYP.model.User;
import com.BYP.model.Battery;
import com.BYP.Role;
import com.BYP.model.Station;
import com.BYP.DAO.RoleRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTest{

	@Autowired
	private daoInterface<User> userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private daoInterface<Battery> batteryRepository;

	@Autowired
	private daoInterface<Station> stationRepository;

	@Mock
	private EntityManager entityManager;

	@Test
	public void testCreateUserAndUC1() {

		// simulating new account creation
		User user = new User("name.surname@mail.com", "name", "surname", "password", roleRepository.findByName("ROLE_USER").get());
		userRepository.save(user);
		assertEquals(userRepository.getByID(user.getId()).get().getId(), user.getId());

		// simulating battery pickup
		Battery battery = batteryRepository.getByID(1).get();
		battery.updateAssign(user);
		assertEquals(battery.getAssignedUser().getId(), user.getId());

		// simulating battery dropoff
		battery.updateAssign(stationRepository.getByID(1).get());
		assertEquals(battery.getAssignedStation().getId(), stationRepository.getByID(1).get().getId());
	}
}
