package com.example;

public class GlobalObjects {

    //region Get all movies url - first part
    public final static String GET_ALL_MOVIES_URL_FIRST_PART = "http://api.themoviedb.org/3/discover/movie?&api_key=";

    public static String getPopularMoviesURL() {
        return GET_ALL_MOVIES_URL_FIRST_PART;
    }
    //endregion

    //region Movies DB API Key
    public final static String MOVIE_API_KEY = "KEY_HERE";

    public static String getMovieApiKey() {
        return MOVIE_API_KEY;
    }
    //endregion

    //region Json Tag name in popular movies json
    public final static String JSON_RESULT_TAG_NAME = "results";

    public static String getJsonResultsTagName() {
        return JSON_RESULT_TAG_NAME;
    }
    //endregion

    //region Set Image this to be queried from the movie db api
    //Movie poster image size, available options are
    // "w92", "w154", "w185", "w342", "w500", "w780", or "original". Note: doulbe slashes at the end are must
    public static String MOVIE_POSTER_IMG_SIZE;

    public static void setMoviePosterImgSize(String moviePosterImgSize) {
        MOVIE_POSTER_IMG_SIZE = moviePosterImgSize;
    }

    public static String getMoviePosterImageSize() {
        return MOVIE_POSTER_IMG_SIZE;
    }
    //endregion

    //region Movie Poster Image View Padding
    public static String IMAGEVIEW_PADDING;

    public static void setImgViewPadding(String paddingValue) {

        IMAGEVIEW_PADDING = paddingValue;
    }

    public static String getImageviewPadding() {

        return IMAGEVIEW_PADDING;
    }
    //endregion

    //region Movie Poster Image View Dimensions
    public static int imgViewWidth;

    public static void setImgViewWidth(int wdth) {

        imgViewWidth = wdth;
    }

    public static int getImageviewWidth() {

        return imgViewWidth;
    }

    public static int imgViewHeight;

    public static void setImgViewHeight(int ht) {

        imgViewHeight = ht;
    }

    public static int getImageViewHeight() {

        return imgViewHeight;
    }
    //endregion

    //region Movie Poster (Image) Resize dimensions
    public static int posterResizeWidth;

    public static void setPosterResizewWidth(int wdth) {

        posterResizeWidth = wdth;
    }

    public static int getPosterResizeWidth() {

        return posterResizeWidth;
    }

    public static int posterResizeHeight;

    public static void setPosterResizewHeight(int hte) {

        posterResizeHeight = hte;
    }

    public static int getPosterResizewHeight() {

        return posterResizeHeight;
    }
    //endregion

    //region ImageView size, will be used while creating source of the ImageView
    public static String BITMAP_SIZE;

    public static void setBMPSize(String bmpSize) {
        BITMAP_SIZE = bmpSize;
    }

    public static String getBMPSize() {
        return BITMAP_SIZE;
    }
    //endregion

    //region Base URL to get the full path of the movie poster,
    public final static String MOVIE_POSTER_BASE_URL = "http://image.tmdb.org/t/p/";

    public static String getMoviePosterBaseURL() {
        return MOVIE_POSTER_BASE_URL;
    }
    //endregion

    //region String lieteral, to get selected movie id
    //constant used while sending the movie id from main activity to details activity
    public final static String SELECTED_MOVIE_ID_LITERAL = "Selected Movie ID";

    public static String getSelectedMovieIdLiteral() {
        return SELECTED_MOVIE_ID_LITERAL;
    }
    //endregion

    //region Get Movie Details URL - Part 1
    //base url to fetch the movie details
    public final static String MOVIE_DETAILS_URL_PART1 = "http://api.themoviedb.org/3/movie/";

    public static String getMovieDetailsURLPart1() {
        return MOVIE_DETAILS_URL_PART1;
    }
    //endregion

    //region Get Movie Details URL - Part 2
    //base url to fetch the movie details
    public final static String MOVIE_DETAILS_URL_PART2 = "?api_key=";

    public static String getMovieDetailsURLPart2() {
        return MOVIE_DETAILS_URL_PART2;
    }
    //endregion

    //region Sort word in query
    public final static String SORT_BY_KEY_WORD = "&sort_by=";

    public static String getSortKeyWord() {

        return SORT_BY_KEY_WORD;
    }
    //endregion

    //region Popularity Field Name
    //Query to get movie data , sorted by popularity and rating, sample query as below
    //http://api.themoviedb.org/3/discover/movie?&api_key=&sort_by=popularity.desc
    // first part of the query, till api_key is already defined above, then plug in he api key and sort key word,
    // so te last part is as below

    //Reference : https://www.themoviedb.org/documentation/api/discover
    public final static String POPULARITY_FIELD_NAME = "popularity.desc";

    public static String getPopularityFieldName() {
        return POPULARITY_FIELD_NAME;
    }
    //endregion

    //region Running Device Density like HDPI/XHDPI...

    public static Double RUNNING_DEVICE_DENSITY;

    public static void setDeviceDensity(Double density) {
        RUNNING_DEVICE_DENSITY = density;
    }

    public static Double getDeviceDensity() {
        return RUNNING_DEVICE_DENSITY;
    }
    //endregion

    //region Hightest Rating Field NAme
    //Rating
    public final static String RATING_FIELD_NAME = "vote_average.desc";

    public static String getHighestRatingFieldName() {
        return RATING_FIELD_NAME;
    }
    //endregion

    //region Posters GridView Fragment Tag value
    public final static String GRID_VIEW_FRAGMENT_TAG = "GRIDVIEW FRAGMENT";

    public static String getGridViewFragmentTag() {
        return GRID_VIEW_FRAGMENT_TAG;
    }
    //endregion

    //region Construct Error Message
    public static String constructErrorMsg(String errType, String functionName, String errMsg) {

        String returnErrorMessage = "The " + errType + " occurred in the function, " +
                functionName + ", Error message : " + errMsg;
        return returnErrorMessage;
    }
    //endregion
}
