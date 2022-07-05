package com.example.hiltproject

import android.widget.ArrayAdapter

data class WeatherDataModel (
                              val id: Int=0,
                             val timepoint: Int?,
                             val temp2m: Int?,
                             val cloudcover: Int?,
                             val seeing: Int?,
                             val transparency: Int?,
                             val lifted_index: Int?,
                             val rh2m: Int?,
                             val prec_type: String?,
                              val wind10m: WeatherDr?,
                              )

data class WeatherDr( val speed: Int?,
                      val direction: String?)

data class WeatherModel(val dataseries:ArrayList<WeatherDataModel>)

