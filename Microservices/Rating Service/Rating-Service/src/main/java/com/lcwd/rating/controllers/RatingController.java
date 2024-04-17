package com.lcwd.rating.controllers;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private  RatingService ratingService;

    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody Rating rating) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getAll(){
        return ResponseEntity.ok(ratingService.getAll());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId){
        List<Rating> r = ratingService.getByUserId(userId);
        System.out.println("r: " + r);
        return ResponseEntity.ok((List<Rating>) ratingService.getByUserId(userId));
             //   ResponseEntity.status(HttpStatus.OK).body(ratingService.getByUserId(userId));
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<Rating> getRatingByHotelId(@PathVariable String hotelId) {
        return ResponseEntity.ok((Rating) ratingService.getByHotelId(hotelId));
    }

//    @DeleteMapping("/delete/{ratingId}")
//    public  void deleteRating(@PathVariable String deleteId){
//        Optional<Rating> r = ratingService.getRatingById(deleteId);
//        ratingService.deleteRating(r);
//    }


}
