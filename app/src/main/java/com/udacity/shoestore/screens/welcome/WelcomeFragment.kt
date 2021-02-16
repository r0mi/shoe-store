package com.udacity.shoestore.screens.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.WelcomeFragmentBinding

class WelcomeFragment : Fragment() {
    private lateinit var binding: WelcomeFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.welcome_fragment, container, false)

        binding.nextButton.setOnClickListener {
            val action = WelcomeFragmentDirections.actionWelcomeDestinationToInstructionsDestination()
            NavHostFragment.findNavController(this).navigate(action)
        }

        val args by navArgs<WelcomeFragmentArgs>()
        binding.user = args.user
        binding.newUser = args.newUser

        return binding.root
    }
}