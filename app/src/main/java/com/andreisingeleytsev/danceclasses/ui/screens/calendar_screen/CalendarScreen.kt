package com.andreisingeleytsev.danceclasses.ui.screens.calendar_screen

import android.icu.util.LocaleData
import android.os.Build
import android.telecom.Call.Details
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.andreisingeleytsev.danceclasses.R
import com.andreisingeleytsev.danceclasses.ui.screens.home_screen.ColumnItem
import com.andreisingeleytsev.danceclasses.ui.theme.Primary
import com.andreisingeleytsev.danceclasses.ui.utils.Fonts
import com.andreisingeleytsev.danceclasses.ui.utils.Routes
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.day.NonSelectableDayState
import io.github.boguszpawlowski.composecalendar.header.MonthState
import io.github.boguszpawlowski.composecalendar.rememberSelectableCalendarState
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarScreen(navHostController: NavHostController){
    val day = remember {
        mutableStateOf(LocalDate.now())
    }
    val danceIndex = remember {
        mutableStateOf(day.value.dayOfWeek.value)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        val calendarState = rememberSelectableCalendarState()
        Card(colors = CardDefaults.cardColors(
            containerColor = Color.White
        ), shape = RoundedCornerShape(8.dp), modifier = Modifier.padding(14.dp)
        ) {
            SelectableCalendar(
                calendarState = calendarState, today = day.value,
                dayContent = { DayContentOfMonth(dayState = it as NonSelectableDayState, day, danceIndex) },
                daysOfWeekHeader = { DefaultDaysOfWeekHeader(daysOfWeek = it) },
                monthHeader = { MonthHeader(monthState = it) },
                showAdjacentMonths = true,
                modifier = Modifier.padding(20.dp)
            )
        }
        Text(text = stringResource(R.string.classes_on), style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontFamily = Fonts.font
        ), modifier = Modifier.padding(20.dp) )
        if (danceIndex.value in 1..6) {
            ColumnItem(danceIndex = danceIndex.value){danceIndex ->
                navHostController.navigate(Routes.DETAILS_SCREEN+"/$danceIndex")
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DayContentOfMonth(
    dayState: NonSelectableDayState,
    day: MutableState<LocalDate>,
    danceIndex: MutableState<Int>
) {
    val backgroundColor = when (dayState.date) {
        day.value -> Primary
        else -> {
            Color.White
        }
    }
    val textColor = when (dayState.date) {
        day.value-> Color.White
        else -> {
            Color.Black
        }
    }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ), shape = RoundedCornerShape(8.dp), modifier = Modifier.clickable {
            day.value = dayState.date
            danceIndex.value = dayState.date.dayOfWeek.value
        }
    ) {
        Text(
            text = dayState.date.dayOfMonth.toString(),
            style = TextStyle(
                fontSize = 14.sp,
                color = textColor,
                fontFamily = Fonts.font
            ),
            modifier = Modifier
                .padding(horizontal = 12.dp),
            textAlign = TextAlign.Center,

            )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DefaultDaysOfWeekHeader(
    daysOfWeek: List<DayOfWeek>,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        daysOfWeek.forEach { dayOfWeek ->
            Text(
                textAlign = TextAlign.Center,
                text = dayOfWeek.getDisplayName(java.time.format.TextStyle.SHORT, Locale.getDefault()),
                modifier = modifier
                    .weight(1f)
                    .wrapContentHeight()
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MonthHeader(monthState: MonthState) {
    Row(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.calendar), style = TextStyle(
                fontSize = 16.sp,
                fontFamily = Fonts.font,
                color = Color.Black
            )
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = {
                monthState.currentMonth = monthState.currentMonth.minusMonths(1)
            }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = null,
                    tint = Color.LightGray
                )
            }
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                monthState.currentMonth.month.name,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.LightGray,
                    fontFamily = Fonts.font
                )
            )
            Spacer(modifier = Modifier.width(5.dp))
            IconButton(onClick = {
                monthState.currentMonth = monthState.currentMonth.plusMonths(1)
            }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = Color.LightGray
                )
            }
        }

    }
}