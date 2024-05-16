package com.userservice.userservice.model;

import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Objects;

public class Restaurant {

    @Id
    private String restId;
    private String name;
    private  String location;
    private List<Dishes> dishes;


    public Restaurant() {
    }

    public Restaurant(String restId, String name, String location, List<Dishes> dishes) {
        this.restId = restId;
        this.name = name;
        this.location = location;
        this.dishes = dishes;
    }

    public String getRestId() {
        return restId;
    }

    public void setRestId(String restId) {
        this.restId = restId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Dishes> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dishes> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restId='" + restId + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", dishes=" + dishes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant restaurant = (Restaurant) o;
        return Objects.equals(restId, restaurant.restId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(restId);
    }
}
