package com.andreisingeleytsev.danceclasses.ui.screens.onboard_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.andreisingeleytsev.danceclasses.R
import com.andreisingeleytsev.danceclasses.ui.theme.Primary
import com.andreisingeleytsev.danceclasses.ui.utils.Routes
import com.andreisingeleytsev.danceclasses.ui.screens.onboard_screen.OnBoardScreenViewModel
import kotlinx.coroutines.delay


@Composable
fun OnboardScreen(
    navHostController: NavHostController,
    viewModel: OnBoardScreenViewModel = hiltViewModel()
) {
    val state = rememberScrollState()
    val isLoading = remember {
        mutableStateOf(true)
    }
    LaunchedEffect(key1 = true){
        delay(1000)
        isLoading.value = false
    }
    BoxWithConstraints(
        Modifier
            .fillMaxSize()
            .background(Color.White)) {
        val width = 3 * maxWidth / 4
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(state)) {
            Row(modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                Card(
                    shape = RoundedCornerShape(22.dp), colors = CardDefaults.cardColors(
                        containerColor = Primary
                    )
                ) {
                    Text(
                        text = stringResource(R.string.onboard_1),
                        color = Color.White,
                        fontSize = 22.sp,
                        modifier = Modifier
                            .padding(10.dp)
                            .width(width)
                    )
                }
            }
            Row(modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Card(
                    shape = RoundedCornerShape(22.dp), colors = CardDefaults.cardColors(
                        containerColor = Primary
                    )
                ) {
                    Text(
                        text = stringResource(R.string.onboard_2),
                        color = Color.White,
                        fontSize = 22.sp,
                        modifier = Modifier
                            .padding(10.dp)
                            .width(width)
                    )
                }
            }
            Row(modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                Card(
                    shape = RoundedCornerShape(22.dp), colors = CardDefaults.cardColors(
                        containerColor = Primary
                    )
                ) {
                    Text(
                        text = stringResource(R.string.onboard_3),
                        color = Color.White,
                        fontSize = 22.sp,
                        modifier = Modifier
                            .padding(10.dp)
                            .width(width)
                    )
                }
            }
            AnimatedVisibility(
                modifier = Modifier.fillMaxWidth(),
                visible = state.value >= state.maxValue-50
            ) {
                Button(
                    onClick = {
                        viewModel.saveOnBoardingState(true)
                        navHostController.popBackStack()
                        navHostController.navigate(Routes.MAIN_SCREEN)
                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = Primary
                    ), modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(), shape = RoundedCornerShape(47.dp)
                ) {
                    Text(
                        text = "Start Dancing!",
                        color = Color.White,
                        fontSize = 22.sp
                    )
                }
            }

        }
    }
    if (isLoading.value)Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White))
}