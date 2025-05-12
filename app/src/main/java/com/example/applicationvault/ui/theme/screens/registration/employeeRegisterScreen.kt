package com.example.applicationvault.ui.theme.screens.registration

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
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
import coil.compose.AsyncImage
import com.example.applicationvault.R
import com.example.applicationvault.data.EmployeeViewModel
import com.example.applicationvault.navigation.ROUTE_EMPLOYEE_LOGIN

@Composable
fun EmployeeregisterScreen(navController: NavController){
    val imageUri = rememberSaveable() { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent())
    {uri: Uri? ->
        uri?.let {imageUri.value=it} }
    val employeeViewModel: EmployeeViewModel = viewModel()
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

        Spacer(modifier = Modifier.padding(5.dp))
        OutlinedTextField(
            value = firstname,
            onValueChange = {newFirstname -> firstname = newFirstname},
            label = { Text(text = "Enter your first name") },
            placeholder = { Text(text = "Please enter your first name ") },
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally),
        )
        Spacer(modifier = Modifier.padding(5.dp))
        OutlinedTextField(
            value = lastname,
            onValueChange = {newLastname -> lastname = newLastname},
            label = { Text(text = "Enter your last name") },
            placeholder = { Text(text = "Please enter your last name ") },
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally),
        )
        Spacer(modifier = Modifier.padding(5.dp))
        OutlinedTextField(
            value = email,
            onValueChange = {newEmail -> email = newEmail},
            label = { Text(text = "Enter your email") },
            placeholder = { Text(text = "Please enter your email ") },
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally),
        )
        Spacer(modifier = Modifier.padding(5.dp))
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
        Button( onClick ={
            employeeViewModel.Signup(firstname,lastname,email,password, navController,context)}, modifier = Modifier.
        wrapContentWidth()
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally), colors = ButtonDefaults.buttonColors(Color.Black))
        { Text(text = "Save") }
        Text(text = buildAnnotatedString { append("If already registered,Login Here") },
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally)
                .clickable {
                    navController.navigate(ROUTE_EMPLOYEE_LOGIN)
                })
//        Button(onClick = {
//
//        }, modifier = Modifier, colors = ButtonDefaults.buttonColors(Color.Green)) { Text(text = "Save") }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EmployeeregisterScreenPreview(){
    EmployeeregisterScreen(rememberNavController())
}
