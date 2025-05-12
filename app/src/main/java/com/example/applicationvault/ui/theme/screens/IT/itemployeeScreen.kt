package com.example.applicationvault.ui.theme.screens.IT

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.applicationvault.R
import com.example.applicationvault.data.EmployeeViewModel


@Composable
fun ItEmployeeScreen(navController: NavController) {
    val imageUri = rememberSaveable() { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent())
    { uri: Uri? ->
        uri?.let { imageUri.value = it }
    }
    var context = LocalContext.current
    val employeeViewModel: EmployeeViewModel = viewModel()
    var firstname by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var qualifications by remember { mutableStateOf("") }
    val passwordVisible by remember { mutableStateOf(false) }
    Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,){
        Card(
            shape = CircleShape,
            modifier = Modifier.padding(10.dp).size(200.dp)
        ) {
            AsyncImage(
                model = imageUri.value ?: R.drawable.ic_person,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(200.dp).clickable { launcher.launch("image/") }
            )}
            OutlinedTextField(
                value = firstname,
                onValueChange = { newFirstname -> firstname = newFirstname },
                label = { Text(text = "Enter firstname name") },
                placeholder = { Text(text = "Please enterfirstname") },
                modifier = Modifier.wrapContentWidth()
            )
            OutlinedTextField(
                value = lastname,
                onValueChange = { newLastname -> lastname = newLastname },
                label = { Text(text = "Enter job position") },
                placeholder = { Text(text = "Please enter the job position") },
                modifier = Modifier.wrapContentWidth()
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
                value = qualifications,
                onValueChange = { newQualifications -> qualifications = newQualifications },
                label = { Text(text = "Enter qualifications") },
                singleLine = false,
                placeholder = { Text(text = "Please enter your qualifications") },
                modifier = Modifier.wrapContentWidth()
            )
            Button(
                onClick = {
                    imageUri.value?.let {
                        employeeViewModel.uploadEmployeeWithImage(
                            it,
                            context,
                            firstname,
                            lastname,
                            email,
                            password,
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
fun ItEmployeeScreenPreview(){
    ItEmployeeScreen(rememberNavController())
}