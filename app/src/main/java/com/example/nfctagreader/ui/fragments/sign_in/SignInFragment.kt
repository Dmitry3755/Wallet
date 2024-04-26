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
import com.example.nfctagreader.utils.isEnteringTextValid
import com.example.nfctagreader.view_model.DaggerViewModelFactory
import javax.inject.Inject

class SignInFragment : BaseFragment() {

    @Inject
    lateinit var daggerViewModelFactory: ViewModelProvider.Factory
    override val mViewModel by viewModels<SignInViewModel>() {
        daggerViewModelFactory
    }

    override val mIsBottomMenuVisible = false

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
        binding?.let { bind ->
            bind.signInButton.setOnClickListener {

                if (isEnteringTextValid(
                        bind.signInEmail,
                        getString(R.string.error_text_is_not_empty)
                    ) ||
                    isEnteringTextValid(
                        bind.signInPassword,
                        getString(R.string.error_text_is_not_empty)
                    )
                ) {
                    return@setOnClickListener
                }

                mViewModel.trySignIn(
                    bind.signInEmail.text.toString(),
                    bind.signInPassword.text.toString()
                )

                mViewModel.profile.observe(viewLifecycleOwner) {
                    mViewModel.navigateToWalletMainFragment()
                }

            }

            bind.signInCreateAccountButton.setOnClickListener {
                mViewModel.navigateToSignUpFragment()
            }
        }
    }

}
