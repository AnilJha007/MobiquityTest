package com.mobile.mobiquityassignment.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.mobile.mobiquityassignment.R
import com.mobile.mobiquityassignment.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_mobiquity.*

@AndroidEntryPoint
class MobiquityActivity : BaseActivity() {

    override fun getLayoutResourceId() = R.layout.activity_mobiquity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpNavActionBar()
    }

    private fun setUpNavActionBar() {
        val appBarConfiguration = AppBarConfiguration.Builder(R.id.homeDest).build()
        setupActionBarWithNavController(
                (navHostFragment as NavHostFragment).navController,
                appBarConfiguration
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}