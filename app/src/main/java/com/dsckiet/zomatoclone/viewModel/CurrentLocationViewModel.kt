package com.dsckiet.zomatoclone.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dsckiet.zomatoclone.model.LocationSearch
import com.dsckiet.zomatoclone.model.LocationSearchItem
import com.dsckiet.zomatoclone.models.ModelCurrentLocation
import com.dsckiet.zomatoclone.models.ModelLocationData
import com.dsckiet.zomatoclone.repository.CurrentLocationRepository

class CurrentLocationViewModel(application: Application) : AndroidViewModel(application) {
    val currentLocation: LiveData<ModelLocationData>
    val repository = CurrentLocationRepository(application)

    init {
        this.currentLocation = repository.currentLocation
    }
    fun getCurrentLocation(lat:Double,lon:Double)
    {
        repository.getCurrentLocation(lat,lon)
    }

}