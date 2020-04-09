package com.darbytelematics.ratingdataservice.resource;

import com.darbytelematics.ratingdataservice.model.Rating;
import com.darbytelematics.ratingdataservice.model.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {
    @RequestMapping("{movieId}")
    public Rating getRatings(@PathVariable("movieId") String movieId){
        return new Rating("My ID",4);
    }

    @RequestMapping("/users/{userId}")
    public UserRating getUserRatings(@PathVariable("userId") String userId){
        List<Rating> ratings = Arrays.asList(
                new Rating("100",4),
                new Rating("500",5)
        );
        UserRating userRating = new UserRating();
        userRating.setUserRating(ratings);
        return userRating;
    }
}
