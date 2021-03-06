package com.kirangisp.fragmenthelpermodule;

import java.util.ArrayList;


/**
 * Holds the global objects fro holding ArrayList of Movie Posters and instance of Movie Detail Info
 * to be used across the application. Also, KEY string to save these global objects into Bundle
 * are defined here.
 */
public class CommonFunctions {

    //region ArrayList to be saved in Bundle in OnSavedInstance method on MoviePosters frgament
    private static ArrayList<HelperModuleMovieData> moviePosterInfoArrayLst;

    public static ArrayList<HelperModuleMovieData> getMoviePosterInfoArrayLst() {
        return moviePosterInfoArrayLst;
    }

    public static void setMoviePosterInfoArrayLst(ArrayList<HelperModuleMovieData> moviePosterInfoArrayLst) {
        CommonFunctions.moviePosterInfoArrayLst = moviePosterInfoArrayLst;
    }
    //endregion

    //region MovieDetails instace to be saved in bundle in OnSaveInstance on Movie Details fragment
    private static HelperModuleMovieDetails movieDetailsInfo;

    public static HelperModuleMovieDetails getMovieDetailsInfo() {
        return movieDetailsInfo;
    }

    public static void setMovieDetailsInfo(HelperModuleMovieDetails movieDetailsInfo) {
        CommonFunctions.movieDetailsInfo = movieDetailsInfo;
    }
    //endregion

    //region KEY used to save the movie posters data into bundle
    private static final String MOVIE_POSTERS_DATA_KEY = "Movie Posters Data";

    public static String getMoviePostersDataKey() {
        return MOVIE_POSTERS_DATA_KEY;
    }
    //endregion

    //region KEY used to save the movie details data into bundle
    private static final String MOVIE_DETAILS_DATA_KEY = "Movie Details Data";

    public static String getMovieDetailsKey() {
        return MOVIE_DETAILS_DATA_KEY;
    }
    //endregion


}
