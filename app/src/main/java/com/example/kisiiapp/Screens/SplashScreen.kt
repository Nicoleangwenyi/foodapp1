package com.example.kisiiapp.Screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kisiiapp.AuthState
import com.example.kisiiapp.AuthViewModel
import com.example.kisiiapp.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController, authViewModel: AuthViewModel) {
    // Observe the authState
    val authState = authViewModel.authState.value

    // Navigate based on the authState
    LaunchedEffect(authState) {
        delay(2000) // 2-second delay for the splash screen

        when (authState) {
            is AuthState.Authenticated -> {
                navController.navigate("Home") {
                    popUpTo("splash") { inclusive = true } // Remove splash screen from back stack
                }
            }
            is AuthState.Unauthenticated -> {
                navController.navigate("login") {
                    popUpTo("splash") { inclusive = true } // Remove splash screen from back stack
                }
            }
            else -> {
                // Handle other states (e.g., Loading, Error) if needed
            }
        }
    }

    // Splash screen UI

    // Gradient background
    val gradientColors = if (isSystemInDarkTheme()) {
        listOf(Color(0xFF0B3D02), Color(0xFF1B5E20)) // Dark theme gradient
    } else {
        listOf(Color(0xFF81C784), Color(0xFF388E3C)) // Light theme gradient
    }

    // Fade-in animation for the content
    AnimatedVisibility(
        visible = true,
        enter = fadeIn(animationSpec = tween(2000)) +
                slideInVertically(initialOffsetY = { -it / 2 }, animationSpec = tween(2000)) // Starts from center and moves down
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(gradientColors)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.splashlogo), // Replace with your logo
                contentDescription = "App Logo",
                modifier = Modifier.size(400.dp)
            )


        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSplashScreen() {
    val navController = rememberNavController()
    val authViewModel = AuthViewModel() // Replace with a fake/mock ViewModel if needed
    SplashScreen(navController = navController, authViewModel = authViewModel)
}