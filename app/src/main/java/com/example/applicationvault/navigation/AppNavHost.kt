package com.example.applicationvault.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.applicationvault.ui.theme.SplashScreen
import com.example.applicationvault.ui.theme.screens.login.LoginScreen
import com.example.applicationvault.ui.theme.screens.registration.EmployerregistrationScreen
import com.example.applicationvault.ui.theme.screens.registration.RegisterScreen

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController(), startDestination:String= ROUTE_SPLASH){
    NavHost(navController,startDestination){
        composable(ROUTE_SPLASH) { SplashScreen { navController.navigate(ROUTE_REGISTER){popUpTo(
            ROUTE_SPLASH){inclusive=true}} }  }
        composable(ROUTE_REGISTER) { RegisterScreen(navController) }
        composable(ROUTE_LOGIN) { LoginScreen() }
        composable(ROUTE_EMPLOYER_REGISTER) { EmployerregistrationScreen() }
    }

}