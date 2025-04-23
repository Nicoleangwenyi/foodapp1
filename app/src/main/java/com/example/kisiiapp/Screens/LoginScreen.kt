package com.example.kisiiapp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.kisiiapp.AuthViewModel
import com.example.kisiiapp.R
import com.example.kisiiapp.ui.theme.KisiiAppTheme
import com.example.kisiiapp.ui.theme.NavyGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    navController: NavController,
    authViewModel: AuthViewModel
) {

    var Email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var rememberMe by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.Start
    ) {

        // Illustration/Image
        Image(
            painter = painterResource(id = R.drawable.loginpic), // Replace with your drawable resource
            contentDescription = "Login Illustration",
            modifier = Modifier
                .height(360.dp)
                .padding(bottom = 5.dp)
                .align(Alignment.CenterHorizontally)
        )

        // Login Text
        Text(
            text = "Welcome Back!",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 38.sp),
            color = NavyGreen
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Please Sign in to continue.",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = Email,
            onValueChange = { Email = it },
            label = { Text("Email") },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Username Icon") },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(40.dp), // Rounded corners
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,   // Primary color when focused
                unfocusedBorderColor = MaterialTheme.colorScheme.outline  // Outline color when not focused
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password Field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            leadingIcon = {
                Icon(Icons.Default.Lock,
                    contentDescription = "Password Icon"

                    )
            },
            trailingIcon = {
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(
                        painter = painterResource(
                            if (isPasswordVisible) R.drawable.visible else R.drawable.novisible
                        ),
                        contentDescription = "Toggle Password Visibility",
                        modifier = Modifier.size(26.dp)
                    )
                }
            },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(40.dp), // Rounded corners
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,   // Primary color when focused
                unfocusedBorderColor = MaterialTheme.colorScheme.outline  // Outline color when not focused
            )
        )

        Spacer(modifier = Modifier.height(13.dp))

        // Remember Me Checkbox
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(checked = rememberMe, onCheckedChange = { rememberMe = it })
            Spacer(modifier = Modifier.width(3.dp))
            Text(text = "Remember me next time")
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Sign-In Button
        Button(
            onClick = { onLoginSuccess() },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = NavyGreen
            )
        ) {
            Text(text = "Sign In")

        }

        Spacer(modifier = Modifier.height(12.dp))

        // Sign-Up Text
        TextButton(onClick = { navController.navigate("register") }) {
            Text(
                text = buildAnnotatedString {
                    append("Don’t have an account? ")
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            textDecoration = TextDecoration.Underline
                        )
                    ) {
                        append("Sign Up")
                    }
                }
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    val authViewModel = AuthViewModel()
    KisiiAppTheme {
        LoginScreen(
            onLoginSuccess = {},
            navController = navController,
            authViewModel = authViewModel
        )
    }
}