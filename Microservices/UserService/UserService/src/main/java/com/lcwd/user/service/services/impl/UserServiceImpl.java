package com.lcwd.user.service.services.impl;


import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exception.ResourceNotFoundException;
import com.lcwd.user.service.external.services.HotelService;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService
{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

 //   @Override
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
    @Override
    public List<User> getAllUsers() {
        List<User> usrs = userRepository.findAll();
        for (User user : usrs){
            Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
           // logger.info("{}",ratingOfUser);
            List<Rating> ratings = Arrays.stream(ratingOfUser).toList();
            List<Rating> ratingList = ratings.stream().map(rating -> {
                System.out.println(rating.getHotelId());
                // api caLL to hotel service to get hotel
                ResponseEntity<Hotel> forEntity= restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);
                Hotel hotel = forEntity.getBody();
                logger.info("response status: {}" ,forEntity.getStatusCode());

                //set hotel to rating
                rating.setHotel(hotel);
                return rating;
            }).collect(Collectors.toList());
            user.setRatings(ratingList);
        }
        return usrs;
    }

    @Override
    public User getUserById(String userId) {
        //get user details from user repo
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("Could not find user with id" + userId));
        //for getting ratings detail we need to fetch it from rating service
        //ArrayList<Rating> ratingOfUser = restTemplate.getForObject("http://localhost:8084/ratings/users/"+user.getUserId(), ArrayList.class);
        Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
       // logger.info("{}",ratingOfUser);
        List<Rating> ratings = Arrays.stream(ratingOfUser).toList();
        List<Rating> ratingList = ratings.stream().map(rating -> {
            System.out.println(rating.getHotelId());
           // api caLL to hotel service to get hotel
           // ResponseEntity<Hotel> forEntity= restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);
           // Hotel hotel = forEntity.getBody();
           //logger.info("response status: {}" ,forEntity.getStatusCode());

            Hotel hotel = hotelService.getHotel(rating.getHotelId());


            //set hotel to rating
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);
        return  user;
    }

//    @Override
//    public void deleteUser(String userId) {
//
//    }
//
//    @Override
//    public User updateUser(User user) {
//        return null;
//    }
}


/*
http://localhost:8082/users
http://localhost:8082/users/6717ebfe-50fa-4f19-8a7d-c37098a0df4a
http://localhost:8083/hotels/2f7c9d32-9e31-49bd-9d63-8e367f23d4e3

[
  {
    "userId": "6717ebfe-50fa-4f19-8a7d-c37098a0df4a",
    "name": "Amazing Jaspal",
    "email": "Jaspal@dk.in",
    "about": "I am an owner of Punjabi restaurant",
    "ratings": [
      {
        "ratingId": "66169d69364e8f63e5992c49",
        "userId": "6717ebfe-50fa-4f19-8a7d-c37098a0df4a",
        "hotelId": "2f7c9d32-9e31-49bd-9d63-8e367f23d4e3",
        "rating": 5,
        "feedback": "Exceped swimming pool",
        "hotel": {
          "id": "2f7c9d32-9e31-49bd-9d63-8e367f23d4e3",
          "name": "Taj",
          "about": "Good Food",
          "location": "lko"
        }
      },
      {
        "ratingId": "66169ec0364e8f63e5992c4a",
        "userId": "6717ebfe-50fa-4f19-8a7d-c37098a0df4a",
        "hotelId": "fa65718e-77aa-41d4-ae43-ff5918763b5c",
        "rating": 9,
        "feedback": "Nice garden",
        "hotel": {
          "id": "fa65718e-77aa-41d4-ae43-ff5918763b5c",
          "name": "Moti Mahal",
          "about": "Good garden",
          "location": "udi"
        }
      }
    ]
  },
  {
    "userId": "fa45e038-7d72-4712-8cd7-1fb2037c0473",
    "name": "Sakshi",
    "email": "sakshi@dk.in",
    "about": "I am an owner of club",
    "ratings": [
      {
        "ratingId": "660ec858687b4d6e1ba97602",
        "userId": "fa45e038-7d72-4712-8cd7-1fb2037c0473",
        "hotelId": "2f7c9d32-9e31-49bd-9d63-8e367f23d4e3",
        "rating": 8,
        "feedback": "good hotel",
        "hotel": {
          "id": "2f7c9d32-9e31-49bd-9d63-8e367f23d4e3",
          "name": "Taj",
          "about": "Good Food",
          "location": "lko"
        }
      }
    ]
  }
]

 */