package com.authenticationservice.authenticationservice.userService;

import com.authenticationservice.authenticationservice.exception.InvalidCredentialsException;
import com.authenticationservice.authenticationservice.exception.UserAlreadyExistsException;
import com.authenticationservice.authenticationservice.model.User;
import com.authenticationservice.authenticationservice.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository iuserRepository;

@Autowired
    public UserServiceImpl(IUserRepository iuserRepository) {
        this.iuserRepository = iuserRepository;
    }


    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {
        if(iuserRepository.findById(user.getEmailId()).isPresent()){
            throw new UserAlreadyExistsException("User already exist");
        }else {
            return iuserRepository.save(user);
        }
    }

       @Override
    public User getUserByUserIdAndPassword(String emailId, String password) throws InvalidCredentialsException {
        User user=iuserRepository.findByEmailIdAndPassword(emailId,password);
        if(user==null){
            throw new InvalidCredentialsException("invalid credentials");
        }
        return user;
    }
}


