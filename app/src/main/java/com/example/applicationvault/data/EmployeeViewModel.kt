package com.example.applicationvault.data

import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.applicationvault.network.ImgurService
import com.example.applicationvault.models.EmployeeModel
import com.example.applicationvault.models.EmployerModel
import com.example.applicationvault.navigation.ROUTE_EMPLOYEE_LOGIN
import com.example.applicationvault.navigation.ROUTE_EMPLOYER_HOME
import com.example.applicationvault.navigation.ROUTE_EMPLOYEE_HOME
import com.example.applicationvault.navigation.ROUTE_VIEW_EMPLOYEE
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File


class EmployeeViewModel: ViewModel() {
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
                        email = email, password = password, employeeid = userId,
                    )
                    saveEmployeeToDatabase(userId, userData, navController, context)
                } else {
                    _errorMessage.value = task.exception?.message

                    Toast.makeText(context, "Registration failed", Toast.LENGTH_LONG).show()
                }
            }
    }
    fun saveEmployeeToDatabase(
        employeeid: String,
        userData: EmployeeModel,
        navController: NavController,
        context: Context
    ) {
        val regRef = FirebaseDatabase.getInstance()
            .getReference("Employees/$employeeid")
        regRef.setValue(userData).addOnCompleteListener { regRef ->
            if (regRef.isSuccessful) {
                Toast.makeText(context, "User Successfully Registered", Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_EMPLOYEE_LOGIN)
            } else {
                _errorMessage.value = regRef.exception?.message
                Toast.makeText(context, "Database error", Toast.LENGTH_LONG).show()
            }
        }
    }
    fun Login(email:String,password: String,navController: NavController,context: Context){
        if (email.isBlank()|| password.isBlank()){
            Toast.makeText(context,"Email and password required",Toast.LENGTH_LONG).show()
            return
        }
        _isLoading.value = true

        mAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener{ task ->
                _isLoading.value = false
                if (task.isSuccessful){
                    Toast.makeText(context,"Login successful",Toast.LENGTH_LONG).show()
                    navController.navigate(ROUTE_EMPLOYEE_HOME)
                }else{
                    _errorMessage.value = task.exception?.message
                    Toast.makeText(context,"Login failed",Toast.LENGTH_LONG).show()
                }
            }
    }
    fun viewEmployees(
        employee: MutableState<EmployeeModel>,
        employees: SnapshotStateList<EmployeeModel>,
        context: Context
    ): SnapshotStateList<EmployeeModel> {
        val ref = FirebaseDatabase.getInstance().getReference("Employees")

        ref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                employees.clear()
                for (snap in snapshot.children) {
                    val value = snap.getValue(EmployeeModel::class.java)
                    value?.let {
                        employees.add(it)
                    }
                }
                if (employees.isNotEmpty()) {
                    employee.value = employees.first()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Failed to fetch employees: ${error.message}", Toast.LENGTH_SHORT).show()

            }
        })

        return employees
    }
    fun updateEmployee(context: Context, navController: NavController,
                      firstname: String,lastname: String,
                      email: String,password: String,employeeid: String){
        val databaseReference = FirebaseDatabase.getInstance()
            .getReference("Employees/$employeeid")
        val updatedEmployee = EmployeeModel(firstname,lastname,
            email,password,employeeid)

        databaseReference.setValue(updatedEmployee)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){

                    Toast.makeText(context,"Employee Updated Successfully", Toast.LENGTH_LONG).show()
                    navController.navigate(ROUTE_VIEW_EMPLOYEE)
                }else{

                    Toast.makeText(context,"Employee update failed", Toast.LENGTH_LONG).show()
                }
            }
    }
    fun deleteEmployee(context: Context,employeeid:String,
                      navController: NavController){
        AlertDialog.Builder(context)
            .setTitle("Delete Employee")
            .setMessage("Are you sure you want to delete this employee?")
            .setPositiveButton("Yes"){ _, _ ->
                val databaseReference = FirebaseDatabase.getInstance()
                    .getReference("Employees/$employeeid")
                databaseReference.removeValue().addOnCompleteListener {
                        task ->
                    if (task.isSuccessful){

                        Toast.makeText(context,"Employee deleted Successfully",Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(context,"Employee not deleted",Toast.LENGTH_LONG).show()
                    }
                }
            }
            .setNegativeButton("No"){ dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
    private val database = FirebaseDatabase.getInstance().reference.child("Employees")

    private fun getImgurService():ImgurService{
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY


        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.imgur.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ImgurService::class.java)
    }
    private fun getFileFromUri(context: Context,uri: Uri): File?{
        return try {
            val inputStream = context.contentResolver
                .openInputStream(uri)
            val file = File.createTempFile("temp_image",".jpg",context.cacheDir)
            file.outputStream().use {output ->
                inputStream?.copyTo(output)
            }
            file
        }catch (e: Exception){
            e.printStackTrace()
            null
        }
    }
    fun uploadEmployeeWithImage(
        uri: Uri,
        context: Context,
        firstname: String,
        lastname: String,
        email: String,
        password: String,
        navController: NavController
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val file = getFileFromUri(context, uri)
                if (file == null) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "Failed to process image", Toast.LENGTH_SHORT).show()
                    }
                    return@launch
                }

                val reqFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                val body = MultipartBody.Part.createFormData("image", file.name, reqFile)

                val response = getImgurService().uploadImage(
                    body,
                    "Client-ID 11f59c4330b42f5"
                )

                if (response.isSuccessful) {
                    val imageUrl = response.body()?.data?.link ?: ""

                    val employeeid = database.push().key ?: ""
                    val employee = EmployeeModel(
                        firstname , lastname, email , password , employeeid , imageUrl                    )

                    database.child(employeeid).setValue(employee)
                        .addOnSuccessListener {
                            viewModelScope.launch {
                                withContext(Dispatchers.Main) {
                                    Toast.makeText(context, "Employee saved successfully", Toast.LENGTH_SHORT).show()
                                    navController.navigate(ROUTE_VIEW_EMPLOYEE)

                                }
                            }
                        }.addOnFailureListener {
                            viewModelScope.launch {
                                withContext(Dispatchers.Main) {
                                    Toast.makeText(context, "Failed to save employee", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }

                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "Upload error", Toast.LENGTH_SHORT).show()
                    }
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Exception: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    fun update(context: Context, navController: NavController, firstname: String, lastname: String,email: String, password: String, employeeid: String) {

    }
    fun logout(navController: NavController,context: Context){
        mAuth.signOut()
        Toast.makeText(context,"User successfully logged out",Toast.LENGTH_LONG).show()
        navController.navigate(ROUTE_EMPLOYEE_LOGIN)}

}