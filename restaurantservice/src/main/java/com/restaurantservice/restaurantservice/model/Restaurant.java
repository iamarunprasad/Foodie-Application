package com.restaurantservice.restaurantservice.model;

import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Objects;


public class Restaurant {
    @Id
    private String restId;
    private String name;
    private  String location;
    private List<Dishes> dishList;


    public Restaurant() {
    }

    public Restaurant(String restId, String name, String location, List<Dishes> dishList) {
        this.restId = restId;
        this.name = name;
        this.location = location;
        this.dishList = dishList;
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

    public List<Dishes> getDishList() {
        return dishList;
    }

    public void setDishList(List<Dishes> dishList) {
        this.dishList = dishList;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restId='" + restId + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", dishes=" + dishList +
                '}';
    }


}
