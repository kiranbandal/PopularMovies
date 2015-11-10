package com.example;

/**
 * To hold the movie data. retrieved from movie db api.
 * ArrayList of this class instances wil be
 * passed to the custom adapter
 */
public class MovieData {

    public MovieData(String moviePosterPathInput, String movieIdInAPIInput) {
        this.moviePosterPath = moviePosterPathInput;
        this.movieIdInAPI = movieIdInAPIInput;
    }

    String moviePosterPath;
    String movieIdInAPI;

    public String getMoviePosterPath() {
        return moviePosterPath;
    }


    public String getMovieIDInAPI() {
        return movieIdInAPI;
    }


}
