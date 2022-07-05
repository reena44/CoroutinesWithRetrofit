package com.example.hiltproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hiltproject.R
import com.example.hiltproject.repo.MainRepo
import com.example.hiltproject.viewModel.MainActivityViewModel


class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainActivityViewModel
    lateinit var recyclerView :RecyclerView
           lateinit var adapter : WeatherAdapter
    lateinit var progress_circular: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        progress_circular = findViewById(R.id.progress_circular)
        setupRecyclerView()
        setupViewModel()
        progress_circular.visibility = View.VISIBLE
        viewModel.getDetail(applicationContext)
        observeData()
    }

    private fun observeData() {
        viewModel.getWeatherData().observe(this, Observer {
            progress_circular.visibility = View.GONE
            adapter.updateList(it.dataseries)
        })

        viewModel.getError().observe(this, Observer {

        })

    }

    private fun setupViewModel() {
         viewModel = ViewModelProviders.of(this, MainActivityViewModel.ViewModelFactory(MainRepo())).get(
            MainActivityViewModel::class.java)
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        adapter = WeatherAdapter(this, arrayListOf())
        recyclerView.adapter = adapter
    }


}