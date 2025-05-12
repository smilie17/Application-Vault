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
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.applicationvault.data.EmployerViewModel
import com.example.applicationvault.navigation.ROUTE_EMPLOYER_LOGIN

@Composable
fun EmployerregistrationScreen(navController: NavController){
    val imageUri = rememberSaveable() { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent())
    {uri: Uri? ->
        uri?.let {imageUri.value=it} }
    var jobname by remember { mutableStateOf("") }
    var requirements by remember { mutableStateOf("") }
    var deadline by remember { mutableStateOf("") }
    var companyname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
  var employerViewModel: EmployerViewModel = viewModel()
    val passwordVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Column ( modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Register your available job here!!",
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
        Spacer(modifier = Modifier.padding(3.dp))
        OutlinedTextField(
            value = companyname,
            onValueChange = { newCompanyname -> companyname = newCompanyname },
            label = { Text(text = "Enter companyname") },
            placeholder = { Text(text = "Please enter the name of the company") },
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally),
        )
        Spacer(modifier = Modifier.padding(3.dp))
        OutlinedTextField(
            value = jobname,
            onValueChange = { newJobname -> jobname = newJobname },
            label = { Text(text = "Enter jobname") },
            placeholder = { Text(text = "Please enter the available job") },
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally),
        )
        OutlinedTextField(
            value = email,
            onValueChange = { newEmail -> email = newEmail },
            label = { Text(text = "Enter email") },
            placeholder = { Text(text = "Please enter your email") },
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally),
        )
        OutlinedTextField(
            value = password,
            onValueChange = { newPassword -> password = newPassword },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            label = { Text(text = "Enter password") },
            placeholder = { Text(text = "Please enter your password") },
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally),
        )

        OutlinedTextField(
            value = requirements,
            onValueChange = { newRequirements -> requirements = newRequirements },
            label = { Text(text = "Enter requirements") },
            singleLine = false,
            placeholder = { Text(text = "Please enter the job requirements") },
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally),
        )
        OutlinedTextField(
            value = deadline,
            onValueChange = { newDeadline -> deadline = newDeadline },
            label = { Text(text = "Enter job deadline") },
            placeholder = { Text(text = "Please enter the application deadline") },
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally),
        )
        Button(onClick = {employerViewModel.signup(companyname,jobname,email,requirements,password,deadline,navController, context)})
        { Text(text = "Submit") }
        Text(text = buildAnnotatedString { append("If already registered,Login Here") },
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally)
                .clickable {
                    navController.navigate(ROUTE_EMPLOYER_LOGIN)
                })
//        Button(onClick = {
//            imageUri.value?.let {
//                employerViewModel.uploadEmployerWithImage(it,context,companyname, jobname, email, password, requirements, deadline, navController)    }?: Toast.makeText(context,"Please pick an image", Toast.LENGTH_LONG).show()
//        }, modifier = Modifier, colors = ButtonDefaults.buttonColors(Color.Green)) { Text(text = "Save") }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EmployerregistarionScreenPreview(){
    EmployerregistrationScreen(rememberNavController())
}