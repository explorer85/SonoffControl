    package com.learntodroid.postrequestwithjson;

    import android.content.Intent;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.View;
    import android.widget.Button;
    import android.widget.Switch;
    import android.widget.CompoundButton;
    import android.widget.EditText;
    import android.widget.TextView;


    import androidx.appcompat.app.AppCompatActivity;

    import java.util.Observable;
    import java.util.Observer;
    import java.util.Timer;
    import java.util.TimerTask;
    import java.util.logging.Logger;

    import retrofit2.Call;
    import retrofit2.Callback;
    import retrofit2.Response;

    public class CommentsActivity extends AppCompatActivity implements Observer {
        private EditText urlText;
        Switch switchPower;
        TextView textResponse;
        Switch switchPeriodic;
        private EditText textPeriod;
        private CommentsRepository commentsRepository;



        SonoffSwitcher ss;

       /* public void switchSonoffPower(boolean on) {
                Data d = new Data("on");
                if(on)  {
                    d.switc = "on";
                } else {
                    d.switc = "off";
                }

                commentsRepository.url = urlText.getText().toString();
                commentsRepository = commentsRepository.getInstance();
                Comment c = new Comment("100000140e",d);
                commentsRepository.getCommentsService().createComment(c).enqueue(new Callback<Comment>() {
                    @Override
                    public void onResponse(Call<Comment> call, Response<Comment> r) {
                        textResponse.setText("Sucsess" + r.toString());

                    }
                    @Override
                    public void onFailure(Call<Comment> call, Throwable t) {
                        textResponse.setText("onFailure" + t.getMessage());
                    }
                });
        }*/

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_comments);

            urlText = findViewById(R.id.activity_comments_url);
            switchPower = findViewById(R.id.switchPower);
            textResponse = findViewById(R.id.textResponse);
            switchPeriodic = findViewById(R.id.switchPeriodic);
            textPeriod = findViewById(R.id.textPerid);


            switchPower.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    textResponse.setText("");
                    ss = new SonoffSwitcher();
                    ss.addObserver(CommentsActivity.this);
                    ss.url = urlText.getText().toString();
                    ss.switchSonoffPower(isChecked);
                    textResponse.setText(ss.response);


                }
            });

            switchPeriodic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        startService(
                                new Intent(CommentsActivity.this,
                                        PeriodicTimerService.class).putExtra("url", urlText.getText().toString()).
                                        putExtra("period", textPeriod.getText().toString()));

                    } else {
                        stopService(
                                new Intent(CommentsActivity.this, PeriodicTimerService.class));

                    }
                }
            });





        }


        private SonoffSwitcher sonoffUpdate ;
        @Override
        public void update(Observable observable, Object o) {
            sonoffUpdate = (SonoffSwitcher) observable;
            textResponse.setText(sonoffUpdate.getResponseText());
        }
    }


//88fi6897
//sonoff ESP_1A337B
