package com.authenticationservice.authenticationservice.Repository;

import com.authenticationservice.authenticationservice.model.User;
import com.authenticationservice.authenticationservice.repository.IUserRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class UserRepositoryTest {

    @Autowired
    private IUserRepository iUserRepository;

    private User user;

    @BeforeEach
    public void setUp() throws Exception {
        user = new User("arun@gmail.com","arun","Arun");

    }

    @AfterEach
    public void tearDown() throws Exception {
        user = null;

    }

    @Test
    public void testSaveUserSuccess() {

        iUserRepository.save(user);
        User object =  iUserRepository.findById(user.getEmailId()).get();
        assertEquals(user.getEmailId(), object.getEmailId());
    }

    @Test
    public void testLoginUserSuccess() {
        iUserRepository.save(user);
        User object = iUserRepository.findById(user.getEmailId()).get();
        assertEquals(user.getEmailId(), object.getEmailId());
    }
}