package com.example.applicationvault.ui.theme.screens.healthscreens

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
fun EmployerHealthScreen(){
    var hospitalname by remember { mutableStateOf("") }
    var jobposition by remember { mutableStateOf("") }
    var requirements by remember { mutableStateOf("") }
    Column {
        OutlinedTextField(
            value = hospitalname,
            onValueChange = {newHospitalname -> hospitalname = newHospitalname},
            label = { Text(text = "Enter company name") },
            placeholder = { Text(text = "Please enter the company name") },
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

@Preview (showBackground = true, showSystemUi = true)
@Composable
fun EmployeeHealthScreenPreview(){
    EmployerHealthScreen()
}