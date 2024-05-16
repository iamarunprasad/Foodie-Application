package com.userservice.userservice.controller;

import com.userservice.userservice.exception.FavoriteException;
import com.userservice.userservice.exception.UserAlreadyExistsException;


import com.userservice.userservice.model.User;
import com.userservice.userservice.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    IUserService iUserService;

    @Mock
    HttpServletRequest request;

    @InjectMocks
    UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    void saveCustomer_Success() throws UserAlreadyExistsException {
//
//        User user = new User("userId", "username", "password");
//        when(iUserService.addUser(user)).thenReturn(user);
//
//
//        ResponseEntity<?> response = userController.saveCustomer(user);
//
//
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertEquals(user, response.getBody());
//    }
//
//    @Test
//    void saveCustomer_Failure_UserAlreadyExistsException() throws UserAlreadyExistsException {
//
//        User user = new User("userId", "username", "password");
//        when(iUserService.addUser(user)).thenThrow(new UserAlreadyExistsException("User already exists"));
//
//
//        ResponseEntity<?> response = userController.saveCustomer(user);
//
//
//        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
//        assertEquals("User already exists", response.getBody());
//    }

    @Test
    void deleteFavoriteRestaurant_Success() throws FavoriteException {

        when(request.getAttribute("userId")).thenReturn("userId");


        ResponseEntity<String> response = userController.deleteFavoriteRestaurant(request, "restaurantName");


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Rest deleted successfully", response.getBody());
    }


    @Test
    void deleteFavoriteDish_Success() throws FavoriteException {

        when(request.getAttribute("userId")).thenReturn("userId");


        ResponseEntity<String> response = userController.deleteFavoriteDish(request, "dishName");


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Dish deleted successfully", response.getBody());
    }

}