package com.example.nfctagreader.ui.navigation

import androidx.navigation.NavDirections

open class BaseRouter {

    val navigationState = SingleLiveEvent<RoutingAction>()

    fun navigateToFragment(navDirections: NavDirections) {
        navigationState.value = RoutingAction.NavigateToFragment(navDirections)
    }

    fun navigateToAction(action: RoutingAction) {
        navigationState.value = action
    }
}