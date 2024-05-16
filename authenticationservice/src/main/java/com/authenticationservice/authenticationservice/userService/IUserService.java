package com.authenticationservice.authenticationservice.userService;

import com.authenticationservice.authenticationservice.exception.InvalidCredentialsException;
import com.authenticationservice.authenticationservice.exception.UserAlreadyExistsException;
import com.authenticationservice.authenticationservice.model.User;

public interface IUserService {
    User saveUser(User user)throws UserAlreadyExistsException;
    User getUserByUserIdAndPassword(String emailId, String password) throws InvalidCredentialsException;

}
