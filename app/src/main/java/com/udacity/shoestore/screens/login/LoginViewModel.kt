package com.udacity.shoestore.screens.login

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.textfield.TextInputLayout
import com.udacity.shoestore.R

@BindingAdapter("errorText")
fun setErrorMessage(view: TextInputLayout, errorMessage: String?) {
    view.error = errorMessage
}

class LoginViewModel : ViewModel() {

    val email = MutableLiveData<String>()
    private val _emailErrorStrResId = MutableLiveData(0)
    val emailErrorStrResId: LiveData<Int> = _emailErrorStrResId

    val password = MutableLiveData<String>()
    private val _passwordErrorStrResId = MutableLiveData(0)
    val passwordErrorStrResId: LiveData<Int> = _passwordErrorStrResId

    private var _eventLoggedIn = MutableLiveData<Boolean>()
    val eventLoggedIn: LiveData<Boolean> = _eventLoggedIn

    private val _userLoggedIn = MutableLiveData(false)
    val userLoggedIn: LiveData<Boolean> = _userLoggedIn

    private val _newUser = MutableLiveData(false)
    val newUser: LiveData<Boolean> = _newUser

    private fun validateEmail(email: String?, emptyAllowed: Boolean = false): Boolean {
        return if (email.isNullOrEmpty() && !emptyAllowed) {
            _emailErrorStrResId.value = R.string.error_email_required
            false
        } else if (email.isNullOrEmpty() || android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailErrorStrResId.value = 0
            true
        } else {
            _emailErrorStrResId.value = R.string.error_email_not_valid
            false
        }
    }

    private fun validatePassword(password: String?, emptyAllowed: Boolean = false): Boolean {
        return if (password.isNullOrEmpty() && !emptyAllowed) {
            _passwordErrorStrResId.value = R.string.error_password_required
            false
        } else if (password.isNullOrEmpty() || password.length >= 5) {
            _passwordErrorStrResId.value = 0
            true
        } else {
            _passwordErrorStrResId.value = R.string.error_password_too_short
            false
        }
    }

    fun login() {
        val emailIsValid = validateEmail(email.value)
        val passwordIsValid = validatePassword(password.value)
        if (emailIsValid && passwordIsValid) {
            _eventLoggedIn.value = true
            _userLoggedIn.value = true
        }
    }

    fun logout() {
        _userLoggedIn.value = false
    }

    fun createAccountAndLogin() {
        _newUser.value = true
        login()
    }

    fun onLoggedInComplete() {
        _eventLoggedIn.value = false
    }

    init {
        _emailErrorStrResId.value = 0
        _passwordErrorStrResId.value = 0
        _eventLoggedIn.value = false

        email.observeForever {
            validateEmail(it, true)
        }

        password.observeForever {
            validatePassword(it, true)
        }
    }

}