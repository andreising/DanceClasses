package com.andreisingeleytsev.danceclasses.ui.navigation


import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.andreisingeleytsev.danceclasses.ui.screens.home_screen.HomeScreen
import com.andreisingeleytsev.danceclasses.ui.screens.main_screen.MainScreen
import com.andreisingeleytsev.danceclasses.ui.screens.onboard_screen.OnboardScreen
import com.andreisingeleytsev.danceclasses.ui.utils.Routes

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigationGraph(
    navHostController: NavHostController,
    startScreen: String
) {
    NavHost(navController = navHostController, startDestination = startScreen) {
        composable(Routes.MAIN_SCREEN) {
            MainScreen()
        }
        composable(Routes.ONBOARD_SCREEN) {
            OnboardScreen(navHostController)
        }
    }
}
