package com.learntodroid.postrequestwithjson;

import android.util.Log;

import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SonoffSwitcher extends Observable {
    private CommentsRepository commentsRepository;
    public String url;
    public String response;

    public String getResponseText()
    {
        return response;
    }

    public void setResponseText(String response)
    {
        this.response = response;
        setChanged();
        notifyObservers();
    }

    public void switchSonoffPower(boolean on) {
        Data d = new Data("on");
        if(on)  {
            d.switc = "on";
        } else {
            d.switc = "off";
        }

        commentsRepository = new CommentsRepository();
        commentsRepository.url = url;
        Comment c = new Comment("100000140e",d);
        commentsRepository.getCommentsService().createComment(c).enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> r) {
                Log.i("","Sucsesssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
                response = "Sucsess" + r.toString();
                setResponseText(response);
            }
            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                response = "onFailure" + t.getMessage();
                setResponseText(response);
            }
        });
    }
}
