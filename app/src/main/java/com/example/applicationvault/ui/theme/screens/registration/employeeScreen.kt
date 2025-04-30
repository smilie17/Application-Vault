package com.example.applicationvault.ui.theme.screens.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.applicationvault.R
import com.example.applicationvault.data.AuthViewModel

@Composable
fun EmployeeregisterScreen(navController: NavController){
    var authViewModel: AuthViewModel = viewModel()
    var firstname by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val passwordVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Column(modifier = Modifier.wrapContentWidth(), horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "Register for available jobs here!!",
            fontSize = 30.sp,
            fontFamily = FontFamily.SansSerif,
            fontStyle = FontStyle.Normal,
            color = Color.Blue,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .wrapContentWidth()
                .fillMaxWidth()
                .padding(20.dp)
                .background(Color.LightGray)
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .wrapContentWidth()
                .fillMaxWidth()
                .height(100.dp))
        Spacer(modifier = Modifier.padding(10.dp))
        OutlinedTextField(
            value = firstname,
            onValueChange = {newFirstname -> firstname = newFirstname},
            label = { Text(text = "Enter your first name") },
            placeholder = { Text(text = "Please enter your first name ") },
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally),
        )
        Spacer(modifier = Modifier.padding(10.dp))
        OutlinedTextField(
            value = lastname,
            onValueChange = {newLastname -> lastname = newLastname},
            label = { Text(text = "Enter your last name") },
            placeholder = { Text(text = "Please enter your last name ") },
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally),
        )
        Spacer(modifier = Modifier.padding(10.dp))
        OutlinedTextField(
            value = email,
            onValueChange = {newEmail -> email = newEmail},
            label = { Text(text = "Enter your email") },
            placeholder = { Text(text = "Please enter your email ") },
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally),
        )
        Spacer(modifier = Modifier.padding(10.dp))
        OutlinedTextField(
            value = password,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            onValueChange = {newPassword -> password = newPassword},
            label = { Text(text = "Enter your password") },
            placeholder = { Text(text = "Please enter your password ") },
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally),
        )
        Button( onClick = {authViewModel.Signup(firstname,lastname,email,password,navController,context)}, modifier = Modifier.
        wrapContentWidth()
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally), colors = ButtonDefaults.buttonColors(Color.Black))
        { Text(text = "Save") }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EmployeeregisterScreenPreview(){
    EmployeeregisterScreen(rememberNavController())
}
