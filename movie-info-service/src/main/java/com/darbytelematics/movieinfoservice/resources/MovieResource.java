package com.darbytelematics.movieinfoservice.resources;

import com.darbytelematics.movieinfoservice.model.Movie;
import com.darbytelematics.movieinfoservice.model.MovieSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MovieResource {
    @Value("${api.key}")
    private String apikey;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId){
        MovieSummary movieSummary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/"+ movieId +"?api_key=8c1bdc27dd05d0996a87ad0b64f5049f",MovieSummary.class);
        System.out.println(movieSummary);
        Movie x = new Movie(movieId,movieSummary.getTitle(),movieSummary.getOverview());
        System.out.println(x);
        return x;
    }
}

