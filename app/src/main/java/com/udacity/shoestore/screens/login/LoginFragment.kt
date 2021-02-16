package com.udacity.shoestore.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.LoginFragmentBinding

class LoginFragment : Fragment() {
    private val viewModel by activityViewModels<LoginViewModel>()
    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)

        viewModel.eventLoggedIn.observe(this.viewLifecycleOwner, { hasLoggedIn ->
            if (hasLoggedIn) {
                val username = viewModel.email.value.toString().split("@").first()
                val action = LoginFragmentDirections.actionLoginDestinationToWelcomeDestination(username, viewModel.newUser.value!!)
                NavHostFragment.findNavController(this).navigate(action)
                viewModel.onLoggedInComplete()
            }
        })

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.passwordEditText.apply {
            setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    viewModel.login()
                }
                false
            }
        }

        return binding.root
    }
}