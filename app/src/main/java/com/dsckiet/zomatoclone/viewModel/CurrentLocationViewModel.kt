package com.dsckiet.zomatoclone.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dsckiet.zomatoclone.model.LocationSearchItem
import com.dsckiet.zomatoclone.repository.CurrentLocationRepository

class CurrentLocationViewModel(application: Application) : AndroidViewModel(application) {
    val currentLocation: LiveData<LocationSearchItem>
    val repository = CurrentLocationRepository(application)

    init {
        this.currentLocation = repository.currentLocation
    }
    fun getCurrentLocation(lat:Double,lon:Double)
    {
        repository.getCurrentLocation(lat,lon)
    }

}