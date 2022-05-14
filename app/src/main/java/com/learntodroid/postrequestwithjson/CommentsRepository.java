package com.learntodroid.postrequestwithjson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class CommentsRepository {
    public static String url = "62.148.139.147";
    private static CommentsRepository instance;

    private CommentsService commentsService;

    public static CommentsRepository getInstance() {
        if (instance == null) {
            instance = new CommentsRepository();
        }
        return instance;
    }

    public CommentsRepository() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://" + url + ":8081")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        commentsService = retrofit.create(CommentsService.class);
    }

    public CommentsService getCommentsService() {
        return commentsService;
    }
}
