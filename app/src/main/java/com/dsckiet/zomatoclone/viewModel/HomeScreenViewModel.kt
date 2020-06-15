package com.dsckiet.zomatoclone.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

import com.dsckiet.zomatoclone.models.restaurant
import com.dsckiet.zomatoclone.repository.HomeScreenRepository

class HomeScreenViewModel(application: Application) : AndroidViewModel(application) {
    val showProgress: LiveData<Boolean>
    val showRestaurant: LiveData<List<restaurant>>
    val repository = HomeScreenRepository(application)

    init {
        this.showProgress = repository.showProgress
        this.showRestaurant = repository.showRestaurant
    }

    fun getRestaurantDetails(entity_id: Int, entity_type: String) {
        repository.getRestaurantDetails(entity_id, entity_type)
    }
}