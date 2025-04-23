package com.example.kisiiapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kisiiapp.Screens.*

@Composable
fun AppNavGraph(navController: NavHostController, authViewModel: AuthViewModel) {
    NavHost(
        navController = navController,
        startDestination = "splash" // Set login as the first screen
    ) {
        composable("splash") {
            SplashScreen(navController = navController, authViewModel = authViewModel)
        }
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true } // Remove login screen from back stack
                    }
                },
                navController = navController,
                authViewModel = authViewModel
            )
        }
        composable("Home") { backStackEntry ->
            HomeScreen(navController = navController, authViewModel = authViewModel)
        }

        composable("search") {
            SearchScreen(navController = navController, authViewModel = authViewModel)
        }
        composable("myCart") {
            MyCartScreen(navController = navController, authViewModel = authViewModel)
        }
        composable("orders") {
            OrdersScreen(navController = navController, authViewModel = authViewModel)
        }
        composable("account") {
            AccountScreen(navController = navController, authViewModel = authViewModel)
        }

        composable("register") {
            RegisterScreen(navController = navController, authViewModel = authViewModel)
        }
    }
}