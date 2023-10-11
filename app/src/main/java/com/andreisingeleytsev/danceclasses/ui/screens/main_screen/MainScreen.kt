package com.andreisingeleytsev.danceclasses.ui.screens.main_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.andreisingeleytsev.danceclasses.R
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.andreisingeleytsev.danceclasses.ui.navigation.MainNavigationGraph
import com.andreisingeleytsev.danceclasses.ui.utils.Fonts
import com.andreisingeleytsev.danceclasses.ui.menu.DanceClassesBottomNavigationMenu
import com.andreisingeleytsev.danceclasses.ui.theme.BackgroundColor
import com.andreisingeleytsev.danceclasses.ui.utils.Routes

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val title = when(currentRoute){
        Routes.HOME_SCREEN-> {
            R.string.home
        }
        Routes.CALENDAR_SCREEN-> {
            R.string.calendar
        }
        Routes.DETAILS_SCREEN + "/{dance_id}"-> {
            R.string.details
        }

        else -> {R.string.home}
    }
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        CenterAlignedTopAppBar(title = {
            Text(text = stringResource(id = title), style = TextStyle(
                fontSize = 16.sp,
                fontFamily = Fonts.font,
                color = Color.Black
            ))
        }, navigationIcon = {
            if (currentRoute == Routes.DETAILS_SCREEN + "/{dance_id}") IconButton(onClick = { }) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }
        })
    }, bottomBar = {
        DanceClassesBottomNavigationMenu(currentRoute = currentRoute){route ->
            navController.navigate(route)
        }
    }) {
        Box(modifier = Modifier
            .padding(it)
            .fillMaxSize()
            .background(BackgroundColor)){
            MainNavigationGraph(navHostController = navController)
        }
    }
}