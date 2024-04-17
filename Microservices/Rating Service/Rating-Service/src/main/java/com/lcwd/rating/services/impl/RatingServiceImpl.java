package com.lcwd.rating.services.impl;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.repositories.RatingRepository;
import com.lcwd.rating.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating create(Rating rating) {
        return ratingRepository.save(rating);
    }

//    @Override
//    public Optional<Rating> getRatingById(String id) {
//        try {
//            return ratingRepository.findById(id);
//                    //.orElseThrow(() -> new Exception("Hotel with given id not found."));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Override
    public List<Rating> getAll() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }

//    @Override
//    public  void  deleteRating(Optional<Rating> rating){  ratingRepository.delete(rating);}
}
