package com.andreisingeleytsev.danceclasses.ui.screens.details_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.andreisingeleytsev.danceclasses.R
import com.andreisingeleytsev.danceclasses.ui.theme.Primary
import com.andreisingeleytsev.danceclasses.ui.utils.Fonts
import com.andreisingeleytsev.danceclasses.ui.utils.TimeUtils

@Composable
fun DetailsScreen(viewModel: DetailScreenViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .padding(14.dp)
            .fillMaxSize()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ), elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 10.dp
            )
        ) {
            Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = viewModel.danceItem.value.imageId),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(210.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(modifier = Modifier
                    .padding(horizontal = 14.dp)
                    .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = stringResource(viewModel.danceItem.value.nameId), style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            fontFamily = Fonts.font
                        )
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_calory),
                            contentDescription = null,
                            tint = Color.LightGray
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = stringResource(R.string.low_energy), style = TextStyle(
                                fontSize = 12.sp,
                                color = Color.LightGray,
                                fontFamily = Fonts.font
                            )
                        )
                    }
                }
                Row(verticalAlignment = Alignment.Bottom, modifier = Modifier.height(50.dp)) {
                    Text(
                        text = TimeUtils.getTime(viewModel.time.value) + " ", style = TextStyle(
                            fontSize = 30.sp,
                            color = Color.LightGray,
                            fontFamily = Fonts.font
                        ), modifier = Modifier.padding(0.dp)
                    )
                    Text(
                        text = stringResource(R.string.time).capitalize(Locale.current),
                        style = TextStyle(
                            fontSize = 12.sp,
                            color = Color.LightGray,
                            fontFamily = Fonts.font
                        ),
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.about) + stringResource(id = viewModel.danceItem.value.nameId),
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontFamily = Fonts.font
            )
        )
        Text(
            text = stringResource(id = viewModel.danceItem.value.titleId), style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.LightGray,
                fontFamily = Fonts.font
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                      viewModel.onClick()
            },
            modifier = Modifier
                .fillMaxWidth()
                ,
            shape = RoundedCornerShape(22.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Primary
            )
        ) {
            Text(
                text = stringResource(id = viewModel.buttonText.value), style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontFamily = Fonts.font
                )
            )
        }
    }
}