package com.udacity.shoestore.screens.shoes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeDetailsFragmentBinding
import com.udacity.shoestore.models.Shoe

class ShoeDetailsFragment : Fragment() {
    private lateinit var binding: ShoeDetailsFragmentBinding
    private lateinit var viewModelFactory: ShoeDetailsViewModelFactory
    private val shoeDetailsViewModel by viewModels<ShoeDetailsViewModel> { viewModelFactory }
    private val shoeListViewModel by activityViewModels<ShoesViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val shoeDetailsFragmentArgs by navArgs<ShoeDetailsFragmentArgs>()
        viewModelFactory = ShoeDetailsViewModelFactory(shoeDetailsFragmentArgs.shoe)

        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.shoe_details_fragment,
                container,
                false
        )

        binding.viewModel = shoeDetailsViewModel
        binding.lifecycleOwner = this

        shoeDetailsViewModel.eventCancel.observe(viewLifecycleOwner, { cancel ->
            if (cancel) {
                NavHostFragment.findNavController(this).popBackStack()
                shoeDetailsViewModel.onCancelComplete()
            }
        })

        shoeDetailsViewModel.eventSave.observe(viewLifecycleOwner, { save ->
            if (save) {
                val shoe = Shoe(shoeDetailsViewModel.name.value!!, shoeDetailsViewModel.size.value!!.toDouble(), shoeDetailsViewModel.company.value!!, shoeDetailsViewModel.description.value
                        ?: "")
                shoeListViewModel.addShoe(shoe)
                val action = ShoeDetailsFragmentDirections.actionShoeDetailsDestinationToShoeListDestination()
                NavHostFragment.findNavController(this).navigate(action)
                shoeDetailsViewModel.onSaveComplete()
                Snackbar.make(binding.root, R.string.shoe_added, Snackbar.LENGTH_SHORT).show()
            }
        })

        return binding.root
    }
}