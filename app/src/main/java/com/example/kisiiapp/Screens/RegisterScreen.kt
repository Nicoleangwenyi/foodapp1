package com.example.kisiiapp.Screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.kisiiapp.AuthState
import com.example.kisiiapp.AuthViewModel
import com.example.kisiiapp.R
import com.example.kisiiapp.ui.theme.KisiiAppTheme
import com.example.kisiiapp.ui.theme.NavyGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController, authViewModel: AuthViewModel) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var reminderChecked by remember { mutableStateOf(false) }

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value){
        when(authState.value){
            is AuthState.Authenticated -> navController.navigate("home")
            is AuthState.Error -> Toast.makeText(context,
                (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT).show()
            else ->Unit
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
        horizontalAlignment = Alignment.Start
    ) {


        // Illustration/Image
        Image(
            painter = painterResource(id = R.drawable.register), // Replace with your drawable resource
            contentDescription = "Login Illustration",
            modifier = Modifier
                .height(300.dp)
                .padding(bottom = 5.dp)
                .align(Alignment.CenterHorizontally)
        )

        // Title
        Text(
            text = "Create an Account",
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 38.sp,
            color = NavyGreen
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Username Field
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Username Icon") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(40.dp), // Rounded corners
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,   // Primary color when focused
                unfocusedBorderColor = MaterialTheme.colorScheme.outline  // Outline color when not focused
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email Icon") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(40.dp), // Rounded corners
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,   // Primary color when focused
                unfocusedBorderColor = MaterialTheme.colorScheme.outline  // Outline color when not focused
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Mobile Number Field
        OutlinedTextField(
            value = mobileNumber,
            onValueChange = { mobileNumber = it },
            label = { Text("Mobile Number") },
            leadingIcon = { Icon(Icons.Default.Phone, contentDescription = "Phone Icon") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(40.dp), // Rounded corners
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,   // Primary color when focused
                unfocusedBorderColor = MaterialTheme.colorScheme.outline  // Outline color when not focused
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Password Field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password Icon") },
            trailingIcon = {
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(
                        painter = painterResource(
                            id = if (isPasswordVisible) R.drawable.visible else R.drawable.novisible
                        ),
                        contentDescription = "Toggle Password Visibility",
                        modifier = Modifier.size(20.dp)
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

        Spacer(modifier = Modifier.height(10.dp))

        // Reminder Me Toggle
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                checked = reminderChecked,
                onCheckedChange = { reminderChecked = it },
                colors = CheckboxDefaults.colors(checkmarkColor = MaterialTheme.colorScheme.primary)
            )
            Text(
                text = "By continuing you accept our Privacy Policy and Term of Use",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Sign Up Button
        Button(
            onClick = {
                authViewModel.signup(username, email, password)
                      },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = NavyGreen
            )
        ) {
            Text(text = "Register")
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Navigation to Sign In
        TextButton(onClick = { navController.navigate("login") }) {
            Text(
                text = buildAnnotatedString {
                    append("Already have an account? ")
                    withStyle(
                        style = SpanStyle(fontWeight = FontWeight.Bold)
                    ) {
                        append("Sign In")
                    }
                },
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    KisiiAppTheme {
        val navController = rememberNavController()
        val authViewModel = AuthViewModel()
        RegisterScreen(navController = navController, authViewModel = authViewModel)
    }
}
