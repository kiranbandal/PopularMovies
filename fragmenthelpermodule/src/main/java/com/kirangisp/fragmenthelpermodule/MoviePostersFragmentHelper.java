package com.kirangisp.fragmenthelpermodule;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;

import com.example.GlobalObjects;

/**
 * Helper class to set the global properties of device sensity, orientation
 * movie poster imageview size and re-size dimensions
 */
public class MoviePostersFragmentHelper {

    private static final String PSTRS_FRAG_HLPR_LOG_TAG = "Posters Fragment Helper";

    //region For xxx HPI
    private static void setXxxHDPIPortraitProps(){

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

    private static void setXxxHDPILandscapeProps(){
        //
        //image size to be queried from the movie db api
        RunningDevice.setImgSizeToBeQueried("original");

        //set Image View Padding
        RunningDevice.setPosterImgViewPadding(3);

        //set poster image view height and width
        RunningDevice.setMoviePosterImgViewHeight(1275);
        RunningDevice.setMoviePosterImgViewWidth(1090);

        //set resize width/height of the image
        RunningDevice.setMoviePosterResizeHeight(1260);
        RunningDevice.setMoviePosterResizeWidth(1080);

    }
    //endregion
    //region For XX HDPI
    private static void setXxHDPIPortraitProps() {

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

    private static void setXxHDPILandscapeProps() {

        //image size to be queried from the movie db api
        RunningDevice.setImgSizeToBeQueried("w780/");

        //set Image View Padding
        RunningDevice.setPosterImgViewPadding(3);

        //set poster image view height and width
        RunningDevice.setMoviePosterImgViewHeight(990);
        RunningDevice.setMoviePosterImgViewWidth(815);

        //set resize width/height of the image
        RunningDevice.setMoviePosterResizeHeight(990);
        RunningDevice.setMoviePosterResizeWidth(815);

    }
    //endregion

    //region For xHDPI
    private static void setXHDPIPortraitProps() {

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

    private static void setXHDPILandscapeProps() {

        //image size to be queried from the movie db api
        RunningDevice.setImgSizeToBeQueried("w500/");

        //set Image View Padding
        RunningDevice.setPosterImgViewPadding(2);

        //set poster image view height and width
        RunningDevice.setMoviePosterImgViewHeight(700);
        RunningDevice.setMoviePosterImgViewWidth(550);

        //set resize width/height of the image
        RunningDevice.setMoviePosterResizeHeight(680);
        RunningDevice.setMoviePosterResizeWidth(550);
    }
    //endregion

    //region For HDPI
    private static void setHDPIPortraitProps() {
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

    private static void setHDPILandscapeProps() {

        //image size to be queried from the movie db api
        RunningDevice.setImgSizeToBeQueried("w342/");

        //set Image View Padding
        RunningDevice.setPosterImgViewPadding(3);

        //set poster image view height and width
        RunningDevice.setMoviePosterImgViewHeight(450);
        RunningDevice.setMoviePosterImgViewWidth(375);

        //set resize width/height of the image
        RunningDevice.setMoviePosterResizeHeight(430);
        RunningDevice.setMoviePosterResizeWidth(360);
    }
    //endregion

    //region LDPI and MDPI - Small screen
    private static void setLdpiMdpiSmallScreenPortraitProps() {
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

    private static void setLdpiMdpiSmallScreenLandscapeProps() {
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
    //endregion

    //region For LDPI and MDPI - Large screen
    private static void setLdpiMdpiLargecreenPortraitProps() {
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

    private static void setLdpiMdpiLargecreenLandscapeProps() {
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
    //endregion


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
            if (((Activity) appContext).getResources().getConfiguration().orientation == 1) {

                RunningDevice.setOrientation(RunningDevice.DeviceOrientation.PORTRAIT);
            } else if (((Activity) appContext).getResources().getConfiguration().orientation == 2) {
                RunningDevice.setOrientation(RunningDevice.DeviceOrientation.LANDSCAPE);
            }

            //LDPI and MDPI
            if (runningDeviceDensity == 1) {

                //Small screen (LDPI and MDPI)
                if (screenSize == Configuration.SCREENLAYOUT_SIZE_SMALL) {

                    //Set resize dimensions for Movie Details Fragment
                    RunningDevice.setMovieDetailsPosterResizeWidth(250);
                    RunningDevice.setMovieDetailsPosterResizeHeight(325);

                    if (RunningDevice.getOrientation() == RunningDevice.DeviceOrientation.PORTRAIT) {
                        setLdpiMdpiSmallScreenPortraitProps();
                    } else {
                        setLdpiMdpiSmallScreenLandscapeProps();
                    }

                } else {
                    //medium and large screen (LDPI and MDPI)
                    if (RunningDevice.getOrientation() == RunningDevice.DeviceOrientation.LANDSCAPE) {

                        //Portrait orientation
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
                    } else {

                        //for landscape orientation

                        String a;
                        a = "sdf";
                    }

                }
            }
            //if the device density is HDPI, e.g. Samsung Quattro
            else if (runningDeviceDensity == 1.5) {

                //Set resize dimensions for Movie Details Fragment
                RunningDevice.setMovieDetailsPosterResizeWidth(250);
                RunningDevice.setMovieDetailsPosterResizeHeight(325);

                if (RunningDevice.getOrientation() == RunningDevice.DeviceOrientation.PORTRAIT) {
                    setHDPIPortraitProps();
                } else {
                    setHDPILandscapeProps();
                }
            }

            //if the device density is xHDPI, Nexus 4
            else if (runningDeviceDensity == 2) {

                //Set resize dimensions for Movie Details Fragment
                RunningDevice.setMovieDetailsPosterResizeWidth(400);
                RunningDevice.setMovieDetailsPosterResizeHeight(525);

                if (RunningDevice.getOrientation() == RunningDevice.DeviceOrientation.PORTRAIT) {
                    setXHDPIPortraitProps();
                } else {
                    //For Landscape orientation
                    setXHDPILandscapeProps();
                }
            }

            //if the device density is xxHDPI, Nexus 5
            else if (runningDeviceDensity == 3) {

                //Set resize dimensions for Movie Details Fragment
                RunningDevice.setMovieDetailsPosterResizeWidth(600);
                RunningDevice.setMovieDetailsPosterResizeHeight(825);

                if (RunningDevice.getOrientation() == RunningDevice.DeviceOrientation.PORTRAIT) {
                    setXxHDPIPortraitProps();
                } else {
                    setXxHDPILandscapeProps();
                }
            }
            //if the device density is xxxHDPI, My S6
            else if (runningDeviceDensity == 4) {

                //Set resize dimensions for Movie Details Fragment
                RunningDevice.setMovieDetailsPosterResizeWidth(800);
                RunningDevice.setMovieDetailsPosterResizeHeight(1000);

                if (RunningDevice.getOrientation() == RunningDevice.DeviceOrientation.PORTRAIT) {
                    setXxxHDPIPortraitProps();
                } else {
                    setXxxHDPILandscapeProps();
                }
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

        } catch (NullPointerException nullEx) {

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

        } catch (Exception ex) {
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
