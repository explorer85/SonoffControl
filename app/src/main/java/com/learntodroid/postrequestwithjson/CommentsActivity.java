    package com.learntodroid.postrequestwithjson;

    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.Switch;
    import android.widget.CompoundButton;
    import android.widget.EditText;
    import android.widget.TextView;


    import androidx.appcompat.app.AppCompatActivity;

    import java.util.Timer;
    import java.util.TimerTask;

    import retrofit2.Call;
    import retrofit2.Callback;
    import retrofit2.Response;

    public class CommentsActivity extends AppCompatActivity {
        private EditText urlText;
        private Button send;
        Switch switchPower;
        TextView textResponse;
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
            send = findViewById(R.id.activity_comments_send);
            switchPower = findViewById(R.id.switchPower);
            textResponse = findViewById(R.id.textResponse);



            switchPower.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    //запускаем таймер выключения если включили устройство
                    if (isChecked) {
                        //switchOffTimerTask = new SwitchOffTimerTask();
                        //switchOffTimer.schedule(switchOffTimerTask, 60000);
                    }
                    textResponse.setText("");
                    switchSonoffPower(isChecked);


                }
            });




            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });

            switchOffTimer = new Timer();
        }

        class SwitchOffTimerTask extends TimerTask {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        switchPower.setChecked(false);
                    }
                });
            }
        }
    }


//88fi6897
//sonoff ESP_1A337B
