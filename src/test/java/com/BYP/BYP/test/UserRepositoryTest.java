package com.BYP.BYP.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import jakarta.persistence.EntityManager;

import static org.junit.Assert.*;

import com.BYP.DAO.UserRepository;
import com.BYP.DAO.RoleRepository;
import com.BYP.model.User;

@RunWith(SpringRunner.class)
// required to create automatically all the necessary classes
@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	// required in order to create an entityManager object
	@Mock
	private EntityManager entityManager;

	// testing creation of a battery
	@Test
	public void testAddUser() {
		User user = new User("prova@prova.com", "prova", "prova", "prova",
				roleRepository.findByName("ROLE_USER").get());
		userRepository.save(user);
		assertEquals(userRepository.getByID(user.getId()).get().getId(), user.getId());
	}

	@Test
	public void testFindByEmail() {
		String email = "prova@prova.com";
		User user = userRepository.findByEmail(email).get();
		assertEquals(user.getEmail(), email);
	}

	@Test
	public void testExistsByEmail() {
		Boolean exists = userRepository.existsByEmail("prova@prova.com");
		assertEquals(exists, true);
	}

}