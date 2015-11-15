package com.kirangisp.fragmenthelpermodule;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This class is used on MovieDetailInfoFragments to show the details of a selected movie. This class implements Parcelable
 * so that instance of this class can be saved into the Bundle.
 */
public class HelperModuleMovieDetails extends HelperModuleMovieData implements Parcelable {

    private String title;
    private String releaseYear;
    private String vote;
    private String synopsis;

    public HelperModuleMovieDetails(String moviePosterPathInput,
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

    //Constructor needed for Parcelable
    HelperModuleMovieDetails(Parcel input) {
        super(input);

        title = input.readString();
        releaseYear = input.readString();
        vote = input.readString();
        synopsis = input.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);

        dest.writeString(title);
        dest.writeString(releaseYear);
        dest.writeString(vote);
        dest.writeString(synopsis);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<HelperModuleMovieDetails> CREATOR = new Creator<HelperModuleMovieDetails>() {
        @Override
        public HelperModuleMovieDetails createFromParcel(Parcel in) {
            return new HelperModuleMovieDetails(in);
        }

        @Override
        public HelperModuleMovieDetails[] newArray(int size) {
            return new HelperModuleMovieDetails[size];
        }
    };

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
