package com.restaurantservice.restaurantservice.proxy;

import com.restaurantservice.restaurantservice.model.Merchant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name="user-authentication-service",url="http://localhost:8081")
    public interface UserProxy {

        @PostMapping("/api/v1/saveUser")
        public ResponseEntity<?> saveUser(@RequestBody Merchant merchant);

    }

