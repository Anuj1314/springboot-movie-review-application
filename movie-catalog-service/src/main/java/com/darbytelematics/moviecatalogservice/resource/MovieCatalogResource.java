package com.darbytelematics.moviecatalogservice.resource;

import com.darbytelematics.moviecatalogservice.model.CatalogItem;
import com.darbytelematics.moviecatalogservice.model.Movie;
import com.darbytelematics.moviecatalogservice.model.Rating;
import com.darbytelematics.moviecatalogservice.model.UserRating;
import com.darbytelematics.moviecatalogservice.service.MovieInfo;
import com.darbytelematics.moviecatalogservice.service.UserRatingInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    WebClient.Builder webClientBuilder;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UserRatingInfo userRatingInfo;

    @Autowired
    MovieInfo movieInfo;

    @Autowired
    private DiscoveryClient discoveryClient;
//    for getting config about particular service, advanced load balancing, can get certain instances based on certain conditions


//    @HystrixCommand(fallbackMethod = "getCatalogFallback")
    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
        UserRating userRating = userRatingInfo.getUserRating(userId);

        return userRating.getUserRating().stream()
                .map(rating -> movieInfo.getCatalogItem(rating))
                .collect(Collectors.toList());
        }













//
//    public List<CatalogItem> getCatalogFallback(@PathVariable("userId") String userId) {
//        return Arrays.asList(new CatalogItem("","",0));
//    }
}

//        get all rated movie IDs
//        for each movie id, call the movie-info service and get details
//        put them all together

 /*    Movie movie = webClientBuilder.build()
                    .get()
                    .uri("\"http://localhost:8081/movies/\"+rating.getMovieId()")
                    .retrieve()
//                    .bodyToMono(Movie.class)
//                    .block();*/
//        WebClient is asynchronous, so we used .block() to make it synchronous. The other method could be like we can make whole method asynch, but as of now it is sync here.
