package com.BYP.BYP.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import jakarta.persistence.EntityManager;

import static org.junit.Assert.*;

import com.BYP.DAO.RoleRepository;
import com.BYP.Role;

@RunWith(SpringRunner.class)
// required to create automatically all the necessary classes
@SpringBootTest
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    // required in order to create an entityManager object
    @Mock
    private EntityManager entityManager;

    // testing creation of a battery
    @Test
    public void testAddRole() {
        Role role = new Role("ROLE_PROVA");
        roleRepository.save(role);
        assertEquals(roleRepository.getByID(role.getId()).get().getId(), role.getId());
    }

    @Test
    public void testFindByName() {
        String name = "ROLE_PROVA";
        Role role = roleRepository.findByName(name).get();
        assertEquals(role.getName(), name);
    }

    @Test
    public void testExistsByName() {
        Boolean exists = roleRepository.existsByName("ROLE_PROVA");
        assertEquals(exists, true);
    }

}
