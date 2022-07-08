package com.example.hiltproject.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.hiltproject.WeatherDataModel
import com.example.hiltproject.databinding.ActivityWeatherDetailBinding
import com.example.hiltproject.roomDatabase.WeatherDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WeatherDetailActivity : AppCompatActivity() {
    private var binding : ActivityWeatherDetailBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherDetailBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)

        GlobalScope.launch {
            val dao = WeatherDatabase.invoke(application).getWeatherDao()
            val dataModel=dao.selectSingleItem(1+intent.getIntExtra("POSITION",0))
            binding?.apply {
                text.text = dataModel.timepoint.toString()
                text1.text=dataModel.temp2m.toString()
                text2.text=dataModel.cloudcover.toString()
                text3.text=dataModel.seeing.toString()
                text4.text=dataModel.transparency.toString()
                text5.text=dataModel.lifted_index.toString()
                text6.text=dataModel.rh2m.toString()
                text7.text=dataModel.prec_type.toString()
                text8.text=dataModel.wind10m?.speed.toString()
                text9.text=dataModel.wind10m?.direction.toString()
                imgBack.setOnClickListener {
                    finish()
                }

            }
        }


    }
}