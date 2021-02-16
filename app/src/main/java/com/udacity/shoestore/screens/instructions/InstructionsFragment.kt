package com.udacity.shoestore.screens.instructions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.InstructionsFragmentBinding

class InstructionsFragment : Fragment() {
    private lateinit var binding: InstructionsFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.instructions_fragment, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.instructions_fragment, container, false)
        binding.viewShoesButton.setOnClickListener {
            val action = InstructionsFragmentDirections.actionInstructionsDestinationToShoeListDestination()
            NavHostFragment.findNavController(this).navigate(action)
        }
        return binding.root
    }
}