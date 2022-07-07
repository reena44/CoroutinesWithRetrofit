package com.example.hiltproject.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hiltproject.WeatherDataModel
import com.example.hiltproject.databinding.ActivityWeatherDetailBinding

class WeatherDetailActivity : AppCompatActivity() {
    var binding : ActivityWeatherDetailBinding? = null
    lateinit var dataModel:WeatherDataModel


    companion object{
        const val EXTRA_DATA = "extra_data"
    }

    fun createIntent(context: Context?, data: WeatherDataModel): Intent? {
        val intent = Intent(context, WeatherDetailActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable("dhhd",data)
        intent.putExtra(WeatherDetailActivity.EXTRA_DATA, data)

        return intent
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherDetailBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)

      dataModel = intent.getParcelableExtra<WeatherDataModel>("dhhd")!!

        if (dataModel != null){
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