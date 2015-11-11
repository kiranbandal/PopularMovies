package com.kirangisp.popularmoviesui;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.GlobalObjects;

/**
 * Contains helper functions to the Movie Posters Activity.
 */
public class MoviePostersActivityHelper {

    public static final String POSTERS_ACTIVITY_HELPER_CLASS_TAG = "POSTERS HELPER TAG";

    /*
    * Sets global objects like, Device Density, Poster ImgageView dimensions, Resize dimensions.
    * */
    public static void setImageViewAndPosterResizeDimensions(Context appContext) {

        try {
            //get device density
            double runningDeviceDensity = appContext.getResources().getDisplayMetrics().density;

            //set global object, this will be used to resize te movie poster image
            GlobalObjects.setDeviceDensity(runningDeviceDensity);

           /*
            * Set image size to be queried from movie db api,
            * set ImageView Size to be created on the fly
            * and set movie poster image resize dimension
            * */



            if (GlobalObjects.getDeviceDensity() == 1.0) {

                //get device screen size
                int screenSize = appContext.getResources().getConfiguration().screenLayout &
                        Configuration.SCREENLAYOUT_SIZE_MASK;

                //Small screen
                if (screenSize == Configuration.SCREENLAYOUT_SIZE_SMALL) {

                    //cater for small screen size
                    GlobalObjects.setMoviePosterImgSize("w92/");
                    GlobalObjects.setImgViewPadding("1");

                    GlobalObjects.setImgViewHeight(135);
                    GlobalObjects.setImgViewWidth(155);

                    GlobalObjects.setPosterResizewWidth(130);
                    GlobalObjects.setPosterResizewHeight(150);
                }

                else {
                    //cater for large and medium screens
                    GlobalObjects.setMoviePosterImgSize("w154/");
                    GlobalObjects.setImgViewPadding("1");

                    GlobalObjects.setImgViewHeight(265);
                    GlobalObjects.setImgViewWidth(305);

                    GlobalObjects.setPosterResizewWidth(245);
                    GlobalObjects.setPosterResizewHeight(285);
                }


            } else if (GlobalObjects.getDeviceDensity() == 1.5) {

                //if the device density is HDPI, e.g. Samsung Quattro
                GlobalObjects.setMoviePosterImgSize("w185/");
                GlobalObjects.setImgViewPadding("1");

                GlobalObjects.setImgViewHeight(265);
                GlobalObjects.setImgViewWidth(305);

                GlobalObjects.setPosterResizewWidth(245);
                GlobalObjects.setPosterResizewHeight(285);

            } else if (GlobalObjects.getDeviceDensity() == 2) {

                //if the device density is xHDPI, Nexus 4
                GlobalObjects.setMoviePosterImgSize("w342/");
                GlobalObjects.setImgViewPadding("3");

                GlobalObjects.setImgViewHeight(405);
                GlobalObjects.setImgViewWidth(495);

                GlobalObjects.setPosterResizewWidth(395);
                GlobalObjects.setPosterResizewHeight(438);

            } else if (GlobalObjects.getDeviceDensity() == 3) {

                //if the device density is xxHDPI, Nexus 5
                GlobalObjects.setMoviePosterImgSize("w500/");
                GlobalObjects.setImgViewPadding("4");

                GlobalObjects.setImgViewHeight(630);
                GlobalObjects.setImgViewWidth(800);

                GlobalObjects.setPosterResizewWidth(615);
                GlobalObjects.setPosterResizewHeight(755);


            } else if (GlobalObjects.getDeviceDensity() == 4) {

                ////if the device density is xHDPI, My S6
                GlobalObjects.setMoviePosterImgSize("w780/");
                GlobalObjects.setImgViewPadding("5");

                GlobalObjects.setImgViewHeight(830);
                GlobalObjects.setImgViewWidth(1000);

                GlobalObjects.setPosterResizewWidth(820);
                GlobalObjects.setPosterResizewHeight(980);
            }
            else {

                //cater for for remaining screen densities
                GlobalObjects.setMoviePosterImgSize("w92/");
                GlobalObjects.setImgViewPadding("1");

                GlobalObjects.setImgViewHeight(135);
                GlobalObjects.setImgViewWidth(155);

                GlobalObjects.setPosterResizewWidth(130);
                GlobalObjects.setPosterResizewHeight(150);
            }
        } catch (NullPointerException nullExc) {

            String errMsg = GlobalObjects.constructErrorMsg("Null Pointer Exception ", "setImageViewAndPosterResizeDimensions()",
                    nullExc.getMessage());
            Log.e(POSTERS_ACTIVITY_HELPER_CLASS_TAG, errMsg);

            GlobalObjects.setMoviePosterImgSize("w185/");
            GlobalObjects.setImgViewPadding("1");

            GlobalObjects.setImgViewHeight(265);
            GlobalObjects.setImgViewWidth(305);

            GlobalObjects.setPosterResizewWidth(245);
            GlobalObjects.setPosterResizewHeight(285);

        } catch (Exception ex) {
            String errMsg = GlobalObjects.constructErrorMsg("Generic Exception ", "setImageViewAndPosterResizeDimensions()",
                    ex.getMessage());
            Log.e(POSTERS_ACTIVITY_HELPER_CLASS_TAG, errMsg);

            GlobalObjects.setMoviePosterImgSize("w185/");
            GlobalObjects.setImgViewPadding("1");

            GlobalObjects.setImgViewHeight(265);
            GlobalObjects.setImgViewWidth(305);

            GlobalObjects.setPosterResizewWidth(245);
            GlobalObjects.setPosterResizewHeight(285);
        }


    }

    /**
     * Checks whether the device is connected to te Internet.
     */
    public static boolean isPhoneConnectedToInternet(Context appContext) {


        // Whether there is a Wi-Fi connection.
        boolean wifiConnected = false;
        // Whether there is a mobile connection.
        boolean mobileConnected = false;

        try {
            ConnectivityManager connMgr =
                    (ConnectivityManager) appContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();

            if (activeInfo != null && activeInfo.isConnected()) {

                wifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
                mobileConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;

                if (wifiConnected || mobileConnected) {
                    return true;
                }
            } else {
                return false;
            }
        } catch (SecurityException securitEx) {

            String errMsg = GlobalObjects.constructErrorMsg("Security Exception", "isPhoneConnectedToInternet()", securitEx.getMessage());
            Log.e(POSTERS_ACTIVITY_HELPER_CLASS_TAG, errMsg);
            return false;

        } catch (NullPointerException ex) {
            String errMsg = GlobalObjects.constructErrorMsg("Null Reference Exception", "isPhoneConnectedToInternet()", ex.getMessage());
            Log.e(POSTERS_ACTIVITY_HELPER_CLASS_TAG, errMsg);
            return false;

        } catch (Exception e) {
            String errMsg = GlobalObjects.constructErrorMsg("Generic Exception", "isPhoneConnectedToInternet()", e.getMessage());
            Log.e(POSTERS_ACTIVITY_HELPER_CLASS_TAG, errMsg);
            return false;
        }
        return false;
    }
}
