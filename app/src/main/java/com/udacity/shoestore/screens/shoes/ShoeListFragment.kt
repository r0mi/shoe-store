package com.udacity.shoestore.screens.shoes

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeListFragmentBinding
import com.udacity.shoestore.databinding.ShoeListItemBinding
import com.udacity.shoestore.screens.login.LoginViewModel

class ShoeListFragment : Fragment() {
    private val viewModel by activityViewModels<ShoesViewModel>()
    private val loginViewModel by activityViewModels<LoginViewModel>()
    private lateinit var binding: ShoeListFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.shoe_list_fragment, container, false)

        setHasOptionsMenu(true)

        val navController = findNavController()

        loginViewModel.userLoggedIn.observe(viewLifecycleOwner, { userIsLoggedIn ->
            if (userIsLoggedIn) {
                //
            } else {
                val action = ShoeListFragmentDirections.actionShoeListDestinationToLoginDestination()
                navController.navigate(action)
            }
        })

        viewModel.fabClicked.observe(viewLifecycleOwner, { buttonClicked ->
            if (buttonClicked) {
                val action = ShoeListFragmentDirections.actionShoeListDestinationToShoeDetailsDestination()
                navController.navigate(action)
                viewModel.onFabClickedComplete()
            }
        })

        viewModel.shoes.observe(viewLifecycleOwner, { shoes ->
            for (shoe in shoes) {
                val shoeBinding: ShoeListItemBinding = DataBindingUtil.inflate(
                        inflater,
                        R.layout.shoe_list_item,
                        binding.shoeListLayout,
                        true
                )
                shoeBinding.shoe = shoe
            }
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.log_out -> loginViewModel.logout()
        }
        return super.onOptionsItemSelected(item)
    }
}