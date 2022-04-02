package com.learntodroid.postrequestwithjson;

import com.google.gson.annotations.SerializedName;



public class Comment {
    @SerializedName("devised")
    public String devised;

    @SerializedName("data")
    public Data data;


    public Comment(String devised, Data data) {
        this.devised = devised;
        this.data = data;
    }


}
