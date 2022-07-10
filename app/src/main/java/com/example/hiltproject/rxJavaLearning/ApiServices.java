package com.example.hiltproject.rxJavaLearning;

import com.example.hiltproject.WeatherModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface  ApiServices {

    @GET("bin/astro.php?lon=79.45&lat=43.98&ac=0&unit=metric&output=json&tzshift=0")
    Observable <WeatherModel>getWeatherList();
}
