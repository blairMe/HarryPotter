package com.bfa.harrypotterarticle.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bfa.harrypotterarticle.ui.screens.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HarryPotterNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = HarryPotterScreens.HomeScreen.name) {
        composable(HarryPotterScreens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }
    }
}