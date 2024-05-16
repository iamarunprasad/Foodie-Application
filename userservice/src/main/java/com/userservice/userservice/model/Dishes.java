package com.userservice.userservice.model;

import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Objects;

public class Dishes {
    @MongoId
    private String dishID;
    private String name;
    private String category;
    private float Price;
    private int rating;
    public Dishes() {
    }

    public Dishes(String dishID, String name, String category, float price, int rating) {
        this.dishID = dishID;
        this.name = name;
        this.category = category;
        Price = price;
        this.rating = rating;
    }

    public String getDishID() {
        return dishID;
    }

    public void setDishID(String dishID) {
        this.dishID = dishID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "dishes{" +
                "dishID='" + dishID + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", Price=" + Price +
                ", rating=" + rating +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dishes dishes = (Dishes) o;
        return Objects.equals(dishID, dishes.dishID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dishID);
    }
}

