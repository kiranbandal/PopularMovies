package com.example;

/**
 * Created by User on 04-Nov-15.
 */
public class MovieDetails extends MovieData {

    String title;
    String releaseYear;
    String vote;
    String synopsis;

    public MovieDetails(String moviePosterPathInput,
                        String movieIdInAPIInput,
                        String movieTitle,
                        String movieReleaseYear,
                        String movieVote,
                        String movieSynopsis) {
        super(moviePosterPathInput, movieIdInAPIInput);

        this.title = movieTitle;
        this.releaseYear = movieReleaseYear;
        this.vote = movieVote;
        this.synopsis = movieSynopsis;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public String getVote() {
        return vote;
    }

    public String getSynopsis() {
        return synopsis;
    }
}
