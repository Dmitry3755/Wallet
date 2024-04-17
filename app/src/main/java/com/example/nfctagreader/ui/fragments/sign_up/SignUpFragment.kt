package com.example.nfctagreader.ui.fragments.sign_up

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.nfctagreader.R
import com.example.nfctagreader.databinding.FmtScannerCardScreenBinding
import com.example.nfctagreader.databinding.FmtSignUpBinding
import com.example.nfctagreader.ui.base.BaseFragment
import com.example.nfctagreader.ui.base.BaseViewModel
import com.example.nfctagreader.view_model.DaggerViewModelFactory
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignUpFragment : BaseFragment(R.layout.fmt_sign_up) {

    @Inject
    lateinit var daggerViewModelFactory: ViewModelProvider.Factory
    override val mViewModel by viewModels<SignUpViewModel>() {
        daggerViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

/*        mViewModel.user.observe(viewLifecycleOwner) {
            mViewModel.navigateToWalletMainFragment()
        }*/
    }

}