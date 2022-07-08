package com.example.hiltproject.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hiltproject.WeatherDataModel
import com.example.hiltproject.databinding.ItemLayoutBinding
import kotlin.collections.ArrayList

class WeatherAdapter(private val clickListener: ClickListener) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>()  {

    private val listener :ClickListener = clickListener
    private var list= ArrayList<WeatherDataModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(dataSeries: ArrayList<WeatherDataModel>) {
        list.clear()
        list.addAll(dataSeries)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

       val binding =  ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(list[position],listener, position)
    }

    override fun getItemCount(): Int {
     return  list.size
    }
    class ViewHolder(private val itemBinding: ItemLayoutBinding):RecyclerView.ViewHolder(itemBinding.root){

        fun bindView(weatherDataModel: WeatherDataModel, listener: ClickListener, position: Int) {
            itemBinding.apply {
                text.text = weatherDataModel.timepoint.toString()
                text1.text = weatherDataModel.temp2m.toString()
                llRoot.setOnClickListener{
                      listener.onItemClickListener(position)
                }
            }
            }
    }
    interface ClickListener{
        fun onItemClickListener(position: Int)
    }


}