package com.example.applicationvault.ui.theme.screens.home

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.applicationvault.R
import com.example.applicationvault.data.EmployeeViewModel
import com.example.applicationvault.navigation.ROUTE_EMPLOYER_REGISTER
import com.example.applicationvault.navigation.ROUTE_ENGINEERING
import com.example.applicationvault.navigation.ROUTE_HEALTH
import com.example.applicationvault.navigation.ROUTE_IT
import com.example.applicationvault.navigation.ROUTE_MANAGEMENT
import com.example.applicationvault.navigation.ROUTE_MEDIA
import com.example.applicationvault.navigation.ROUTE_TEACHING

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployerHomeScreen(navController: NavController){
    var employeeViewModel: EmployeeViewModel = viewModel()
    val selectedItem = remember { mutableStateOf(0) }
    val context = LocalContext.current
    Scaffold (
        bottomBar = { NavigationBar(containerColor = Color.Black){
            NavigationBarItem(
                selected =selectedItem.value == 0,
                onClick = {selectedItem.value = 0
                    val intent= Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto: info@eemobilis.com")
                        putExtra(Intent.EXTRA_SUBJECT,"Inquiry")
                        putExtra(Intent.EXTRA_TEXT,"Hello,confirm to me my total fee balance")
                    }
                    context.startActivity(intent)},
                icon = { Icon(Icons.Filled.Email, contentDescription = "Email") },
                label = { Text(text = "Email") },
                alwaysShowLabel = true
            )
            NavigationBarItem(
                selected =selectedItem.value == 1,
                onClick = {selectedItem.value = 1
                    val sendIntent= Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT,"Download app here: https://www.download.com")
                        type = "text/plain"
                    }
                    val shareIntent = Intent.createChooser(sendIntent,null)
                    context.startActivity(shareIntent)},
                icon = { Icon(Icons.Filled.Share, contentDescription = "Share") },
                label = { Text(text = "Share") },
                alwaysShowLabel = true
            )
            NavigationBarItem(
                selected =selectedItem.value == 2,
                onClick = {selectedItem.value = 2
                    val intent= Intent(Intent.ACTION_DIAL).apply {
                        data = Uri.parse("tel:0770503514")
                    }
                    context.startActivity(intent)},
                icon = { Icon(Icons.Filled.Phone, contentDescription = "Phone") },
                label = { Text(text = "Phone") },
                alwaysShowLabel = true
            )
        } }
    )
    { innerPadding ->


        Box() {
            Image(
                painter = painterResource(id = R.drawable.background8),
                contentDescription = "background image",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.padding(innerPadding)
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopAppBar(
                title = { Text(text = "ApplicationVault") },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "Home"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search"
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Menu"
                        )
                    }
                    IconButton(onClick = {employeeViewModel.logout(navController, context)}) {
                        Icon(
                            imageVector = Icons.Filled.AccountBox,
                            contentDescription = "Logout"
                        )
                    }


                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    navigationIconContentColor = Color.Black,
                    titleContentColor = Color.Black,
                    actionIconContentColor = Color.Black,
                )
            )

    Column (modifier = Modifier.wrapContentWidth(), horizontalAlignment = Alignment.CenterHorizontally){
        Row {

            Card(
                modifier = Modifier.padding(10.dp).clickable { navController.navigate(
                    ROUTE_IT
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
                    ROUTE_TEACHING) },
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
                    ROUTE_MANAGEMENT) },
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
                    ROUTE_HEALTH) },
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
                { Text(text = "Health related jobs", color = Color.Black) }}
            Card(
                modifier = Modifier.padding(10.dp).clickable { navController.navigate(
                    ROUTE_MEDIA) },
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
                    ROUTE_ENGINEERING) },
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
    }}}}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EmployerHomeScreenPreview(){
    EmployerHomeScreen(rememberNavController())
}
