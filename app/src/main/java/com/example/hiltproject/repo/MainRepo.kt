package com.example.hiltproject.repo

import android.content.Context
import android.widget.Toast
import com.example.hiltproject.WeatherModel
import com.example.hiltproject.network.RetrofitService
import com.example.hiltproject.isNetworkAvailable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainRepo {

    interface  GetDetail{
        fun getSuccesData(response: WeatherModel)
        fun getError(localizedMessage: String)
    }

    fun getDetail(listener: GetDetail, applicationContext: Context){
        if (isNetworkAvailable(applicationContext)) {

            kotlinx.coroutines.GlobalScope.launch {
                val response = RetrofitService.getInstance().getWeatherListAsync()

                withContext(Dispatchers.Main) {
                    try {
                        listener.getSuccesData(response)
                    } catch (ex: Exception) {
                        //   listener.getError(ex.localizedMessage!!)

                    }
                }
            }
        }
        else{
            Toast.makeText(applicationContext,"Network not found",Toast.LENGTH_LONG).show()
        }

    }

}