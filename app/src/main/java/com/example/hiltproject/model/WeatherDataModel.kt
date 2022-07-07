package com.example.hiltproject

import android.os.Parcel
import android.os.Parcelable

import android.widget.ArrayAdapter
import androidx.versionedparcelable.ParcelField
import com.google.gson.annotations.SerializedName


data class WeatherDataModel (
    @SerializedName("id")
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
                              ):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        TODO("wind10m")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeValue(timepoint)
        parcel.writeValue(temp2m)
        parcel.writeValue(cloudcover)
        parcel.writeValue(seeing)
        parcel.writeValue(transparency)
        parcel.writeValue(lifted_index)
        parcel.writeValue(rh2m)
        parcel.writeString(prec_type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WeatherDataModel> {
        override fun createFromParcel(parcel: Parcel): WeatherDataModel {
            return WeatherDataModel(parcel)
        }

        override fun newArray(size: Int): Array<WeatherDataModel?> {
            return arrayOfNulls(size)
        }
    }
}

data class WeatherDr( val speed: Int?,
                      val direction: String?)

data class WeatherModel(val dataseries:ArrayList<WeatherDataModel>)

