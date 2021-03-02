package com.mindorks.framework.mvvm.custom.remote.volley.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelHeader implements Parcelable {

    public static final Creator<ModelHeader> CREATOR = new Creator<ModelHeader>() {
        @Override
        public ModelHeader createFromParcel(Parcel source) {
            return new ModelHeader(source);
        }

        @Override
        public ModelHeader[] newArray(int size) {
            return new ModelHeader[size];
        }
    };

    private String key;
    private String value;

    public ModelHeader(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public ModelHeader() {
    }

    private ModelHeader(Parcel in) {
        this.key = in.readString();
        this.value = in.readString();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.key);
        dest.writeString(this.value);
    }
}
