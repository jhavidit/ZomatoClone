package com.dsckiet.zomatoclone.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dsckiet.zomatoclone.R
import com.dsckiet.zomatoclone.adapter.HomeScreenAdapter
import com.dsckiet.zomatoclone.viewModel.HomeScreenViewModel
import kotlinx.android.synthetic.main.fragment_home_screen.*

class HomeScreen : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var viewModelHome: HomeScreenViewModel
    private lateinit var adapter: HomeScreenAdapter
    var entityId = 23
    var entityState = "city"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            entityId = it.getInt("entityId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //entityId = requireArguments().getInt("entityID")

        return inflater.inflate(R.layout.fragment_home_screen, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //entityId = requireArguments().getInt("entityID")
        viewModelHome = ViewModelProvider(this).get(HomeScreenViewModel::class.java)
        adapter = HomeScreenAdapter(requireActivity())
        rv_home.adapter = adapter

        viewModelHome.showProgress.observe(viewLifecycleOwner, Observer {
            if (it)
                progressBar.visibility = View.VISIBLE
            else
                progressBar.visibility = View.GONE
        })
        Log.i("id", entityId.toString())
        viewModelHome.getRestaurantDetails(entityId, entityState)
        viewModelHome.showRestaurant.observe(viewLifecycleOwner, Observer {
            adapter.getLocationData(it)
        })

    }


}