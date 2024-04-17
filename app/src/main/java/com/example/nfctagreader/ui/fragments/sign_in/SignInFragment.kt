package com.example.nfctagreader.ui.fragments.sign_in

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.nfctagreader.R
import com.example.nfctagreader.databinding.FmtSignInBinding
import com.example.nfctagreader.ui.base.BaseFragment
import com.example.nfctagreader.ui.fragments.sign_up.SignUpViewModel
import com.example.nfctagreader.view_model.DaggerViewModelFactory
import javax.inject.Inject

class SignInFragment : BaseFragment(R.layout.fmt_sign_in) {

    @Inject
    lateinit var daggerViewModelFactory: ViewModelProvider.Factory
    override val mViewModel by viewModels<SignInViewModel>() {
        daggerViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
