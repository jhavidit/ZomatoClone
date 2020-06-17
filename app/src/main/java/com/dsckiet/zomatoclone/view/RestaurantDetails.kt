package com.dsckiet.zomatoclone.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.dsckiet.zomatoclone.R
import kotlinx.android.synthetic.main.fragment_restaurant_details.*


class RestaurantDetails : Fragment() {
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        val restaurantName = arguments?.getString("resName")
        val resAdd = arguments?.getString("resAddress")
        val resImg = arguments?.getString("resImage")
        val resCuisines = arguments?.getString("resCuisines")
        var resContact = arguments?.getString("resContact")
        val resMenu = arguments?.getString("resMenu")
        val resPhoto = arguments?.getString("resPhoto")
        val resOrder = arguments?.getString("resOrder")

        resContact = "Contact : $resContact"
        restaurant_detail_name.text = restaurantName
        restaurant_detail_address.text = resAdd
        restaurant_detail_cusines.text = resCuisines
        restaurant_detail_contact.text = resContact
        Glide.with(requireContext())
            .load(resImg)
            .into(restaurant_detail_image)

        restaurant_detail_menu.setOnClickListener {
            val bundle = bundleOf("link" to resMenu)
            it.findNavController()
                .navigate(R.id.action_restaurantDetails_to_webFragment, bundle)
        }

        restaurant_detail_photo.setOnClickListener {
            val bundle = bundleOf("link" to resPhoto)
            it.findNavController()
                .navigate(R.id.action_restaurantDetails_to_webFragment, bundle)
        }
        restaurant_detail_web.setOnClickListener {
            val bundle = bundleOf("link" to resOrder)
            it.findNavController()
                .navigate(R.id.action_restaurantDetails_to_webFragment, bundle)
        }


    }


}