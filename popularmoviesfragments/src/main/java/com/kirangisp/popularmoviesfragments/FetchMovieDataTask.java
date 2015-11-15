package com.kirangisp.popularmoviesfragments;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.FetchMovieRequest;
import com.example.FetchMovieRequestType;
import com.example.GlobalObjects;
import com.kirangisp.fragmenthelpermodule.CommonFunctions;
import com.kirangisp.fragmenthelpermodule.CustomJSONParser;
import com.kirangisp.fragmenthelpermodule.MovieResponseHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Async Task to get the movie data from the moviedb api. Same task is used
 * for all types of requests.
 */
public class FetchMovieDataTask extends AsyncTask<Void, Void, String> {

    //will hold, request type, query url
    private FetchMovieRequest mRequest;

    private Context mAppContext;

    private final static String FETCH_MOVIE_TASK_LOG_TAG = "Fetch Movie Task";

    //Empty Constructor
    public FetchMovieDataTask() {

    }

    //parametrised constructor
    public FetchMovieDataTask(FetchMovieRequest request, Context c) {
        mAppContext = c;
        mRequest = request;
    }

    @Override
    protected String doInBackground(Void... params) {


        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String movieDataResponseJsonStr = null;

        try {

            URL url = new URL(mRequest.getQueryURL());

            // Create the request to MovieDB API, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }


            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }

            movieDataResponseJsonStr = buffer.toString();
        } catch (IOException ex) {

            String errMsg = GlobalObjects.constructErrorMsg("IO Exception", "doInBackground()", ex.getMessage());
            Log.e(FETCH_MOVIE_TASK_LOG_TAG, errMsg);

            return null;

        } catch (Exception ex) {

            String errMsg = GlobalObjects.constructErrorMsg("Generic Exception", "doInBackground()", ex.getMessage());
            Log.e(FETCH_MOVIE_TASK_LOG_TAG, errMsg);

            return null;
        } finally {
            //CLose the Net Connection
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

            //close the reder
            if (reader != null) {
                try {
                    reader.close();
                } catch (IllegalArgumentException e) {
                    String errMsg = GlobalObjects.constructErrorMsg("Illegal Argument Exception", "doInBackground()", e.getMessage());
                    Log.e(FETCH_MOVIE_TASK_LOG_TAG, errMsg);
                } catch (final IOException e) {
                    String errMsg = GlobalObjects.constructErrorMsg("IOE xception", "doInBackground()", e.getMessage());
                    Log.e(FETCH_MOVIE_TASK_LOG_TAG, errMsg);
                }
            }

            return movieDataResponseJsonStr; //the response from the moviedb api is returned
        }
    }

    /*
* Function to return the HashMap that contains, the IDs of the needed views
* on the Movie Details Info fragment
* */


    @Override
    protected void onPostExecute(String taskOuput) {

        try {
            //create instance of response handler and depending in request type, call the appropriate method
            MovieResponseHandler responseHandler = new MovieResponseHandler();

            String rawMovieJson = taskOuput;

         /* if the request type was DETAIL_OF_SINGLE_MOVIE, then only, response is to be handled differently
        * In other cases, we just use the same method to create custom adapater and shoe posters in gridview.
        * */

            if (mRequest.getRequestType() == FetchMovieRequestType.DETAIL_OF_SINGLE_MOVIE) {
                //get the needed movie details by parsing it
                CommonFunctions.setMovieDetailsInfo(new CustomJSONParser(rawMovieJson).getMovieDetails());

                responseHandler.displayMovieDetails(mAppContext,
                        CommonFunctions.getMovieDetailsInfo(),
                        MovieDetailsInfoFragment.getMovieDetailFragViews());
            }

            else {
            /*for all other request types, same method from the handler will be called
            * Call the json parser class and get the ArrayList of movie info objects.
            * */

                //save the ArrayList in global property so that it can be accessed on the reqd fragment
                CommonFunctions.setMoviePosterInfoArrayLst(new CustomJSONParser(rawMovieJson).parseFavoriteMoviesJSON());
                responseHandler.displayMoviePosters(CommonFunctions.getMoviePosterInfoArrayLst(),
                        mAppContext,R.id.moviePostersGrdView);
            }

        } catch (NullPointerException ex) {
            String errMsg = GlobalObjects.constructErrorMsg("Null Pointer Exception", "onPostExecute()", ex.getMessage());
            Log.e(FETCH_MOVIE_TASK_LOG_TAG, errMsg);
        }

        catch (Exception ex) {
            String errMsg = GlobalObjects.constructErrorMsg("Generic Exception", "onPostExecute()", ex.getMessage());
            Log.e(FETCH_MOVIE_TASK_LOG_TAG, errMsg);
        }

    }
}
