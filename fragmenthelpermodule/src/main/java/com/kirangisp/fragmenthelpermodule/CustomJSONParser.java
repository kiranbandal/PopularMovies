package com.kirangisp.fragmenthelpermodule;

import com.example.GlobalObjects;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Exposes the functions to parse the raw json retrieved from the movie db api. These functions return
 * the result in needed types like HelperModulrMovieDetails and ArrayList of HelperModuleMovieData
 */
public class CustomJSONParser {
    private String mInputJSONString;

    //region Constructors
    //Empty Constructor
    public CustomJSONParser() {
    }

    //Constructor taking raw json as parameter
    public CustomJSONParser(String inputJSON) {

        this.mInputJSONString = inputJSON;
    }
    //endregion

    public HelperModuleMovieDetails getMovieDetails() {

        try {

            JSONObject jsonObj = new JSONObject(this.mInputJSONString);

            //we have flat json object here, so we can directly get the needed values from it
            String relaseDate = jsonObj.getString("release_date");
            String ttl = jsonObj.getString("original_title");
            String vote = jsonObj.getString("vote_average");
            String posterPath = jsonObj.getString("poster_path");
            String synopsis = jsonObj.getString("overview");

            return new HelperModuleMovieDetails(posterPath,
                    null,
                    ttl,
                    relaseDate,
                    vote,
                    synopsis);

        }
        catch (JSONException ex) {
            return null;

        } catch (Exception e) {
            return null;
        }
    }

    /*
* This is the  method that gets called to parse the raw json (got from moviedb api call
* while fetching data for all favorite movies
*
* */
    public ArrayList<HelperModuleMovieData> parseFavoriteMoviesJSON() {

        //Array List will be returned
        ArrayList<HelperModuleMovieData> favoriteMoviesDataArrayList = new ArrayList<HelperModuleMovieData>();

        try {

            //get entire json and get "results" array form it
            JSONObject entireJson = new JSONObject(this.mInputJSONString);
            JSONArray resultsJsonObjectsArray =
                    (JSONArray) entireJson.get(GlobalObjects.getJsonResultsTagName());

            /**
             * //loop on all objects in "results" json,
             //starting from ""adult"" up to "vote_count"
             */
            for (int i = 0; i < resultsJsonObjectsArray.length(); i++) {

                //get each movie json
                JSONObject movieJson = resultsJsonObjectsArray.getJSONObject(i);

                //read values from json object and pass them in MovieInfo instance
                HelperModuleMovieData movieInfo = new HelperModuleMovieData
                        (movieJson.getString("poster_path"),
                                movieJson.getString("id"));

                //add our object
                favoriteMoviesDataArrayList.add(movieInfo);
            }

        } catch (JSONException jsonEx) {
            //return returnString;
            String errMsg = jsonEx.getMessage();

        } catch (Exception ex) {
            String errMsg = ex.getMessage();
        } finally {

            this.mInputJSONString = null;
            return favoriteMoviesDataArrayList;
        }

    }
}
