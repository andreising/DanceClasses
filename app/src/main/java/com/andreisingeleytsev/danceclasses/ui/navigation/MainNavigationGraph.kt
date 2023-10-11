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
import com.andreisingeleytsev.danceclasses.ui.screens.calendar_screen.CalendarScreen
import com.andreisingeleytsev.danceclasses.ui.screens.details_screen.DetailsScreen
import com.andreisingeleytsev.danceclasses.ui.screens.home_screen.HomeScreen
import com.andreisingeleytsev.danceclasses.ui.utils.Routes

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainNavigationGraph(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = Routes.HOME_SCREEN) {
        composable(Routes.HOME_SCREEN) {
            HomeScreen(navHostController)
        }
        composable(Routes.CALENDAR_SCREEN) {
            CalendarScreen(navHostController)
        }
        composable(Routes.DETAILS_SCREEN + "/{dance_id}") {
            DetailsScreen()
        }
    }
}
