package com.dsckiet.zomatoclone.view

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dsckiet.zomatoclone.R
import com.dsckiet.zomatoclone.adapter.LocationSearchAdapter
import com.dsckiet.zomatoclone.viewModel.LocationSearchViewModel
import kotlinx.android.synthetic.main.fragment_location_search.*


class LocationSearch : Fragment() {

    private lateinit var viewModel: LocationSearchViewModel
    private lateinit var adapter: LocationSearchAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location_search, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LocationSearchViewModel::class.java)

        location_search.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                // If the event is a key-down event on the "enter" button
                if (event.action === KeyEvent.ACTION_DOWN &&
                    keyCode == KeyEvent.KEYCODE_ENTER
                ) {
                    // Perform action on key press
                    val query = location_search.text.toString()
                    if (query.isEmpty())
                        Toast.makeText(
                            requireContext(),
                            "Search Bar cannot be empty",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    else {
                        Toast.makeText(requireContext(), query, Toast.LENGTH_SHORT).show()
                        adapter = LocationSearchAdapter(requireContext())
                        rv_location_search.adapter = adapter
                        viewModel.showProgress.observe(viewLifecycleOwner, Observer {
                            if (it)
                                progressBar_locationSearch.visibility = View.VISIBLE
                            else
                                progressBar_locationSearch.visibility = View.GONE
                        })
                        viewModel.searchLocation(query)
                        viewModel.locationSearch.observe(viewLifecycleOwner, Observer {
                            adapter.getLocationData(it)

                        })
                    }
                    return true
                }
                return false
            }
        })


    }

}