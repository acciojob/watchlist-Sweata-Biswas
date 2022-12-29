package com.driver;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;


@RestController
public class MovieController {
    @Autowired
    MovieService movieService;


    @PostMapping("/movies/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody() Movie movie){

        String response = movieService.addMovie(movie);
        return  new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PostMapping("/movies/add-director")
    public ResponseEntity<String> addDirector(@RequestBody() Director director){

        String response = movieService.addDirector(director);
        return  new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movie") String movieName,@RequestParam("director") String directorName){

        String response = movieService.addPairOfMovieAndDirector(movieName,directorName);
        return  new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String movieName ){
        Movie response= movieService.getMovieByName(movieName);
        if(response == null) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping("/movies/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String directorName ){
        Director response= movieService.getDirectorByName(directorName);
        if(response == null) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/movies/get-movies-by-director-name/{name}")
    public ResponseEntity<List<Movie>> getMoviesByDirectorName(@PathVariable("name") String directorName){
            List<Movie> response = movieService.getMoviesByDirectorName(directorName);
        if(response == null){
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/movies/get-all-movies")
    public ResponseEntity<List<Movie>> findAllMovies(){
        return new ResponseEntity<>(movieService.findAllMovies(),HttpStatus.OK);
    }
    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("name") String directorName){
        return new ResponseEntity<>(movieService.deleteDirectorByName(directorName),HttpStatus.OK);
    }
    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        return new ResponseEntity<>(movieService.deleteAllDirector(),HttpStatus.OK);
    }
}
