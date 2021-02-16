package com.udacity.shoestore.screens.shoes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.R

class ShoeDetailsViewModel(shoeName: String?, shoeCompany: String?, shoeSize: String?, shoeDescription: String?) : ViewModel() {
    val name = MutableLiveData(shoeName)
    private val _nameErrorStrResId = MutableLiveData(0)
    val nameErrorStrResId: LiveData<Int> = _nameErrorStrResId

    val company = MutableLiveData(shoeCompany)
    private val _companyErrorStrResId = MutableLiveData(0)
    val companyErrorStrResId: LiveData<Int> = _companyErrorStrResId

    val size = MutableLiveData(shoeSize)
    private val _sizeErrorStrResId = MutableLiveData(0)
    val sizeErrorStrResId: LiveData<Int> = _sizeErrorStrResId

    val description = MutableLiveData(shoeDescription)

    private var _eventSave = MutableLiveData(false)
    val eventSave: LiveData<Boolean> = _eventSave

    private var _eventCancel = MutableLiveData(false)
    val eventCancel: LiveData<Boolean> = _eventCancel

    private fun validateInput(value: String?, errorVariable: MutableLiveData<Int>): Boolean {
        return if (value.isNullOrEmpty()) {
            errorVariable.value = R.string.error_required
            false
        } else {
            true
        }
    }

    fun cancel() {
        _eventCancel.value = true
    }

    fun save() {
        val nameOk = validateInput(name.value, _nameErrorStrResId)
        val companyOk = validateInput(company.value, _companyErrorStrResId)
        val sizeOk = validateInput(size.value, _sizeErrorStrResId)
        if (nameOk && companyOk && sizeOk) {
            _eventSave.value = true
        }
    }

    fun onSaveComplete() {
        _eventSave.value = false
    }

    fun onCancelComplete() {
        _eventCancel.value = false
    }

}