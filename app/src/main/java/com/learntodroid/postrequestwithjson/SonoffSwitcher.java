package com.learntodroid.postrequestwithjson;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SonoffSwitcher {
    private CommentsRepository commentsRepository;
    public String url;
    public String response;

    public void switchSonoffPower(boolean on) {
        Data d = new Data("on");
        if(on)  {
            d.switc = "on";
        } else {
            d.switc = "off";
        }

        commentsRepository.url = url;
        commentsRepository = commentsRepository.getInstance();
        Comment c = new Comment("100000140e",d);
        commentsRepository.getCommentsService().createComment(c).enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> r) {
                Log.i("","Sucsesssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
                response = "Sucsess" + r.toString();
               // textResponse.setText("Sucsess" + r.toString());

            }
            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                response = "onFailure" + t.getMessage();
              //  textResponse.setText("onFailure" + t.getMessage());
            }
        });
    }
}
