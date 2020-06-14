package com.dsckiet.zomatoclone.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dsckiet.zomatoclone.R
import com.dsckiet.zomatoclone.models.restaurant
import kotlinx.android.synthetic.main.restaurant_cell.view.*


class HomeScreenAdapter(private val context: Context) :
    RecyclerView.Adapter<HomeScreenAdapter.ViewHolder>() {
    var list: List<restaurant> = ArrayList()

    fun getLocationData(list: List<restaurant>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.restaurant_cell, parent, false)
        )

    }

    override fun getItemCount(): Int {
        return list.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.restaurantName.text = item.restaurant.name
        holder.restaurantFoodType.text = item.restaurant.cuisines
        holder.restaurantFoodPrice.text = item.restaurant.currency +" "+item.restaurant.averageCostForTwo.toString()+" for two people"
        holder.restaurantRating.text = item.restaurant.userRating.aggregateRating
        var imageUrl = item.restaurant.thumb
        var rating_color="#"+item.restaurant.userRating.ratingColor
        holder.restaurantRating.setBackgroundColor(Color.parseColor(rating_color))

        Log.i("image", imageUrl)
        Glide.with(context)
            .load(imageUrl)
            .into(holder.restaurantImage)
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val restaurantRating: TextView = view.restaurant_rating
        val restaurantImage: ImageView = view.restaurant_image
        val restaurantName: TextView = view.restaurant_name
        val restaurantFoodType: TextView = view.restaurant_food_type
        val restaurantFoodPrice: TextView = view.restaurant_food_price
        val restaurantFoodOffer: TextView = view.restaurant_food_offer

    }

}