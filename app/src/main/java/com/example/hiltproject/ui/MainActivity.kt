package com.example.hiltproject.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hiltproject.WeatherDataModel
import com.example.hiltproject.databinding.ActivityMainBinding
import com.example.hiltproject.repo.MainRepo
import com.example.hiltproject.roomDatabase.WeatherDao
import com.example.hiltproject.roomDatabase.WeatherDatabase
import com.example.hiltproject.viewModel.MainActivityViewModel


class MainActivity : AppCompatActivity(){
    lateinit private var repository: MainRepo
    lateinit var viewModel: MainActivityViewModel
    lateinit var weatherAdapter : WeatherAdapter
    private var binding: ActivityMainBinding?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)
        setupRecyclerView()
        val  dao = WeatherDatabase.invoke(application).getWeatherDao()
        setupViewModel(dao)
        observeData(dao)
    }

    private fun setupViewModel(dao: WeatherDao) {
        repository = MainRepo(dao)
        viewModel = ViewModelProviders.of(this,
            MainActivityViewModel.ViewModelFactory(repository)).get(MainActivityViewModel::class.java)
        binding?.progressCircular!!.visibility= View.VISIBLE

        repository .getDetail(applicationContext)
    }

    private fun observeData(dao: WeatherDao) {
        binding?.progressCircular!!.visibility= View.GONE

        dao.getAllData().observe(this) {
            if (it.isNullOrEmpty()) {
               repository .getDetail(applicationContext)
            }else{
                binding?.progressCircular!!.visibility = View.GONE
                weatherAdapter.updateList(it as ArrayList<WeatherDataModel> /* = java.util.ArrayList<com.example.hiltproject.WeatherDataModel> */)

            }
        }

    }

    private fun setupRecyclerView() {
        binding?.recyclerView?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            weatherAdapter=WeatherAdapter(object : WeatherAdapter.ClickListener{
                override fun onItemClickListener(position: Int) {
                    val intent= Intent(this@MainActivity,WeatherDetailActivity::class.java)
                    intent.putExtra("POSITION",position)
                    startActivity(intent)
                }
            })
            adapter=weatherAdapter
        }

    }

}