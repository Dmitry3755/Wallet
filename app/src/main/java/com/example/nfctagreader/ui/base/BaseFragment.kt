package com.example.nfctagreader.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.example.nfctagreader.databinding.FmtSignUpBinding
import com.example.nfctagreader.ui.navigation.NavigationDelegate
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.launch

abstract class BaseFragment(private val fragmentId: Int) : Fragment() {

    private lateinit var inflatedViewBaseFragment: View

    protected abstract val mViewModel: BaseViewModel
    private val mNavigationDelegate: NavigationDelegate = NavigationDelegate(this)

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        inflatedViewBaseFragment = inflater.inflate(fragmentId, container, false)
        return inflatedViewBaseFragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.let {
            mNavigationDelegate.setUpNavigate(it)
        }
    }

}