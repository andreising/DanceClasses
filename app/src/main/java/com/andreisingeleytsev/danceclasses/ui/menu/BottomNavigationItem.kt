package com.andreisingeleytsev.danceclasses.ui.menu

import com.andreisingeleytsev.danceclasses.R
import com.andreisingeleytsev.danceclasses.ui.utils.Routes

sealed class BottomNavigationItem( val icon_id: Int, val route: String) {
    object HomeItem: BottomNavigationItem( R.drawable.icon_home, Routes.HOME_SCREEN)
    object CalendarItem: BottomNavigationItem( R.drawable.icon_calendar, Routes.CALENDAR_SCREEN)
    object ClassItem: BottomNavigationItem( R.drawable.icon_class, Routes.DETAILS_SCREEN+ "/1")
}
