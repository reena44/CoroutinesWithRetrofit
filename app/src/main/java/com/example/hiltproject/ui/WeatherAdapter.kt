package com.example.hiltproject.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hiltproject.WeatherDataModel
import com.example.hiltproject.databinding.ItemLayoutBinding
import kotlin.collections.ArrayList

class WeatherAdapter(var clickListener: ClickListener, val dataseries: ArrayList<WeatherDataModel>) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>()  {

      val listener :ClickListener = clickListener;
    var list : ArrayList<WeatherDataModel> = dataseries
    fun updateList(dataseries: ArrayList<WeatherDataModel>) {
        list.clear()
        list.addAll(dataseries)
        notifyDataSetChanged()


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

       val binding =  ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(list[position],listener)


    }

    override fun getItemCount(): Int {
     return   list.size
    }
    class ViewHolder(val itemBinding: ItemLayoutBinding):RecyclerView.ViewHolder(itemBinding.root){

        fun bindView(weatherDataModel: WeatherDataModel, listener: ClickListener) {
            itemBinding.apply {
                text.text = weatherDataModel.timepoint.toString()
                text1.text = weatherDataModel.temp2m.toString()
                llRoot.setOnClickListener{
                      listener.onItemClickListener(weatherDataModel)
                }
            }
            }
    }
    interface ClickListener{
        fun onItemClickListener(position: WeatherDataModel)
    }


}