package com.example.applicationvault.ui.theme.screens.updates

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.applicationvault.models.EmployeeModel
import com.example.applicationvault.data.EmployeeViewModel
import com.example.applicationvault.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Composable
fun employeeupdateScreen(navController: NavController,employeeid: String){
    val imageUri = rememberSaveable() { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? -> uri?.let { imageUri.value=it } }
    var firstname by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val employeeViewModel: EmployeeViewModel= viewModel()
    val context= LocalContext.current
    val currentDataRef  = FirebaseDatabase.getInstance()
        .getReference().child("Employees/$employeeid")

    DisposableEffect(Unit){
        val listener = object  : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val student = snapshot.getValue(EmployeeModel::class.java)
                student?.let {
                    firstname = it.firstname
                    lastname= it.lastname
                    email = it.email
                    password = it.password
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context,error.message,Toast.LENGTH_LONG).show()
            }
        }
        currentDataRef.addValueEventListener(listener)
        onDispose { currentDataRef.removeEventListener(listener) }
    }
    Column (modifier = Modifier.padding(10.dp)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally){
        Box(modifier = Modifier.fillMaxWidth()
            .background(Color.Red).padding(16.dp)){ Text(text = "UPDATE EMPLOYEE",
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.fillMaxWidth()) }
        Card(shape = CircleShape, modifier = Modifier.padding(10.dp).size(200.dp)) {
            AsyncImage(model = imageUri.value ?: R.drawable.ic_person,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(200.dp)
                    .clickable { launcher.launch("image/*") })
        }
        Text(text = "Upload Picture Here")

        OutlinedTextField(value = firstname,
            onValueChange = {newName->firstname=newName},
            label = { Text(text = "Enter firstname") },
            placeholder = { Text(text = "PLease enter first name") },
            modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = lastname,
            onValueChange = {newLastname->lastname=newLastname},
            label = { Text(text = "Enter lastname") },
            placeholder = { Text(text = "PLease enter lastname") },
            modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = email,
            onValueChange = {newEmail->email=newEmail},
            label = { Text(text = "Enter student email") },
            placeholder = { Text(text = "PLease enter email") },
            modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = password,
            onValueChange = {newPassword->password=newPassword},
            label = { Text(text = "Enter password") },
            placeholder = { Text(text = "PLease enter password") },
            modifier = Modifier.fillMaxWidth().height(150.dp), singleLine = false)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { /*TODO: Handle Go Back*/ }) {
                Text(text = "GO BACK")
            }
            Spacer(modifier = Modifier.width(20.dp))
            Button(onClick = {
                employeeViewModel.updateEmployee(
                    context = context,
                    navController = navController,
                    firstname = firstname,
                    lastname = lastname,
                    email = email,
                    password = password,
                    employeeid = employeeid,
                )
            }) {
                Text(text = "UPDATE")
            }

        }
    }
}