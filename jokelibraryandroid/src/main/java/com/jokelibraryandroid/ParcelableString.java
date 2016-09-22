package com.jokelibraryandroid;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Saurabh on 22-09-2016.
 */

public class ParcelableString implements Parcelable {

    public static final Creator<ParcelableString> CREATOR = new Creator<ParcelableString>() {
        @Override
        public ParcelableString createFromParcel(Parcel in) {
            return new ParcelableString(in);
        }

        @Override
        public ParcelableString[] newArray(int size) {
            return new ParcelableString[size];
        }
    };
    private String string;

    public ParcelableString(String string) {
        this.string = string;
    }

    protected ParcelableString(Parcel in) {
        string = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(string);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getString() {
        return string;
    }
}
