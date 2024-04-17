package com.lcwd.rating.services;

import com.lcwd.rating.entities.Rating;

import java.util.List;
import java.util.Optional;

public interface RatingService {

    Rating create(Rating rating);

    List<Rating> getAll();

    //Optional<Rating> getRatingById(String id);

    List<Rating> getByUserId(String userId);

    List<Rating> getByHotelId(String hotelId);

//    void deleteRating(Optional<Rating> rating);


}
