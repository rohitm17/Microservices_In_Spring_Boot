package com.lcwd.user.service;

import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingService ratingService;

	@Test
	void createRating() {
		Rating rating = Rating.builder().userId("fa45e038-7d72-4712-8cd7-1fb2037c0473").hotelId("fa65718e-77aa-41d4-ae43-ff5918763b5c")
				.rating(5).feedback("greatttttttttt").build();
		ResponseEntity<Rating> rating1 = ratingService.createRating(rating);
		System.out.println(rating1+" created rating successfully");
	}
}
