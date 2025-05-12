package com.example.applicationvault.ui.theme.screens.media

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.applicationvault.R
import com.example.applicationvault.data.EmployerViewModel

@Composable
fun MediaScreen(navController: NavController){
    var deadline by remember { mutableStateOf("") }
    val imageUri = rememberSaveable() { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent())
    { uri: Uri? ->
        uri?.let { imageUri.value = it }
    }
    var jobposition by remember { mutableStateOf("") }
    var companyname by remember { mutableStateOf("") }
    var requirements by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val passwordVisible by remember { mutableStateOf(false) }
    var employerViewModel: EmployerViewModel = viewModel()
    var context = LocalContext.current


    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = "Logo",
            modifier = Modifier
                .wrapContentWidth()
                .fillMaxWidth()
                .height(100.dp)

        )
        OutlinedTextField(
            value = jobposition,
            onValueChange = {newJobposition -> jobposition = newJobposition},
            label = { Text(text = "Please enter the available job") },
            placeholder = { Text(text = "Enter the available job e.g. civil engineer") },
            modifier = Modifier.wrapContentWidth()
                .align(alignment = Alignment.CenterHorizontally)
        )
        OutlinedTextField(
            value = companyname,
            onValueChange = {newCompanyname -> companyname = newCompanyname},
            label = { Text(text = "Please enter the company name") },
            placeholder = { Text(text = "Enter the company name") },
            modifier = Modifier.wrapContentWidth()
                .align(alignment = Alignment.CenterHorizontally)
        )
        OutlinedTextField(
            value = email,
            onValueChange = { newEmail -> email = newEmail },
            label = { Text(text = "Enter email") },
            placeholder = { Text(text = "Please enter email") },
            modifier = Modifier.wrapContentWidth()
        )
        OutlinedTextField(
            value = password,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            onValueChange = { newPassword -> password = newPassword },
            label = { Text(text = "Enter password") },
            placeholder = { Text(text = "Please enter the password") },
            modifier = Modifier.wrapContentWidth()
        )
        OutlinedTextField(
            value = requirements,
            onValueChange = {newRequirements -> requirements = newRequirements},
            label = { Text(text = "Please enter the job requirements") },
            placeholder = { Text(text = "Enter the job requirements") },
            singleLine = false,
            modifier = Modifier.wrapContentWidth()
                .align(alignment = Alignment.CenterHorizontally)
        )
        Button(
            onClick = {
                imageUri.value?.let {
                    employerViewModel.uploadEmployerWithImage(
                        it,
                        context,
                        jobposition,
                        companyname,
                        email,
                        deadline ,
                        password,
                        requirements,
                        navController
                    )
                } ?: Toast.makeText(context, "Please pick an image", Toast.LENGTH_LONG).show()
            },
            modifier = Modifier,
            colors = ButtonDefaults.buttonColors(Color.Green)
        ) { Text(text = "Save") }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MediaScreenPreview(){
    MediaScreen(rememberNavController())
}