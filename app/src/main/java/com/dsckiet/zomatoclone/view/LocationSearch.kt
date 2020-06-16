package com.dsckiet.zomatoclone.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dsckiet.zomatoclone.R
import com.dsckiet.zomatoclone.adapter.HomeScreenAdapter
import com.dsckiet.zomatoclone.viewModel.HomeScreenViewModel


class LocationSearch : Fragment() {

    private lateinit var viewModelHome: HomeScreenViewModel
    private lateinit var adapter: HomeScreenAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location_search, container, false)


    }

}