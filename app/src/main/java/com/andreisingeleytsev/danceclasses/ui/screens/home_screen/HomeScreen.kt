package com.andreisingeleytsev.danceclasses.ui.screens.home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.andreisingeleytsev.danceclasses.R
import com.andreisingeleytsev.danceclasses.ui.theme.Primary
import com.andreisingeleytsev.danceclasses.ui.utils.DanceConverter
import com.andreisingeleytsev.danceclasses.ui.utils.Fonts
import com.andreisingeleytsev.danceclasses.ui.utils.Routes

@Composable
fun HomeScreen(navHostController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = stringResource(R.string.next_classes), style = TextStyle(
                fontSize = 16.sp,
                fontFamily = Fonts.font,
                color = Color.Black
            ), modifier = Modifier.padding(16.dp)
        )
        Row(modifier = Modifier
            .padding(start = 16.dp)
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())) {
            CarouselItem(1){danceIndex ->
                navHostController.navigate(Routes.DETAILS_SCREEN+"/$danceIndex")
            }
            Spacer(modifier = Modifier.width(16.dp))
            CarouselItem(5){danceIndex ->
                navHostController.navigate(Routes.DETAILS_SCREEN+"/$danceIndex")
            }
            Spacer(modifier = Modifier.width(16.dp))
            CarouselItem(6){danceIndex ->
                navHostController.navigate(Routes.DETAILS_SCREEN+"/$danceIndex")
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.next_classes_this_week), style = TextStyle(
                fontSize = 16.sp,
                fontFamily = Fonts.font,
                color = Color.Black
            ), modifier = Modifier.padding(16.dp)
        )
        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())) {
            ColumnItem(1){danceIndex ->
                navHostController.navigate(Routes.DETAILS_SCREEN+"/$danceIndex")
            }
            ColumnItem(2){danceIndex ->
                navHostController.navigate(Routes.DETAILS_SCREEN+"/$danceIndex")
            }
            ColumnItem(3){danceIndex ->
                navHostController.navigate(Routes.DETAILS_SCREEN+"/$danceIndex")
            }
            ColumnItem(4){danceIndex ->
                navHostController.navigate(Routes.DETAILS_SCREEN+"/$danceIndex")
            }
            ColumnItem(5){danceIndex ->
                navHostController.navigate(Routes.DETAILS_SCREEN+"/$danceIndex")
            }
            ColumnItem(6){danceIndex ->
                navHostController.navigate(Routes.DETAILS_SCREEN+"/$danceIndex")
            }
        }
    }
}


@Composable
fun CarouselItem(danceIndex: Int, onNavigate: (Int)-> Unit){
    val dance = DanceConverter.list.getValue(danceIndex)
    Card(elevation = CardDefaults.elevatedCardElevation(
        defaultElevation = 10.dp
    ), modifier = Modifier
        .size(250.dp)
        .clickable {
            onNavigate(danceIndex)
        }) {
        Image(
            painter = painterResource(id = dance.imageId),
            contentDescription = null,
            modifier = Modifier
                .height(125.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Text(text = stringResource(id = dance.nameId).uppercase(), style = TextStyle(
            fontSize = 12.sp,
            fontFamily = Fonts.font,
            color = Primary
        ), modifier = Modifier.padding(start = 12.dp)
        )
        Text(
            text = stringResource(id = dance.titleId), style = TextStyle(
                fontSize = 16.sp,
                fontFamily = Fonts.font,
                color = Color.Black,
            ), modifier = Modifier.padding(start = 12.dp), maxLines = 4,
            overflow = TextOverflow.Ellipsis
        )

    }
}

@Composable
fun ColumnItem(danceIndex: Int, onNavigate: (Int)-> Unit) {
    val dance = DanceConverter.list.getValue(danceIndex)
    Card(
        modifier = Modifier
            .padding(14.dp)
            .fillMaxWidth(), colors = CardDefaults.cardColors(
            containerColor = Color.White
        ), shape = RoundedCornerShape(15.dp), elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(modifier = Modifier.size(60.dp), shape = RoundedCornerShape(15.dp)) {
                    Image(
                        painter = painterResource(id = dance.imageId),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = stringResource(id = dance.nameId), style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = Fonts.font,
                            color = Color.Black
                        )
                    )
                    Text(
                        text = (dance.time / 3600000).toString() + ":" + ((dance.time % 3600000)/60000).toString() + " " + stringResource(
                            id = R.string.hours
                        ), style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = Fonts.font,
                            color = Color.LightGray
                        )
                    )
                }
            }
            Image(
                painter = painterResource(id = R.drawable.btn_see_more),
                contentDescription = null,
                modifier = Modifier.clickable {
                    onNavigate(danceIndex)
                })
        }
    }
}