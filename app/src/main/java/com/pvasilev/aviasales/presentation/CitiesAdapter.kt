package com.pvasilev.aviasales.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pvasilev.aviasales.R
import com.pvasilev.aviasales.data.models.City
import kotlinx.android.synthetic.main.item_city.view.*

class CitiesAdapter : ListAdapter<City, CitiesAdapter.CityVH>(CitiesAdapter) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityVH {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        return CityVH(itemView)
    }

    override fun onBindViewHolder(holder: CityVH, position: Int) {
        holder.bind(getItem(position))
    }

    class CityVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(city: City) {
            itemView.tv_city.text = city.latinCity
            itemView.tv_fullname.text = city.latinFullName
            itemView.tv_code.text = city.countryCode
        }
    }

    companion object : DiffUtil.ItemCallback<City>() {
        override fun areItemsTheSame(oldItem: City, newItem: City): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: City, newItem: City): Boolean = oldItem == newItem
    }
}