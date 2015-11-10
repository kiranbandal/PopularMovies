package com.example;

/**
 * To hold the request type. This enum is used in FetchMovieRequest class to specify the request type like,
 * all movies or get details of a single selecgted movie etc.
 */
public enum FetchMovieRequestType {
    ALL_MOVIES,
    DETAIL_OF_SINGLE_MOVIE,
    SORT_ON_POPULARITY,
    SORT_ON_HIGHTEST_RATING
}
