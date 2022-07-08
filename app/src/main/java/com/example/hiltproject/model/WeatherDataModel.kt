package com.example.hiltproject

import android.os.Parcel
import android.os.Parcelable

import android.widget.ArrayAdapter
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.ParcelField
import com.google.gson.annotations.SerializedName

@Entity (tableName = "weather_table")
data class WeatherDataModel (
                             @PrimaryKey(autoGenerate = true)
                             val id: Int=0,
                             val timepoint: Int?,
                             val temp2m: Int?,
                             val cloudcover: Int?,
                             val seeing: Int?,
                             val transparency: Int?,
                             val lifted_index: Int?,
                             val rh2m: Int?,
                             val prec_type: String?,
                             @Embedded

                             val wind10m: WeatherDr?,
                              )

data class WeatherDr( val speed: Int?,
                      val direction: String?)

data class WeatherModel(val dataseries:ArrayList<WeatherDataModel>)

