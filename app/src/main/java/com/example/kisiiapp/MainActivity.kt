package com.example.kisiiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kisiiapp.Screens.LoginScreen
import com.example.kisiiapp.ui.theme.KisiiAppTheme
import com.example.kisiiapp.ui.theme.NavyGreen
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val authViewModel : AuthViewModel by viewModels()
        setContent {
            KisiiAppTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()
    val authViewModel = remember { AuthViewModel() }

    // List of routes where BottomNavBar should NOT be displayed
    val excludedRoutes = listOf("splash", "login", "register")

    Scaffold (
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            // Show the BottomNavBar only if the current route is not "splash"
            if (currentRoute !in excludedRoutes) {
                BottomNavBar(navController)
            }
        },
        containerColor = Color.White
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)){
            AppNavGraph(
                navController = navController,
                authViewModel = authViewModel
            )
        }

    }

    }

@Composable
fun BottomNavBar(navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val screenList = listOf(
        BottomNavScreens.Home,
        BottomNavScreens.Search,
        BottomNavScreens.MyCart,
        BottomNavScreens.Orders,
        BottomNavScreens.Account,
    )

    // Define fixed colors to prevent changes in dark mode
    val backgroundColor = Color.White
    val selectedIconColor = NavyGreen
    val unselectedIconColor = Color.Black


    NavigationBar(containerColor = backgroundColor) {
        screenList.forEach { screen ->
            NavigationBarItem(
                selected = currentDestination?.route == screen.route,
                onClick = { navController.navigate(screen.route) },
                icon = {
                    Icon(
                        painter = painterResource(id = screen.icon),
                        contentDescription = "nav icon",
                        modifier = Modifier.size(24.dp)
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = selectedIconColor,
                    unselectedIconColor = unselectedIconColor

                )
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun MainAppPreview() {
    KisiiAppTheme {
        MainApp()
    }
}