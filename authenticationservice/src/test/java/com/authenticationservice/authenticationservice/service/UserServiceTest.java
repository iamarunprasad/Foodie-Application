package com.authenticationservice.authenticationservice.service;

import com.authenticationservice.authenticationservice.exception.UserAlreadyExistsException;
import com.authenticationservice.authenticationservice.model.User;
import com.authenticationservice.authenticationservice.repository.IUserRepository;
import com.authenticationservice.authenticationservice.userService.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private IUserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    @BeforeEach
    public void setUp() {
        user = new User("Sam@gmail.com","Sam@123","user");
    }

    @AfterEach
    public void tearDown() {
        user = null;
    }
    @Test
    public void givenUserToSaveReturnSavedUserSuccess() throws UserAlreadyExistsException {
        when(userRepository.findById(anyString())).thenReturn(Optional.ofNullable(null));
        when(userRepository.save(any())).thenReturn(user);
        assertEquals(user,userService.saveUser(user));
        verify(userRepository,times(1)).findById(any());
        verify(userRepository,times(1)).save(any());
    }
    @Test
    public void givenUserToSaveReturnSavedUserFailure() throws UserAlreadyExistsException {
        when(userRepository.findById(any())).thenReturn(Optional.ofNullable(user));
        assertThrows(UserAlreadyExistsException.class,()->userService.saveUser(user));
        verify(userRepository,times(1)).findById(any());
        verify(userRepository,times(0)).save(any());
    }
    @Test
    public void givenUserLoginSuccessReturnRetrievedUser()
    {
        when(userRepository.findByEmailIdAndPassword(any(),any())).thenReturn(user);
        System.out.println(user);
        assertEquals(user,userRepository.findByEmailIdAndPassword(user.getEmailId(),user.getPassword()));
        verify(userRepository,times(1)).findByEmailIdAndPassword(any(),any());
    }
}

