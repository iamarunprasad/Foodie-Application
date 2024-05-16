package com.userservice.userservice.model;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import java.util.List;


@Document
public class User {

    @MongoId
    private String emailId;
    @Transient
    private String password;
    private String userName;
    private String role;
    private String location;
    private String phoneNumber;
    List<Restaurant> favoriteRestaurant;
    List<Dishes> favoriteDish;

    public User() {
    }





    public String getEmailId() {
        return emailId;
    }

    public void setEmailID(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Restaurant> getFavoriteRestaurant() {
        return favoriteRestaurant;
    }

    public void setFavoriteRestaurant(List<Restaurant> favoriteRestaurant) {
        this.favoriteRestaurant = favoriteRestaurant;
    }

    public List<Dishes> getFavoriteDish() {
        return favoriteDish;
    }

    public void setFavoriteDish(List<Dishes> favoriteDish) {
        this.favoriteDish = favoriteDish;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "userEmailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", role='" + role + '\'' +
                ", location='" + location + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", restaurantList=" + favoriteRestaurant +
                ", dishesList=" + favoriteDish +

                '}';
    }

    public User(String emailId, String password, String userName, String location, String phoneNumber, List<Restaurant> favoriteRestaurant, List<Dishes> favoriteDish) {
        this.emailId = emailId;
        this.password = password;
        this.userName = userName;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.favoriteRestaurant = favoriteRestaurant;
        this.favoriteDish = favoriteDish;
        this.role="User";
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}