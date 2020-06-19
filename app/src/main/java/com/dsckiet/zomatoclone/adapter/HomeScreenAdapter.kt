package com.dsckiet.zomatoclone.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dsckiet.zomatoclone.R
import com.dsckiet.zomatoclone.models.restaurant
import kotlinx.android.synthetic.main.restaurant_cell.view.*


class HomeScreenAdapter(private val context: Context) :
    RecyclerView.Adapter<HomeScreenAdapter.ViewHolder>() {
    private lateinit var navController: NavController
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
        holder.restaurantFoodPrice.text =
            item.restaurant.currency + " " + item.restaurant.averageCostForTwo.toString() + " for two people"
        holder.restaurantRating.text = item.restaurant.userRating.aggregateRating
        var imageUrl = item.restaurant.thumb
        var rating_color = "#" + item.restaurant.userRating.ratingColor
        holder.restaurantRating.setBackgroundColor(Color.parseColor(rating_color))

        Log.i("image", imageUrl)
        Glide.with(context)
            .load(imageUrl)
            .into(holder.restaurantImage)
        holder.restaurantCard.setOnClickListener {
            //  val resId=item.restaurant.r.resId
            val restaurantName = item.restaurant.name
            val restaurantImage = item.restaurant.thumb
            val restaurantAddress = item.restaurant.location.address
            val restaurantCuisines = item.restaurant.cuisines
            val restaurantContact = item.restaurant.phoneNumbers
            val restaurantPhoto = item.restaurant.photosUrl
            val restaurantMenu = item.restaurant.menuUrl
            val restaurantOrder = item.restaurant.orderUrl
            val bundle = bundleOf(
                "resName" to restaurantName,
                "resImage" to restaurantImage,
                "resAddress" to restaurantAddress,
                "resCuisines" to restaurantCuisines,
                "resContact" to restaurantContact,
                "resPhoto" to restaurantPhoto,
                "resMenu" to restaurantMenu,
                "resOrder" to restaurantOrder
            )
            it.findNavController()
                .navigate(R.id.action_homeScreen_to_restaurantDetails, bundle)


        }

    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val restaurantRating: TextView = view.restaurant_rating
        val restaurantImage: ImageView = view.restaurant_image
        val restaurantName: TextView = view.restaurant_name
        val restaurantFoodType: TextView = view.restaurant_food_type
        val restaurantFoodPrice: TextView = view.restaurant_food_price
        val restaurantCard: CardView = view.restaurant_cell_card


    }


}