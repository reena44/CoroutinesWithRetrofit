package com.example.hiltproject.rxJavaLearning;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static ApiServices apiServices = null;
    public static ApiServices getInstance() {
        if (apiServices == null) {
            final OkHttpClient okHttpClient = makeOkHttpClient();
            final Retrofit client = makeRetrofit(okHttpClient);
            apiServices = client.create(ApiServices.class);
        }
        return apiServices;
    }

    private static Retrofit makeRetrofit(OkHttpClient okHttpClient) {

        return new Retrofit.Builder()
                .baseUrl("https://www.7timer.info/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }


    private static OkHttpClient makeOkHttpClient() {
        final HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //add the interceptor for logging the curl commands
        return new OkHttpClient.Builder()
                //.addInterceptor(new SecurityInterceptor(PrefManager.getInstance()))
                .addInterceptor(httpLoggingInterceptor)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .cache(null)
                .build();
    }

}