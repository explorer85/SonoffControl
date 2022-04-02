    package com.learntodroid.postrequestwithjson;

    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.RadioGroup;

    import androidx.appcompat.app.AppCompatActivity;

    import retrofit2.Call;
    import retrofit2.Callback;
    import retrofit2.Response;

    public class CommentsActivity extends AppCompatActivity {
        private EditText urlText;
        private Button send;
        private String switchState = "on";

        private CommentsRepository commentsRepository;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_comments);

            urlText = findViewById(R.id.activity_comments_url);
            send = findViewById(R.id.activity_comments_send);



            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    commentsRepository.url = urlText.getText().toString();
                    commentsRepository = commentsRepository.getInstance();

                    Data d = new Data("on");
                    if (switchState == "on")
                        switchState = "off";
                    else
                        switchState = "on";
                    d.switc = switchState;
                            Comment c = new Comment("100000140e",d);
                            commentsRepository.getCommentsService().createComment(c).enqueue(new Callback<Comment>() {
                                @Override
                                public void onResponse(Call<Comment> call, Response<Comment> r) {
                                   // Toast.makeText(getApplicationContext(), "Comment " + r.body().getComment() + " created", Toast.LENGTH_SHORT).show();
                                }
                                @Override
                                public void onFailure(Call<Comment> call, Throwable t) {
                                   // Toast.makeText(getApplicationContext(), "Error Creating Comment: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });



                }
            });
        }
    }
//88fi6897
//sonoff ESP_1A337B
