package com.restaurantservice.restaurantservice.model;


import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document
public class Merchant {

    @MongoId
    private String emailId;

    @Transient
    private String password;
    private String role;
    private String merchantName;
    private String location;
    private String phoneNumber;
    private List<Restaurant>  restaurantList;



    public Merchant(String emailId, String password, String merchantName, String location, String phoneNumber, List<Restaurant>  restaurantList) {
        this.emailId = emailId;
        this.password = password;
        this.merchantName = merchantName;
        this.location = location;
        this.phoneNumber = phoneNumber;
   this.restaurantList=restaurantList;

        this.role="Admin";
    }

    public Merchant() {
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Merchant{" +
                "emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", location='" + location + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", restaurantList=" + restaurantList +

                '}';
    }

    public List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }


}

