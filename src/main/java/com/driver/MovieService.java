package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository; // create object automatically
    public String addMovie(Movie movie){
        String result = movieRepository.addMovieInDb(movie);
        return result;
    }
    public String addDirector(Director director){
        String result = movieRepository.addDirectorInDb(director);
        return result;
    }
    public String addPairOfMovieAndDirector(String movieName, String directorName){
        String result = movieRepository.addPairOfMovieAndDirectorInD(movieName,directorName);
        return result;
    }
    public Movie getMovieByName(String movieName){
        return movieRepository.getMovieFromDb(movieName);
    }
    public Director getDirectorByName(String directorName){
        return movieRepository.getDirectorFromDb(directorName);
    }
    public List<String> getMoviesByDirectorName(String directorName){
        return movieRepository.getListOfMovieByDirectorName(directorName);
    }
    public String getDirectorByMovieName(String movieName){
        return movieRepository.getDirectorByMovieName(movieName);
    }
    public List<String> findAllMovies(){
        return movieRepository.listOfMoviesFromDb();
    }
    public String deleteDirectorByName(String directorName){
        return movieRepository.deleteDirectorByNameInDb(directorName);
    }
    public String deleteAllDirector(){
        return movieRepository.deleteAllDirectorAndItsMovie();
    }
}
