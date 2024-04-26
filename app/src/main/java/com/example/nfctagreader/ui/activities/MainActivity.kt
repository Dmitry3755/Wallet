package com.example.nfctagreader.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.nfctagreader.R
import com.example.nfctagreader.databinding.ActMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
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
    private lateinit var floatingActionButton: FloatingActionButton
    private lateinit var navView: BottomNavigationView

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActMainBinding.inflate(layoutInflater)
        daggerViewModelFactory.create(MainActivityViewModel::class.java)
        navView = binding.navView
        floatingActionButton = binding.addCardButton
        setContentView(binding.root)
        initializeNav()
        navView.setupWithNavController(navController)
        addCardSetOnClickListener()
    }

    private fun initializeNav() {
        val navFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navFragment.navController
        if (mViewModel.getEmail() != null && mViewModel.getPassword() != null) {
            navController.setGraph(R.navigation.navigation_graph)
            navController.navigate(R.id.navigation_sign_in)
        }
    }

    fun addCardSetOnClickListener() {
        floatingActionButton.setOnClickListener {
            navController.navigate(R.id.navigation_scan_card)
        }
    }

    fun setNavigationBarVisibility(isVisible: Boolean) {
        binding.frameContainer.isVisible = isVisible
    }

    fun showSnackBarWithMessage(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

}