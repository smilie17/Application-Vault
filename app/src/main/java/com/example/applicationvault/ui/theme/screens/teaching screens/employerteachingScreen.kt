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
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun EmployerTeachingScreen(){
    var schoolname by remember { mutableStateOf("") }
    var jobposition by remember { mutableStateOf("") }
    var requirements by remember { mutableStateOf("") }
    Column{
        OutlinedTextField(
            value = schoolname,
            onValueChange = {newSchoolname -> schoolname = newSchoolname},
            label = { Text(text = "Enter school name") },
            placeholder = { Text(text = "Please enter the school name") },
            modifier = Modifier.wrapContentWidth()
        )
        OutlinedTextField(
            value = jobposition,
            onValueChange = {newJobposition -> jobposition = newJobposition},
            label = { Text(text = "Enter job position") },
            placeholder = { Text(text = "Please enter the job position") },
            modifier = Modifier.wrapContentWidth()
        )
        OutlinedTextField(
            value = requirements,
            onValueChange = {newRequirements -> requirements = newRequirements},
            label = { Text(text = "Enter requirements") },
            placeholder = { Text(text = "Please enter the requirements") },
            modifier = Modifier.wrapContentWidth()
        )
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun EmployerTeachingScreenPreview(){
    EmployerTeachingScreen()
}

