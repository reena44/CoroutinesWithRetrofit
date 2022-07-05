package com.example.hiltproject.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hiltproject.R
import com.example.hiltproject.WeatherDataModel

class WeatherAdapter(var clickListener: MainActivity, val dataseries: ArrayList<WeatherDataModel>) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>()  {

      val listener = clickListener;
    var list : ArrayList<WeatherDataModel> = dataseries
    public fun updateList(dataseries: ArrayList<WeatherDataModel>) {
        list.clear()
        list.addAll(dataseries)
        notifyDataSetChanged()


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(list[position],holder)

    }

    override fun getItemCount(): Int {
     return   list.size
    }
    class ViewHolder (view:View):RecyclerView.ViewHolder(view){
        var textview = view.findViewById<TextView>(R.id.text)
        var textview1 = view.findViewById<TextView>(R.id.text1)

        fun bindView(weatherDataModel: WeatherDataModel, holder: ViewHolder) {

            holder.textview.text = weatherDataModel.timepoint.toString()
            holder.textview1.text = weatherDataModel.temp2m.toString()


        }


    }
    interface ClickListener{
        fun onItemClickListener(position: Int)
    }


}