package com.example.nfctagreader.ui.fragments.sign_in

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.nfctagreader.R
import com.example.nfctagreader.databinding.FmtSignInBinding
import com.example.nfctagreader.databinding.FmtSignUpBinding
import com.example.nfctagreader.ui.base.BaseFragment
import com.example.nfctagreader.ui.fragments.sign_up.SignUpViewModel
import com.example.nfctagreader.view_model.DaggerViewModelFactory
import javax.inject.Inject

class SignInFragment : BaseFragment() {

    @Inject
    lateinit var daggerViewModelFactory: ViewModelProvider.Factory
    override val mViewModel by viewModels<SignInViewModel>() {
        daggerViewModelFactory
    }
    private var binding: FmtSignInBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FmtSignInBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
