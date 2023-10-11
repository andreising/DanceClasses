package com.andreisingeleytsev.danceclasses.ui.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.andreisingeleytsev.danceclasses.ui.theme.Primary


@Composable
fun DanceClassesBottomNavigationMenu(currentRoute: String?, onNavigate: (String)->Unit) {
    val listItems = listOf(
        BottomNavigationItem.HomeItem,
        BottomNavigationItem.CalendarItem,
        BottomNavigationItem.ClassItem

    )
    BottomAppBar(
        containerColor = Color.White
    ) {
        listItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute==item.route,
                onClick = {
                    onNavigate(item.route)
                },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = item.icon_id),
                            contentDescription = "bottom_icon"
                        )
                        if (currentRoute == item.route) {
                            Spacer(modifier = Modifier.height(5.dp))
                            Card(
                                shape = CircleShape,
                                modifier = Modifier.size(5.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Primary
                                )
                            ) {}
                        }
                    }

                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Primary,
                    unselectedIconColor = Color.Black,
                    indicatorColor = Color.White
                )
            )

        }
    }
}