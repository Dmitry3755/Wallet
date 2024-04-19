package com.example.nfctagreader.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.nfctagreader.R
import com.example.nfctagreader.databinding.ActMainBinding
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var daggerViewModelFactory: ViewModelProvider.Factory
    private val mViewModel by viewModels<MainActivityViewModel>() {
        daggerViewModelFactory
    }

    private lateinit var navController: NavController
    private lateinit var binding: ActMainBinding

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActMainBinding.inflate(layoutInflater)
        daggerViewModelFactory.create(MainActivityViewModel::class.java)

        setContentView(binding.root)

        if (mViewModel.getEmail() != null && mViewModel.getPassword() != null) {
            initializeNav()
        }
    }

    private fun initializeNav() {
        val navFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navFragment.navController
        navController.setGraph(R.navigation.navigation_graph)
        navController.navigate(R.id.navigation_sign_in)
    }

    fun showSnackBarWithMessage(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

}