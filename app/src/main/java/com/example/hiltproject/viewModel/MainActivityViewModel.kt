package com.example.hiltproject.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hiltproject.repo.MainRepo
import com.example.hiltproject.WeatherModel

class MainActivityViewModel(var mainRepo: MainRepo): ViewModel() {
 
    private val dataWeather = MutableLiveData<WeatherModel>()



    // getter method for seconds var
    fun getWeatherData(): LiveData<WeatherModel> {
        return dataWeather
    }

 
 
     fun getDetail(applicationContext: Context) {
     //   mainRepo.getDetail(this, applicationContext)
    }



    class ViewModelFactory(private val viewModel: MainRepo) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainActivityViewModel(viewModel) as T
        }
    }
}