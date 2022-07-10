package com.example.hiltproject.network

import com.example.hiltproject.WeatherModel
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {



    @GET("bin/astro.php?lon=79.45&lat=43.98&ac=0&unit=metric&output=json&tzshift=0")
   suspend fun getWeatherListAsync(): WeatherModel


    companion object {

        var retrofitService: RetrofitService? = null

        //Create the Retrofit service instance using the retrofit.
        fun getInstance(): RetrofitService {

            if (retrofitService == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client = OkHttpClient.Builder().addInterceptor(interceptor).build()



                val gson = GsonBuilder()
                    .setLenient()
                    .create()
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://www.7timer.info/")
                    .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))

                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }

}

