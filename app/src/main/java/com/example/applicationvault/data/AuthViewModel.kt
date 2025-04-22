package com.example.applicationvault.data

import android.content.Context
import android.widget.Toast

fun login(email: String,password: String,navController: NavController,context: Context){
    if (email.isBlank()|| password.isBlank()){
        Toast.makeText(context,"Email and password required", Toast.LENGTH_LONG).show()
        return
    }
    _isLoading.value = true

    mAuth.signInWithEmailAndPassword(email,password)
        .addOnCompleteListener { task ->
            _isLoading.value = false
            if (task.isSuccessful){

                Toast.makeText(context,"User Successfully logged in", Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_HOME)
            }else{
                _errorMessage.value = task.exception?.message
                Toast.makeText(context,"Login failed", Toast.LENGTH_LONG).show()
            }
        }
}