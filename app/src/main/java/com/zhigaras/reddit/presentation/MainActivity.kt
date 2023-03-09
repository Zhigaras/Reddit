package com.zhigaras.reddit.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zhigaras.reddit.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val bottomTabSet = setOf(R.id.subreddits_tab_nav_graph, R.id.favorites, R.id.profile)
        
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            bottomTabSet
        )
        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottomNavigationBar)
        bottomNavView.setupWithNavController(navController)
        
        val destinationChangesListener =
            NavController.OnDestinationChangedListener { _, destination, _ ->
                bottomNavView.isVisible = true //TODO
            }
        
        navController.addOnDestinationChangedListener(destinationChangesListener)
    }
}