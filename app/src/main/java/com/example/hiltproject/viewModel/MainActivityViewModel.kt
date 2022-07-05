package com.example.hiltproject.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hiltproject.repo.MainRepo
import com.example.hiltproject.WeatherModel

class MainActivityViewModel(var mainRepo: MainRepo): ViewModel(), MainRepo.GetDetail {
 
    private val dataWeather = MutableLiveData<WeatherModel>()
    private val error = MutableLiveData<String>()



    // getter method for seconds var
    fun getWeatherData(): LiveData<WeatherModel> {
        return dataWeather
    }
    // getter method for seconds var
    fun getError(): LiveData<String> {
        return error
    }
 
 
    fun getDetail(applicationContext: Context) {
        mainRepo.getDetail(this, applicationContext)
    }

    override fun getSuccesData(response: WeatherModel) {
        dataWeather.value = response

    }

    override fun getError(localizedMessage: String) {
        error.value = localizedMessage
    }

    class ViewModelFactory(private val viewModel: MainRepo) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainActivityViewModel(viewModel) as T
        }
    }
}