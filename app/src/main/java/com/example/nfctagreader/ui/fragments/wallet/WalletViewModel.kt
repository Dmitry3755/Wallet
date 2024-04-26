package com.example.nfctagreader.ui.fragments.wallet

import com.example.nfctagreader.ui.base.BaseViewModel
import com.example.nfctagreader.ui.navigation.BaseRouter
import javax.inject.Inject

class WalletViewModel @Inject constructor() : BaseViewModel() {

    override val router = WalletRouting()


}