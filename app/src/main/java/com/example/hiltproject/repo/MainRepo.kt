package com.example.hiltproject.repo

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.room.Dao
import com.example.hiltproject.WeatherModel
import com.example.hiltproject.network.RetrofitService
import com.example.hiltproject.isNetworkAvailable
import com.example.hiltproject.roomDatabase.WeatherDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainRepo (val weatherDao: WeatherDao){

    fun getDetail(applicationContext: Context){
        if (isNetworkAvailable(applicationContext)) {

                  try {
                      kotlinx.coroutines.GlobalScope.launch {
                          val response = RetrofitService.getInstance().getWeatherListAsync()
                          weatherDao.insertList(response.dataseries)
                      }
                  }catch (ex: Exception) {
                        Log.d("main", "onFailure: " + ex.message)
                        Log.d("main", "onFailure: " + ex.toString())
                        Log.d("main", "onFailure: " + ex.localizedMessage)
                    }
        }
        else{
            Toast.makeText(applicationContext,"Network not found",Toast.LENGTH_LONG).show()
        }

    }

}