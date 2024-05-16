package com.authenticationservice.authenticationservice.repository;

import com.authenticationservice.authenticationservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface IUserRepository extends JpaRepository<User,String> {
    public User findByEmailIdAndPassword(String emailId, String password);
}
