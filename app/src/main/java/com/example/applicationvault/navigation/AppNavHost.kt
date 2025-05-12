package com.example.applicationvault.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.applicationvault.ui.theme.SplashScreen
import com.example.applicationvault.ui.theme.screens.IT.ItEmployeeScreen
import com.example.applicationvault.ui.theme.screens.management.ManagementScreen
import com.example.applicationvault.ui.theme.screens.IT.ItScreen
import com.example.applicationvault.ui.theme.screens.apply.Jobs
import com.example.applicationvault.ui.theme.screens.engineering.EmployeeEngineeringScreen
import com.example.applicationvault.ui.theme.screens.engineering.EngineeringScreen

import com.example.applicationvault.ui.theme.screens.health.EmployeeHealthScreen
import com.example.applicationvault.ui.theme.screens.health.HealthScreen
import com.example.applicationvault.ui.theme.screens.home.EmployeeHomeScreen
import com.example.applicationvault.ui.theme.screens.home.EmployerHomeScreen
import com.example.applicationvault.ui.theme.screens.jobs.ViewJobs
import com.example.applicationvault.ui.theme.screens.login.EmployeeLoginScreen
import com.example.applicationvault.ui.theme.screens.login.EmployerLoginScreen
import com.example.applicationvault.ui.theme.screens.management.EmployeeManagementScreen
import com.example.applicationvault.ui.theme.screens.media.EmployeeMediaScreen
import com.example.applicationvault.ui.theme.screens.media.MediaScreen
import com.example.applicationvault.ui.theme.screens.registration.EmployeeregisterScreen
import com.example.applicationvault.ui.theme.screens.registration.EmployerregistrationScreen
import com.example.applicationvault.ui.theme.screens.registration.RegisterScreen
import com.example.applicationvault.ui.theme.screens.teaching.EmployeeTeachingScreen
import com.example.applicationvault.ui.theme.screens.teaching.TeachingScreen
import com.example.applicationvault.ui.theme.screens.teaching.ViewTeachingJobs
import com.example.applicationvault.ui.theme.screens.updates.employeeupdateScreen
import com.example.applicationvault.ui.theme.screens.view.ViewEmployees

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController(), startDestination:String= ROUTE_SPLASH){
    NavHost(navController,startDestination){
        composable(ROUTE_SPLASH) { SplashScreen { navController.navigate(ROUTE_REGISTER){popUpTo(
            ROUTE_SPLASH){inclusive=true}} }  }
        composable(ROUTE_REGISTER) { RegisterScreen(navController) }
        composable(ROUTE_EMPLOYEE_LOGIN) { EmployeeLoginScreen(navController) }
        composable(ROUTE_EMPLOYER_REGISTER) { EmployerregistrationScreen(navController) }
        composable(ROUTE_EMPLOYEE_REGISTER) { EmployeeregisterScreen(navController) }
        composable(ROUTE_EMPLOYER_LOGIN) { EmployerLoginScreen(navController) }
        composable(ROUTE_IT) { ItScreen(navController) }
        composable(ROUTE_EMPLOYEE_HOME) { EmployeeHomeScreen(navController)  }
        composable(ROUTE_EMPLOYER_HOME) { EmployerHomeScreen(navController) }
        composable(ROUTE_TEACHING) { TeachingScreen(navController) }
        composable(ROUTE_MANAGEMENT) { ManagementScreen(navController) }
        composable(ROUTE_HEALTH) { HealthScreen(navController) }
        composable(ROUTE_MEDIA) { MediaScreen(navController) }
        composable(ROUTE_ENGINEERING) { EngineeringScreen(navController) }
        composable(ROUTE_EMPLOYEE_ENGINEERING) { EmployeeEngineeringScreen(navController) }
        composable(ROUTE_EMPLOYEE_HEALTH) { EmployeeHealthScreen(navController) }
        composable(ROUTE_EMPLOYEE_TEACHING) { EmployeeTeachingScreen(navController)  }
        composable(ROUTE_EMPLOYEE_MEDIA) { EmployeeMediaScreen(navController) }
        composable(ROUTE_EMPLOYEE_MANAGEMENT) { EmployeeManagementScreen(navController)  }
        composable(ROUTE_EMPLOYEE_IT) { ItEmployeeScreen(navController)  }
        composable(ROUTE_VIEW_EMPLOYEE) { ViewEmployees(navController) }
        composable("$ROUTE_UPDATE_EMPLOYEE/{employeeid}") {
                passedData -> employeeupdateScreen(
            navController,passedData.arguments?.getString("employeeid")!!)
        }
        composable(ROUTE_VIEW_JOBS) { ViewJobs(navController)  }
        composable(ROUTE_APPLY) { Jobs(navController) }

    }

}

//        composable("$ROUTE_UPDATE_EMPLOYER/{employerid}") {
//                passeddata -> employerupdateScreen(
//            navController,
//            employerid = TODO()
//        )
//            navController,passeddata.arguments?.getString("employerid")!!)
//        }
//    }

