    package com.learntodroid.postrequestwithjson;

    import android.os.Bundle;
    import android.util.Log;
    import android.view.View;
    import android.widget.Button;
    import android.widget.Switch;
    import android.widget.CompoundButton;
    import android.widget.EditText;
    import android.widget.TextView;


    import androidx.appcompat.app.AppCompatActivity;

    import java.util.Timer;
    import java.util.TimerTask;
    import java.util.logging.Logger;

    import retrofit2.Call;
    import retrofit2.Callback;
    import retrofit2.Response;

    public class CommentsActivity extends AppCompatActivity {
        private EditText urlText;
        Switch switchPower;
        TextView textResponse;
        Switch switchPeriodic;
        private EditText textPeriod;
        private CommentsRepository commentsRepository;

        private Timer switchOffTimer;
        private SwitchOffTimerTask switchOffTimerTask;

        public void switchSonoffPower(boolean on) {
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
        }

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
                    switchSonoffPower(isChecked);


                }
            });

            switchPeriodic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    //запускаем таймер выключения если включили устройство
                    if (isChecked) {
                        switchSonoffPower(true);
                        switchOffTimer = new Timer();
                        switchOffTimerTask = new SwitchOffTimerTask();
                        switchOffTimer.schedule(switchOffTimerTask, 0, Long.parseLong(textPeriod.getText().toString()) * 1000);
                    } else {
                        switchOffTimer.cancel();

                        switchSonoffPower(false);
                    }
                }
            });





        }

        private boolean isOn = false;
        class SwitchOffTimerTask extends TimerTask {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        Log.i("","timer runnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
                        textResponse.setText("");
                        isOn = !isOn;
                        switchSonoffPower(isOn);
                    }
                });
            }
        }
    }


//88fi6897
//sonoff ESP_1A337B
