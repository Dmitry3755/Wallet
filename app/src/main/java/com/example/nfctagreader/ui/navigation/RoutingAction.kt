package com.example.nfctagreader.ui.navigation

import androidx.navigation.NavDirections

sealed class RoutingAction {

    class NavigateToFragment(val navDirections: NavDirections) : RoutingAction()
    object NavigationBack : RoutingAction()

}