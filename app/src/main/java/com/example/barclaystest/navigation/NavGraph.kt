package com.example.barclaystest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.barclaystest.presentation.PaymentOptionScreen
import com.example.barclaystest.presentation.PaymentScreen
import com.example.barclaystest.presentation.PaymentStatus
import com.example.barclaystest.navigation.Route.HOME_SCREEN
import com.example.barclaystest.navigation.Route.PAYMENT_SCREEN
import com.example.barclaystest.navigation.Route.PAYMENT_STATUS

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = HOME_SCREEN) {
        composable(route = HOME_SCREEN) {
            PaymentOptionScreen(navController)
        }

        composable(
            route = PAYMENT_SCREEN,
            arguments = listOf(
                navArgument("selectedOption") {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            val selectedOption = navBackStackEntry.arguments?.getString("selectedOption")
            PaymentScreen(navController, selectedOption)
        }

        composable(
            route = PAYMENT_STATUS
        ) {
            PaymentStatus(navController)
        }
    }
}