package com.udacity.shoestore.screens.shoes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoesViewModel : ViewModel() {
    private val _shoes = MutableLiveData<MutableList<Shoe>>()
    val shoes: LiveData<MutableList<Shoe>> = _shoes

    private var _fabClicked = MutableLiveData<Boolean>()
    val fabClicked: LiveData<Boolean> = _fabClicked

    fun onFabClicked() {
        _fabClicked.value = true
    }

    fun onFabClickedComplete() {
        _fabClicked.value = false
    }

    fun addShoe(shoe: Shoe) {
        val shoeList = _shoes.value ?: mutableListOf()
        shoeList.add(shoe)
        _shoes.value = shoeList
    }

    init {
        _fabClicked.value = false
    }
}