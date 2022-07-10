package com.example.hiltproject.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hiltproject.CommonUtills.WeatherDiffUtil
import com.example.hiltproject.WeatherDataModel
import com.example.hiltproject.databinding.ItemLayoutBinding
import kotlin.collections.ArrayList

class WeatherAdapter(clickListener: ClickListener) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>()  {

    private val listener :ClickListener = clickListener
    private var list = emptyList<WeatherDataModel>()


    fun updateList(dataSeries: ArrayList<WeatherDataModel>) {
        val diffUtil =
            WeatherDiffUtil(list, dataSeries)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        list = dataSeries
        diffUtilResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding =  ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            bindView(list[position],listener, position)
        }
    }

    override fun getItemCount(): Int {
        return  list.size
    }
    class ViewHolder(private val itemBinding: ItemLayoutBinding):RecyclerView.ViewHolder(itemBinding.root){

        fun bindView(weatherDataModel: WeatherDataModel, listener: ClickListener, position: Int) {
            itemBinding.apply {
                with(weatherDataModel){
                    text.text = this.timepoint.toString()
                    text1.text = this.temp2m.toString()
                }
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