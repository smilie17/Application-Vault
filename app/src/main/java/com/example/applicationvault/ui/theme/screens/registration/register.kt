package com.example.applicationvault.ui.theme.screens.registration

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.applicationvault.navigation.ROUTE_EMPLOYEE_REGISTER
import com.example.applicationvault.navigation.ROUTE_EMPLOYER_REGISTER
import com.example.applicationvault.navigation.ROUTE_REGISTER

@Composable
fun RegisterScreen (navController: NavController){
    Column (modifier = Modifier.fillMaxSize().padding(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally){
        Spacer(modifier = Modifier.padding(50.dp))
            Card(
                modifier = Modifier.padding(10.dp).clickable { navController.navigate(
                    ROUTE_EMPLOYER_REGISTER) },
                shape = RoundedCornerShape(50.dp),
                elevation = CardDefaults.cardElevation(10.dp),
                colors = CardDefaults.cardColors(Color.White)
            )
            {
                Box(
                    modifier = Modifier.height(100.dp)
                        .padding(25.dp),
                    contentAlignment = Alignment.Center
                )
                { Text(text = "Register as Employer", color = Color.Black) }
            }
        Spacer(modifier = Modifier.padding(50.dp))
        Card(
            modifier = Modifier.padding(10.dp).clickable { navController.navigate(
                ROUTE_EMPLOYEE_REGISTER) },
            shape = RoundedCornerShape(50.dp),
            elevation = CardDefaults.cardElevation(10.dp),
            colors = CardDefaults.cardColors(Color.White)
        )
        {
            Box(
                modifier = Modifier.height(100.dp)
                    .padding(25.dp),
                contentAlignment = Alignment.Center
            )
            { Text(text = "Register as employee", color = Color.Black) }
        }
        }
}




@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview(){
    RegisterScreen(rememberNavController())
}