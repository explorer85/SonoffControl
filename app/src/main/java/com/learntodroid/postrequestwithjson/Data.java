package com.learntodroid.postrequestwithjson;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("switch")
    public String switc;

    public Data(String switc) {
        this.switc = switc;
    }

}

