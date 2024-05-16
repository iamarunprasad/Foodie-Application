package com.authenticationservice.authenticationservice.controller;
import com.authenticationservice.authenticationservice.exception.InvalidCredentialsException;
import com.authenticationservice.authenticationservice.exception.UserAlreadyExistsException;
import com.authenticationservice.authenticationservice.model.User;
import com.authenticationservice.authenticationservice.security.SecurityTokenGenerator;
import com.authenticationservice.authenticationservice.userService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final IUserService iuserService;
    private final SecurityTokenGenerator securityTokenGenerator;


@Autowired
    public UserController(IUserService iuserService, SecurityTokenGenerator securityTokenGenerator) {
        this.iuserService = iuserService;
    this.securityTokenGenerator = securityTokenGenerator;
}
    @PostMapping("/saveUser")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        try {
            User registeruser = iuserService.saveUser(user);
            return new ResponseEntity<>(registeruser, HttpStatus.CREATED);
        }catch(UserAlreadyExistsException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            User registeruser = iuserService.getUserByUserIdAndPassword(user.getEmailId(), user.getPassword());
            String role = registeruser.getRole();
            Map<String, String> map =securityTokenGenerator.generateToken(registeruser);
//            map.put("token", securityTokenGenerator.generateToken(registeruser));
            map.put("role", role);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (InvalidCredentialsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }


}
