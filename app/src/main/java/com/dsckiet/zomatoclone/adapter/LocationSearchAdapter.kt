package com.dsckiet.zomatoclone.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dsckiet.zomatoclone.R
import com.dsckiet.zomatoclone.models.LocationSuggestionX
import kotlinx.android.synthetic.main.location_cell.view.*


class LocationSearchAdapter(private val context: Context) :
    RecyclerView.Adapter<LocationSearchAdapter.ViewHolder>() {
    var list: List<LocationSuggestionX> = ArrayList()

    fun getLocationData(list: List<LocationSuggestionX>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.location_cell, parent, false)
        )

    }

    override fun getItemCount(): Int {
        return list.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]


        holder.cityName.text = item.name
        Glide.with(context)
            .load(item.countryFlagUrl)
            .into(holder.countryFlag)

        holder.locationSearchCard.setOnClickListener {
            val cityId = item.id
            val cityName = item.name
            val bundle = bundleOf("cityId" to cityId, "cityName" to cityName)
            it.findNavController()
                .navigate(R.id.action_locationSearch_to_homeScreen, bundle)


        }


    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val cityName: TextView = view.city_name
        val countryFlag: ImageView = view.country_img
        val locationSearchCard: CardView = view.location_search_card


    }


}