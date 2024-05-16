package com.bej.apigatewaysingleport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApigatewaysingleportApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApigatewaysingleportApplication.class, args);
	}
	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p.path("/api/v1/**").uri("http://localhost:8081/"))
				.route(p -> p.path("/api/v2/**","/api/v2/restaurant/**").uri("http://localhost:8082/"))
				.route(p -> p.path("/api/v3/**","/api/v3/user/**").uri("http://localhost:8083/"))
				.build();
	}


}
