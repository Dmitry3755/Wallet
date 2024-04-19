package com.example.nfctagreader.ui.fragments.sign_up

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.nfctagreader.R
import com.example.nfctagreader.databinding.FmtSignUpBinding
import com.example.nfctagreader.ui.base.BaseFragment
import com.example.nfctagreader.utils.isEnteringTextValid
import com.example.nfctagreader.utils.isUserCheckUserAgreement
import com.example.nfctagreader.utils.isUserCredentialsEmail
import com.example.nfctagreader.utils.isUserCredentialsPassword
import javax.inject.Inject

class SignUpFragment : BaseFragment() {

    @Inject
    lateinit var daggerViewModelFactory: ViewModelProvider.Factory
    override val mViewModel by viewModels<SignUpViewModel>() {
        daggerViewModelFactory
    }
    private var binding: FmtSignUpBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FmtSignUpBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.let { bind ->

            bind.signUpButton.setOnClickListener {

                if (isEnteringTextValid(
                        bind.signUpFullName,
                        getString(R.string.error_text_is_not_empty)
                    ) ||
                    isEnteringTextValid(
                        bind.signUpEmail,
                        getString(R.string.error_text_is_not_empty)
                    ) ||
                    isEnteringTextValid(
                        bind.signUpPassword,
                        getString(R.string.error_text_is_not_empty)
                    )
                ) {
                    return@setOnClickListener
                }

                if (isUserCredentialsEmail(
                        bind.signUpEmail,
                        getString(R.string.error_email_incorrect)
                    )
                ) {
                    return@setOnClickListener
                }

                if (isUserCredentialsPassword(
                        bind.signUpPassword,
                        getString(R.string.error_password_is_too_short)
                    )
                ) {
                    return@setOnClickListener
                }

                if(isUserCheckUserAgreement(
                    bind.signUpUserAgreement,
                    bind.textInputLayout4,
                    getString(R.string.fmt_sign_up_in_required)
                )) {
                    return@setOnClickListener
                }

                mViewModel.trySignUp(
                    bind.signUpEmail.text.toString(),
                    bind.signUpPassword.text.toString(),
                    bind.signUpFullName.text.toString()
                )
            }
        }

        mViewModel.profile.observe(viewLifecycleOwner) {
            mViewModel.navigateToWalletMainFragment()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}