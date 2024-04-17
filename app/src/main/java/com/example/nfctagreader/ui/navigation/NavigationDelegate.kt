package com.example.nfctagreader.ui.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.nfctagreader.ui.base.BaseViewModel

class NavigationDelegate(private val fragment: Fragment) {

    fun setUpNavigate(viewModel: BaseViewModel) {
        viewModel.router?.navigationState?.observe(fragment.viewLifecycleOwner) {
            handleRoutingAction(it)
        }
    }

    private fun handleRoutingAction(action: RoutingAction) {
        when (action) {
            is RoutingAction.NavigateToFragment -> {
                fragment.findNavController().navigate(action.navDirections)
            }

            RoutingAction.NavigationBack -> {
                fragment.findNavController().navigateUp()
            }
        }
    }
}