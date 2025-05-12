package com.example.applicationvault.ui.theme.screens.apply

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.applicationvault.navigation.ROUTE_EMPLOYEE_ENGINEERING
import com.example.applicationvault.navigation.ROUTE_EMPLOYEE_HEALTH
import com.example.applicationvault.navigation.ROUTE_EMPLOYEE_IT
import com.example.applicationvault.navigation.ROUTE_EMPLOYEE_MANAGEMENT
import com.example.applicationvault.navigation.ROUTE_EMPLOYEE_MEDIA
import com.example.applicationvault.navigation.ROUTE_EMPLOYEE_TEACHING
import com.example.applicationvault.navigation.ROUTE_ENGINEERING
import com.example.applicationvault.navigation.ROUTE_HEALTH
import com.example.applicationvault.navigation.ROUTE_IT
import com.example.applicationvault.navigation.ROUTE_MANAGEMENT
import com.example.applicationvault.navigation.ROUTE_MEDIA
import com.example.applicationvault.navigation.ROUTE_TEACHING

@Composable
fun Jobs(navController:NavController){
    Column (modifier = Modifier.wrapContentWidth(), horizontalAlignment = Alignment.CenterHorizontally){
        Row {

            Card(
                modifier = Modifier.padding(10.dp).clickable { navController.navigate(
                    ROUTE_EMPLOYEE_IT
                ) },
                shape = RoundedCornerShape(25.dp),
                elevation = CardDefaults.cardElevation(10.dp),
                colors = CardDefaults.cardColors(Color.White)
            )
            {
                Box(
                    modifier = Modifier.height(100.dp)
                        .padding(15.dp),
                    contentAlignment = Alignment.Center
                )
                { Text(text = "IT related jobs", color = Color.Black) }
            }
            Card(
                modifier = Modifier.padding(10.dp).clickable { navController.navigate(
                    ROUTE_EMPLOYEE_TEACHING
                ) },
                shape = RoundedCornerShape(25.dp),
                elevation = CardDefaults.cardElevation(10.dp),
                colors = CardDefaults.cardColors(Color.White)
            )
            {
                Box(
                    modifier = Modifier.height(100.dp)
                        .padding(15.dp),
                    contentAlignment = Alignment.Center
                )
                { Text(text = "Teaching jobs", color = Color.Black) }
            }
            Card(
                modifier = Modifier.padding(10.dp).clickable { navController.navigate(
                    ROUTE_EMPLOYEE_MANAGEMENT
                ) },
                shape = RoundedCornerShape(25.dp),
                elevation = CardDefaults.cardElevation(10.dp),
                colors = CardDefaults.cardColors(Color.White)
            )
            {
                Box(
                    modifier = Modifier.height(100.dp)
                        .padding(10.dp),
                    contentAlignment = Alignment.Center
                )
                { Text(text = "Management jobs", color = Color.Black) }
            }
        }
        Row {
            Card(
                modifier = Modifier.padding(10.dp).clickable { navController.navigate(
                    ROUTE_EMPLOYEE_HEALTH
                ) },
                shape = RoundedCornerShape(30.dp),
                elevation = CardDefaults.cardElevation(10.dp),
                colors = CardDefaults.cardColors(Color.White)
            )
            {
                Box(
                    modifier = Modifier.height(100.dp)
                        .padding(5.dp),
                    contentAlignment = Alignment.Center
                )
                { Text(text = "Health related jobs", color = Color.Black) }
            }
            Card(
                modifier = Modifier.padding(10.dp).clickable { navController.navigate(
                    ROUTE_EMPLOYEE_MEDIA
                ) },
                shape = RoundedCornerShape(30.dp),
                elevation = CardDefaults.cardElevation(10.dp),
                colors = CardDefaults.cardColors(Color.White)
            )
            {
                Box(
                    modifier = Modifier.height(100.dp)
                        .padding(5.dp),
                    contentAlignment = Alignment.Center
                )
                { Text(text = "Media related jobs", color = Color.Black) }
            }
            Card(
                modifier = Modifier.padding(10.dp).clickable { navController.navigate(
                    ROUTE_EMPLOYEE_ENGINEERING
                ) },
                shape = RoundedCornerShape(30.dp),
                elevation = CardDefaults.cardElevation(10.dp),
                colors = CardDefaults.cardColors(Color.White)
            )
            {
                Box(
                    modifier = Modifier.height(100.dp)
                        .padding(5.dp),
                    contentAlignment = Alignment.Center
                )
                { Text(text = "Engineering jobs", color = Color.Black) }
            }

        }
    }
}