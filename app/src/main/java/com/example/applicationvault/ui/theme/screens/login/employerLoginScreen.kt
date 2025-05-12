package com.example.applicationvault.ui.theme.screens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.applicationvault.data.EmployerViewModel

@Composable
fun EmployerLoginScreen(navController: NavController){
    var email by remember { mutableStateOf(value = "") }
    var password by remember { mutableStateOf(value = "") }
    val passwordVisible by remember { mutableStateOf(false) }
    var employerViewModel: EmployerViewModel = viewModel()
    var context = LocalContext.current
    Column {
        Text(text = "Login Here!!",
            color = Color.Black,
            fontFamily = FontFamily.SansSerif,
            fontSize = 30.sp,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.wrapContentWidth()
                .fillMaxWidth()
                .padding(20.dp)
        )
        OutlinedTextField(
            value = email,
            onValueChange = {newEmail -> email = newEmail},
            label = { Text(text = "Enter email") },
            placeholder = { Text(text = "Please enter your email") },
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally),
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email Icon") }

        )
        OutlinedTextField(
            value = password,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            onValueChange = {newPassword -> password = newPassword},
            label = { Text(text = "Enter password") },
            placeholder = { Text(text = "Please enter your password") },
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally),
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password Icon") }
        )
        Button(onClick = {employerViewModel.login(email,password, context ,navController)}, modifier = Modifier.wrapContentWidth().align(Alignment.CenterHorizontally), colors = ButtonDefaults.buttonColors(
            Color.Blue)) { Text(text = "Login") }
    }




}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EmployerScreenPreview(){
    EmployerLoginScreen(rememberNavController())
}