package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class MovieRepository {
    HashMap<String,Movie> movieMap = new HashMap<>();
    HashMap<String,Director> directorMap = new HashMap<>();
    HashMap<String, List<Movie> > pairMovieDirectorMap = new HashMap<>();

    public String addMovieInDb(Movie movie){
        String key = movie.name;
        movieMap.put(key,movie);
        return "Successfully Added";
    }
    public String addDirectorInDb(Director director){
        String key = director.name;
        directorMap.put(key,director);
        return "Successfully Added";
    }
    public String getDirectorByMovieName(String movieName){
        if(movieMap.containsKey(movieName)){



            for (Map.Entry<String, List<Movie>> entry : pairMovieDirectorMap.entrySet()) {
                String director = entry.getKey();
                List<Movie> movieList = entry.getValue();
                for (Movie movie : movieList) {
                    String getMovie = movie.getName();
                    if (getMovie.equals(movieName)) {
                        return director;

                    }
                }

            }
        }
        return null;
    }
    public String addPairOfMovieAndDirectorInD(String movieName,String directorName){
        if(movieMap.containsKey(movieName) && directorMap.containsKey(directorName)){
            List<Movie> movieList = new ArrayList<>();
            if(pairMovieDirectorMap.containsKey(directorName)){

                 movieList = pairMovieDirectorMap.get(directorName);
            }
            movieList.add(movieMap.get(movieName));
            pairMovieDirectorMap.put(directorName,movieList);
        }
        return "Successfully Added";
    }
    public Movie getMovieFromDb(String movieName){
        if(movieMap.containsKey(movieName)){
            return movieMap.get(movieName);
        }
        return null;
    }
    public Director getDirectorFromDb(String directorName){
        if(directorMap.containsKey(directorName)){
            return directorMap.get(directorName);
        }
        return null;
    }
    public List<String> getListOfMovieByDirectorName(String directorName){

        if(pairMovieDirectorMap.containsKey(directorName)) {
            List<String> movieName = new ArrayList<>();
           List <Movie> movieList = pairMovieDirectorMap.get(directorName);
            for(int i = 0; i < movieList.size(); i++){
                 Movie movie =movieList.get(i);
                 movieName.add(movie.name);
            }
            return  movieName;
        }
        return null;
    }
    public List<String> listOfMoviesFromDb(){
        List<String> movieList = new ArrayList<>();
        for(Movie EntrySet :  movieMap.values()){
            movieList.add(EntrySet.name);
        }
        return  movieList;
    }
    public String deleteDirectorByNameInDb(String directorName){
        if(pairMovieDirectorMap.containsKey(directorName)){
            List<Movie> movieList = pairMovieDirectorMap.get(directorName);
              for(int i = 0; i < movieList.size(); i++){
                    movieMap.remove( movieList.get(i).name);
              }
                directorMap.remove(directorName);
                pairMovieDirectorMap.remove(directorName);

        }
        return "deleted the record";
    }
    public String deleteAllDirectorAndItsMovie(){
           for(HashMap.Entry map : pairMovieDirectorMap.entrySet()){
              List<Movie> movieList = pairMovieDirectorMap.get(map.getKey());
               for(int i = 0; i < movieList.size(); i++){
                   movieMap.remove( movieList.get(i).name);
               }
           }
           pairMovieDirectorMap.clear();
           directorMap.clear();

        return "deleted all the records";
    }
}
