package com.example.hiltproject.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hiltproject.WeatherDataModel
import com.example.hiltproject.databinding.ActivityMainBinding
import com.example.hiltproject.repo.MainRepo
import com.example.hiltproject.viewModel.MainActivityViewModel


class MainActivity : AppCompatActivity() ,WeatherAdapter.ClickListener{
    lateinit var viewModel: MainActivityViewModel
    lateinit var recyclerView :RecyclerView
    lateinit var weatherAdapter : WeatherAdapter
    lateinit var progress_circular: ProgressBar
    private var binding: ActivityMainBinding?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)
        setupRecyclerView()
        setupViewModel()
        binding?.progressCircular!!.visibility = View.VISIBLE
        viewModel.getDetail(applicationContext)
        observeData()
    }

    private fun observeData() {
        viewModel.getWeatherData().observe(this, Observer {
            binding?.progressCircular!!.visibility = View.GONE
            weatherAdapter.updateList(it.dataseries)
        })

        viewModel.getError().observe(this, Observer {

        })

    }

    private fun setupViewModel() {
         viewModel = ViewModelProviders.of(this, MainActivityViewModel.ViewModelFactory(MainRepo())).get(
            MainActivityViewModel::class.java)
    }

    private fun setupRecyclerView() {

        binding?.recyclerView!!.apply {
            layoutManager = LinearLayoutManager(this@MainActivity,RecyclerView.VERTICAL,false)
            weatherAdapter = WeatherAdapter(this@MainActivity, arrayListOf())
            adapter= weatherAdapter
        }
    }

    override fun onItemClickListener(data: WeatherDataModel) {
   //     startActivity(WeatherDetailActivity().createIntent(applicationContext,data))

    }


}