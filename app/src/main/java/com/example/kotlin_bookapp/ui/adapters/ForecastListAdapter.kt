package com.example.kotlin_bookapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_bookapp.R
import com.example.kotlin_bookapp.domain.model.Forecast
import com.example.kotlin_bookapp.domain.model.ForecastList
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_forecast.view.*

class ForecastListAdapter(private val weekForecast: ForecastList, private val itemClick: (Forecast)->Unit) : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent, false), itemClick)

    override fun getItemCount(): Int = weekForecast.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(weekForecast[position])
    }

    class ViewHolder(val view: View, private val itemClick:(Forecast)->Unit):RecyclerView.ViewHolder(view){


        fun bindForecast(forecast: Forecast){
            with(forecast){
                Picasso.with(view.context).load(iconUrl).into(view.icon)
                view.date.text = date.toString()
                view.description.text = description
                view.maxTemperature.text = "$high"
                view.minTemperature.text = "$low"
                itemView.setOnClickListener{itemClick(this)}
            }
        }
    }
}