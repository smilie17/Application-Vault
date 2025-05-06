package com.example.applicationvault.ui.theme.screens.teaching

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun EmployeeTeachingScreen(){
    var firstname by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var qualifications by remember { mutableStateOf("") }
    val passwordVisible by remember { mutableStateOf(false) }
    Column {
        OutlinedTextField(
            value = firstname,
            onValueChange = {newFirstname -> firstname= newFirstname},
            label = { Text(text = "Enter firstname name") },
            placeholder = { Text(text = "Please enterfirstname") },
            modifier = Modifier.wrapContentWidth()
        )
        OutlinedTextField(
            value =lastname ,
            onValueChange = {newLastname -> lastname = newLastname},
            label = { Text(text = "Enter job position") },
            placeholder = { Text(text = "Please enter the job position") },
            modifier = Modifier.wrapContentWidth()
        )
        OutlinedTextField(
            value = email,
            onValueChange = {newEmail -> email=newEmail},
            label = { Text(text = "Enter email") },
            placeholder = { Text(text = "Please enter email") },
            modifier = Modifier.wrapContentWidth()
        )
        OutlinedTextField(
            value = password,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            onValueChange = {newPassword -> password = newPassword},
            label = { Text(text = "Enter password") },
            placeholder = { Text(text = "Please enter the password") },
            modifier = Modifier.wrapContentWidth()
        )
        OutlinedTextField(
            value = qualifications,
            onValueChange = {newQualifications -> qualifications = newQualifications},
            label = { Text(text = "Enter password") },
            singleLine = false,
            placeholder = { Text(text = "Please enter the password") },
            modifier = Modifier.wrapContentWidth()
        )


    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EmployeeTeachingScreenPreview(){
    EmployeeTeachingScreen()
}
