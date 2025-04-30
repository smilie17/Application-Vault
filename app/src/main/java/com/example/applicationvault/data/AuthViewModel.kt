package com.example.applicationvault.data

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.applicationvault.models.EmployeeModel
import com.example.applicationvault.models.EmployerModel
import com.example.applicationvault.navigation.ROUTE_LOGIN
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.MutableStateFlow

class AuthViewModel: ViewModel(){
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _isLoading = MutableStateFlow(false)
    private val _errorMessage = MutableStateFlow<String?>(null)

    fun Signup(
        firstname: String, lastname: String, email: String, password: String,
        navController: NavController,
        context: Context
    ) {
        if (firstname.isBlank() || lastname.isBlank() || email.isBlank() || password.isBlank()) {
            Toast.makeText(context, "please fill all the fields", Toast.LENGTH_LONG).show()
            return
        }
        _isLoading.value = true
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                _isLoading.value = false
                if (task.isSuccessful) {
                    val userId = mAuth.currentUser?.uid ?: ""
                    val userData = EmployeeModel(
                        firstname = firstname, lastname = lastname,
                        email = email, password = password, employeeid = userId
                    )
                    saveUserToDatabase(userId, userData, navController, context)
                } else {
                    _errorMessage.value = task.exception?.message

                    Toast.makeText(context, "Registration failed", Toast.LENGTH_LONG).show()
                }
            }
    }
    fun signup(
        companyname: String,jobname:String,requirements:String,deadline:String,email: String,password: String,
        navController: NavController,
        context: Context
    ){
        if (companyname.isBlank() || jobname.isBlank() || requirements.isBlank() || deadline.isBlank() || email.isBlank() || password.isBlank()) {
            Toast.makeText(context, "please fill all the fields", Toast.LENGTH_LONG).show()
            return
            _isLoading.value = true
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    _isLoading.value = false
                    if (task.isSuccessful) {
                        val userId = mAuth.currentUser?.uid ?: ""
                        val userData = EmployerModel(
                            companyname = companyname, jobname = jobname,
                            email = email, password = password, employerid = userId
                        )
                        saveUserToDatabase(userId, userData, navController, context)
                    } else {
                        _errorMessage.value = task.exception?.message

                        Toast.makeText(context, "Registration failed", Toast.LENGTH_LONG).show()
                    }
                }

    }
    fun saveUserToDatabase(
        userId: String,
        userData: EmployeeModel,
        navController: NavController,
        context: Context
    ) {
        val regRef = FirebaseDatabase.getInstance()
            .getReference("Users/$userId")
        regRef.setValue(userData).addOnCompleteListener { regRef ->
            if (regRef.isSuccessful) {
                Toast.makeText(context, "User Successfully Registered", Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_LOGIN)
            } else {
                _errorMessage.value = regRef.exception?.message
                Toast.makeText(context, "Database error", Toast.LENGTH_LONG).show()
            }
        }
    }

}