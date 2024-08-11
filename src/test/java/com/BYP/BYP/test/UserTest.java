package com.BYP.BYP.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

import com.BYP.DAO.RoleRepository;
import com.BYP.model.User;

@RunWith(SpringRunner.class)
// required to create automatically all the necessary classes
@SpringBootTest
public class UserTest {

    @Autowired
    private RoleRepository roleRepository;

    private String email = "prova@prova.com";
    private String password = "prova";
    private String firstName = "prova";
    private String lastName = "prova";
    private String newEmail = "prova2@prova.com";

    @Test
    public void testSetEmail() {
        User user = new User(this.email, this.password, this.firstName, this.lastName,
                roleRepository.findByName("ROLE_USER").get());
        user.setEmail(this.newEmail);
        assertEquals(user.getEmail(), this.newEmail);
    }

}