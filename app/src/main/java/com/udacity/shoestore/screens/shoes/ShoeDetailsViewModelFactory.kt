package com.udacity.shoestore.screens.shoes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.models.Shoe

@Suppress("UNCHECKED_CAST")
class ShoeDetailsViewModelFactory(private val shoe: Shoe?) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShoeDetailsViewModel::class.java)) {
            return ShoeDetailsViewModel(shoe?.name, shoe?.company, shoe?.size?.toString(), shoe?.description) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}