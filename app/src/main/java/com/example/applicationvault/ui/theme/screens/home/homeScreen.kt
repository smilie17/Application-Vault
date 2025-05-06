package com.example.applicationvault.ui.theme.screens.home

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.applicationvault.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController){
    val selectedItem by remember { mutableStateOf("") }
    val context = LocalContext.current
    Column {
        Scaffold(
            bottomBar = {
                NavigationBar(containerColor = Color.Black) {

                    NavigationBarItem(
                        selected = selectedItem.value == 0,
                        onClick = {
                            selectedItem.value = 0
                            val intent = Intent(Intent.ACTION_SENDTO).apply {
                                data = Uri.parse("mailto: info@eemobilis.com")
                                putExtra(Intent.EXTRA_SUBJECT, "Inquiry")
                                putExtra(
                                    Intent.EXTRA_TEXT,
                                    "Hello,confirm to me my total fee balance"
                                )
                            }
                            context.startActivity(intent)
                        },
                        icon = { Icon(Icons.Filled.Email, contentDescription = "Email") },
                        label = { Text(text = "Email") },
                        alwaysShowLabel = true
                    )
                    NavigationBarItem(
                        selected = selectedItem.value == 1,
                        onClick = {
                            selectedItem.value = 1
                            val sendIntent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(
                                    Intent.EXTRA_TEXT,
                                    "Download app here: https://www.download.com"
                                )
                                type = "text/plain"
                            }
                            val shareIntent = Intent.createChooser(sendIntent, null)
                            context.startActivity(shareIntent)
                        },
                        icon = { Icon(Icons.Filled.Share, contentDescription = "Share") },
                        label = { Text(text = "Share") },
                        alwaysShowLabel = true
                    )
                    NavigationBarItem(
                        selected = selectedItem.value == 2,
                        onClick = {
                            selectedItem.value = 2
                            val intent = Intent(Intent.ACTION_DIAL).apply {
                                data = Uri.parse("tel:0770503514")
                            }
                            context.startActivity(intent)
                        },
                        icon = { Icon(Icons.Filled.Phone, contentDescription = "Phone") },
                        label = { Text(text = "Phone") },
                        alwaysShowLabel = true
                    )
                }
            }
        )
        { innerPadding ->


            Box() {
                Image(
                    painter = painterResource(id = R.drawable.logo),
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
                    title = { Text(text = "FreeSpiritApp") },
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
                        IconButton(onClick = {  }) {
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
            }

        }
    }     }