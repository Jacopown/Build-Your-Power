package com.BYP.BYP.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

import com.BYP.DAO.RoleRepository;
import com.BYP.DAO.UserRepository;

@RunWith(SpringRunner.class)
// required to create automatically all the necessary classes
@SpringBootTest
public class DefaultInitializerTest {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAdminRole() {
        Boolean adminRoleExists = roleRepository.existsByName("ROLE_ADMIN");
        assertEquals(adminRoleExists, true);
    }

    @Test
    public void testUserRole() {
        Boolean userRoleExists = roleRepository.existsByName("ROLE_ADMIN");
        assertEquals(userRoleExists, true);
    }

    @Test
    public void testDefaultAdmin() {
        Boolean defaultAdminExists = userRepository.existsByEmail("admin@admin.com");
        assertEquals(defaultAdminExists, true);
    }

}
