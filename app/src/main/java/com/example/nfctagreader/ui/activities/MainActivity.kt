package com.example.nfctagreader.ui.activities

import android.app.Activity
import android.os.Bundle
import com.example.nfctagreader.R
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)

    }
}