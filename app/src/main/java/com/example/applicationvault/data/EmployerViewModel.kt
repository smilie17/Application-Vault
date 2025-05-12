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
import com.example.applicationvault.models.EmployerModel
import com.example.applicationvault.navigation.ROUTE_EMPLOYER_HOME
import com.example.applicationvault.navigation.ROUTE_EMPLOYER_LOGIN
import com.example.applicationvault.navigation.ROUTE_VIEW_EMPLOYER
import com.example.applicationvault.network.ImgurService
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


class EmployerViewModel: ViewModel() {

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _isLoading = MutableStateFlow(false)
    private val _errorMessage = MutableStateFlow<String?>(null)

    fun signup(
        companyname: String,
        jobname: String,
        email: String,
        password: String,
        requirements: String,
        deadline: String,
        navController: NavController,
        context: Context
    ) {
        if (companyname.isBlank() || jobname.isBlank() || email.isBlank() || password.isBlank() || requirements.isBlank() || deadline.isBlank()) {
            Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_LONG).show()
            return
        }
        _isLoading.value = true
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                _isLoading.value = false
                if (task.isSuccessful) {
                    val employerid = mAuth.currentUser?.uid ?: ""
                    val employerData = EmployerModel(
                        companyname = companyname,
                        jobname = jobname,
                        email = email,
                        requirements = requirements,
                        deadline = deadline, password = password, employerid = employerid
                    )
                    saveEmployerToDatabase(employerid, employerData, navController, context)
                } else {
                    _errorMessage.value = task.exception?.message
                    Toast.makeText(context, "Registration failed", Toast.LENGTH_LONG).show()
                }
            }
    }

    fun saveEmployerToDatabase(
        employerid: String,
        employerData: EmployerModel,
        navController: NavController,
        context: Context
    ) {
        val regRef = FirebaseDatabase.getInstance()
            .getReference("Employers/$employerid")
        regRef.setValue(employerData).addOnCompleteListener { regRef ->
            if (regRef.isSuccessful) {
                Toast.makeText(context, "User Successfully Registered", Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_EMPLOYER_LOGIN)
            } else {
                _errorMessage.value = regRef.exception?.message
                Toast.makeText(context, "Database error", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun login(email: String, password: String, context: Context, navController: NavController) {
        if (email.isBlank() || password.isBlank()) {
            Toast.makeText(context, "Email and password required", Toast.LENGTH_LONG).show()
            return
        }
        _isLoading.value = true

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                _isLoading.value = false
                if (task.isSuccessful) {
                    Toast.makeText(context, "Login successful", Toast.LENGTH_LONG).show()
                    navController.navigate(ROUTE_EMPLOYER_HOME)
                } else {
                    _errorMessage.value = task.exception?.message
                    Toast.makeText(context, "Login failed", Toast.LENGTH_LONG).show()
                }
            }
    }

    fun viewEmployers(
        employer: MutableState<EmployerModel>,
        employers: SnapshotStateList<EmployerModel>,
        context: Context
    ): SnapshotStateList<EmployerModel> {
        val ref = FirebaseDatabase.getInstance().getReference("Employers")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                employers.clear()
                for (snap in snapshot.children) {
                    val value = snap.getValue(EmployerModel::class.java)
                    value?.let {
                        employers.add(it)
                    }
                }
                if (employers.isNotEmpty()) {
                    employer.value = employers.first()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    context,
                    "Failed to fetch employers: ${error.message}",
                    Toast.LENGTH_SHORT
                ).show()

            }
        })

        return employers
    }

    fun updateEmployer(
        context: Context, navController: NavController,
        companyname: String, jobname: String,
        email: String, password: String, employerid: String, requirements: String, deadline: String
    ) {
        val databaseReference = FirebaseDatabase.getInstance()
            .getReference("Employers/$employerid")
        val updatedEmployer = EmployerModel(
            companyname, jobname,
            email, password, employerid, requirements, deadline
        )

        databaseReference.setValue(updatedEmployer)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    Toast.makeText(context, "Employer Updated Successfully", Toast.LENGTH_LONG)
                        .show()
                    navController.navigate(ROUTE_VIEW_EMPLOYER)
                } else {

                    Toast.makeText(context, "Employer update failed", Toast.LENGTH_LONG).show()
                }
            }
    }

    fun deleteEmployer(
        context: Context, employerid: String,
        navController: NavController
    ) {
        AlertDialog.Builder(context)
            .setTitle("Delete Employer")
            .setMessage("Are you sure you want to delete this employer?")
            .setPositiveButton("Yes") { _, _ ->
                val databaseReference = FirebaseDatabase.getInstance()
                    .getReference("Employers/$employerid")
                databaseReference.removeValue().addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        Toast.makeText(context, "Employer deleted Successfully", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        Toast.makeText(context, "Employer not deleted", Toast.LENGTH_LONG).show()
                    }
                }
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private val database = FirebaseDatabase.getInstance().reference.child("Employers")

    private fun getImgurService(): ImgurService {
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

    private fun getFileFromUri(context: Context, uri: Uri): File? {
        return try {
            val inputStream = context.contentResolver
                .openInputStream(uri)
            val file = File.createTempFile("temp_image", ".jpg", context.cacheDir)
            file.outputStream().use { output ->
                inputStream?.copyTo(output)
            }
            file
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun uploadEmployerWithImage(
        uri: Uri,
        context: Context,
        companyname: String,
        jobname: String,
        email: String,
        password: String,
        requirements: String,
        deadline: String,
        navController: NavController
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val file = getFileFromUri(context, uri)
                if (file == null) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "Failed to process image", Toast.LENGTH_SHORT)
                            .show()
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

                    val employerid = database.push().key ?: ""
                    val employer = EmployerModel(
                        companyname,
                        jobname,
                        email,
                        password,
                        requirements,
                        deadline,
                        imageUrl,
                        employerid
                    )

                    database.child(employerid).setValue(employer)
                        .addOnSuccessListener {
                            viewModelScope.launch {
                                withContext(Dispatchers.Main) {
                                    Toast.makeText(
                                        context,
                                        "Employer saved successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    navController.navigate(ROUTE_VIEW_EMPLOYER)
                                }
                            }
                        }.addOnFailureListener {
                            viewModelScope.launch {
                                withContext(Dispatchers.Main) {
                                    Toast.makeText(
                                        context,
                                        "Failed to save employer",
                                        Toast.LENGTH_SHORT
                                    ).show()
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
                    Toast.makeText(context, "Exception: ${e.localizedMessage}", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }
}


