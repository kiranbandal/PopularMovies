package com.kirangisp.fragmenthelpermodule;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;

import com.example.GlobalObjects;

/**
 * Created by User on 13-Nov-15.
 */
public class MoviePostersFragmentHelper {

    private static final String PSTRS_FRAG_HLPR_LOG_TAG = "Posters Fragment Helper";

    public static void setGlobalDeviceImgeViewProps(Context appContext) {

        try {

            //get device density from Context and set it to the Global property
            double runningDeviceDensity = appContext.getResources().getDisplayMetrics().density;
            RunningDevice.setDeviceDensity(runningDeviceDensity);

            //get screen size
            //get device screen size
            int screenSize = appContext.getResources().getConfiguration().screenLayout &
                    Configuration.SCREENLAYOUT_SIZE_MASK;

            //set current device orientation
            if (((Activity)appContext).getResources().getConfiguration().orientation == 1){

                RunningDevice.setOrientation(RunningDevice.DeviceOrientation.PORTRAIT);
            }
            else if(((Activity)appContext).getResources().getConfiguration().orientation == 2){
                RunningDevice.setOrientation(RunningDevice.DeviceOrientation.LANDSCAPE);
            }

            //LDPI and MDPI
            if (runningDeviceDensity == 1) {

                //Small screen (LDPI and MDPI)
                if (screenSize == Configuration.SCREENLAYOUT_SIZE_SMALL) {

                    //image size to be queried from the movie db api
                    RunningDevice.setImgSizeToBeQueried("w92/");

                    //set Image View Padding
                    RunningDevice.setPosterImgViewPadding(1);

                    //set poster image view height and width
                    RunningDevice.setMoviePosterImgViewHeight(135);
                    RunningDevice.setMoviePosterImgViewWidth(155);

                    //set resize width/height of the image
                    RunningDevice.setMoviePosterResizeHeight(150);
                    RunningDevice.setMoviePosterResizeWidth(130);

                }
                else {
                    //medium and large screen (LDPI and MDPI)

                    //image size to be queried from the movie db api
                    RunningDevice.setImgSizeToBeQueried("w154/");

                    //set Image View Padding
                    RunningDevice.setPosterImgViewPadding(1);

                    //set poster image view height and width
                    RunningDevice.setMoviePosterImgViewHeight(265);
                    RunningDevice.setMoviePosterImgViewWidth(305);

                    //set resize width/height of the image
                    RunningDevice.setMoviePosterResizeHeight(285);
                    RunningDevice.setMoviePosterResizeWidth(245);
                }
            }
            //if the device density is HDPI, e.g. Samsung Quattro
            else if(runningDeviceDensity == 1.5){

                //image size to be queried from the movie db api
                RunningDevice.setImgSizeToBeQueried("w185/");

                //set Image View Padding
                RunningDevice.setPosterImgViewPadding(1);

                //set poster image view height and width
                RunningDevice.setMoviePosterImgViewHeight(265);
                RunningDevice.setMoviePosterImgViewWidth(305);

                //set resize width/height of the image
                RunningDevice.setMoviePosterResizeHeight(285);
                RunningDevice.setMoviePosterResizeWidth(245);
            }

            //if the device density is xHDPI, Nexus 4
            else if(runningDeviceDensity == 2){

                //image size to be queried from the movie db api
                RunningDevice.setImgSizeToBeQueried("w342/");

                //set Image View Padding
                RunningDevice.setPosterImgViewPadding(3);

                //set poster image view height and width
                RunningDevice.setMoviePosterImgViewHeight(405);
                RunningDevice.setMoviePosterImgViewWidth(495);

                //set resize width/height of the image
                RunningDevice.setMoviePosterResizeHeight(438);
                RunningDevice.setMoviePosterResizeWidth(395);
            }

            //if the device density is xxHDPI, Nexus 5
            else if(runningDeviceDensity == 3){

                //image size to be queried from the movie db api
                RunningDevice.setImgSizeToBeQueried("w500/");

                //set Image View Padding
                RunningDevice.setPosterImgViewPadding(4);

                //set poster image view height and width
                RunningDevice.setMoviePosterImgViewHeight(630);
                RunningDevice.setMoviePosterImgViewWidth(800);

                //set resize width/height of the image
                RunningDevice.setMoviePosterResizeHeight(755);
                RunningDevice.setMoviePosterResizeWidth(615);
            }
            //if the device density is xxxHDPI, My S6
            else if(runningDeviceDensity == 4){

                //image size to be queried from the movie db api
                RunningDevice.setImgSizeToBeQueried("w780/");

                //set Image View Padding
                RunningDevice.setPosterImgViewPadding(5);

                //set poster image view height and width
                RunningDevice.setMoviePosterImgViewHeight(830);
                RunningDevice.setMoviePosterImgViewWidth(1000);

                //set resize width/height of the image
                RunningDevice.setMoviePosterResizeHeight(980);
                RunningDevice.setMoviePosterResizeWidth(820);
            }

            else {
                //cater for for remaining screen densities
                //image size to be queried from the movie db api
                RunningDevice.setImgSizeToBeQueried("w185/");

                //set Image View Padding
                RunningDevice.setPosterImgViewPadding(1);

                //set poster image view height and width
                RunningDevice.setMoviePosterImgViewHeight(265);
                RunningDevice.setMoviePosterImgViewWidth(305);

                //set resize width/height of the image
                RunningDevice.setMoviePosterResizeHeight(285);
                RunningDevice.setMoviePosterResizeWidth(245);
            }

        }

        catch (NullPointerException nullEx) {

            String errMsg = GlobalObjects.constructErrorMsg("Null Pointer Exception ",
                    "setGlobalDeviceImgeViewProps()",
                    nullEx.getMessage());
            Log.e(PSTRS_FRAG_HLPR_LOG_TAG, errMsg);

            //image size to be queried from the movie db api
            RunningDevice.setImgSizeToBeQueried("w185/");

            //set Image View Padding
            RunningDevice.setPosterImgViewPadding(1);

            //set poster image view height and width
            RunningDevice.setMoviePosterImgViewHeight(265);
            RunningDevice.setMoviePosterImgViewWidth(305);

            //set resize width/height of the image
            RunningDevice.setMoviePosterResizeHeight(285);
            RunningDevice.setMoviePosterResizeWidth(245);

        }

        catch (Exception ex) {
            String errMsg = GlobalObjects.constructErrorMsg("Generic Exception ",
                    "setGlobalDeviceImgeViewProps()",
                    ex.getMessage());
            Log.e(PSTRS_FRAG_HLPR_LOG_TAG, errMsg);

            //image size to be queried from the movie db api
            RunningDevice.setImgSizeToBeQueried("w185/");

            //set Image View Padding
            RunningDevice.setPosterImgViewPadding(1);

            //set poster image view height and width
            RunningDevice.setMoviePosterImgViewHeight(265);
            RunningDevice.setMoviePosterImgViewWidth(305);

            //set resize width/height of the image
            RunningDevice.setMoviePosterResizeHeight(285);
            RunningDevice.setMoviePosterResizeWidth(245);
        }


    }

}
