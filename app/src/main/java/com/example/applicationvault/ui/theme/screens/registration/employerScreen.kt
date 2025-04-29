package com.example.applicationvault.ui.theme.screens.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.applicationvault.R

@Composable
fun EmployerregistrationScreen(){
    var jobname by remember { mutableStateOf("") }
    var requirements by remember { mutableStateOf("") }
    var deadline by remember { mutableStateOf("") }
    var companyname by remember { mutableStateOf("") }
    Column ( modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "Register your available job here!!",
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
        Spacer(modifier = Modifier.padding(20.dp))
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .wrapContentWidth()
                .fillMaxWidth()
                .height(100.dp))
        Spacer(modifier = Modifier.padding(20.dp))
        OutlinedTextField(
            value = companyname,
            onValueChange = {newCompanyname -> companyname = newCompanyname},
            label = { Text(text = "Enter companyname") },
            placeholder = { Text(text = "Please enter the name of the company") },
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally),
        )
        Spacer(modifier = Modifier.padding(20.dp))
        OutlinedTextField(
            value = jobname,
            onValueChange = {newJobname -> jobname = newJobname},
            label = { Text(text = "Enter jobname") },
            placeholder = { Text(text = "Please enter the available job") },
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally),
        )
        Spacer(modifier = Modifier.padding(20.dp))
        OutlinedTextField(
            value = requirements,
            onValueChange = {newRequirements -> requirements = newRequirements},
            label = { Text(text = "Enter requirements") },
            placeholder = { Text(text = "Please enter the job requirements") },
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally),
        )
        Spacer(modifier = Modifier.padding(20.dp))
        OutlinedTextField(
            value = deadline,
            onValueChange = {newDeadline -> deadline = newDeadline},
            label = { Text(text = "Enter job deadline") },
            placeholder = { Text(text = "Please enter the application deadline") },
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally),
        )
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EmployerregistarionScreenPreview(){
    EmployerregistrationScreen()
}