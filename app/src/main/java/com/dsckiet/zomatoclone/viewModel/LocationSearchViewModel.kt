package com.dsckiet.zomatoclone.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dsckiet.zomatoclone.models.LocationSearch
import com.dsckiet.zomatoclone.models.LocationSuggestionX
import com.dsckiet.zomatoclone.repository.LocationSearchRepository

class LocationSearchViewModel(application: Application):AndroidViewModel(application) {
    val locationSearch:LiveData<List<LocationSuggestionX>>
    val showProgress:LiveData<Boolean>
    val repository=LocationSearchRepository(application)
    init {
        this.locationSearch=repository.locationSearch
        this.showProgress=repository.showProgress
    }
    fun searchLocation(query:String)
    {
        repository.searchLocation(query)
    }

}