package com.andreisingeleytsev.danceclasses.ui.screens.details_screen

import android.os.CountDownTimer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.andreisingeleytsev.danceclasses.R
import com.andreisingeleytsev.danceclasses.ui.utils.DanceConverter
import com.andreisingeleytsev.danceclasses.ui.utils.DanceItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private var id = 1
    val danceItem = mutableStateOf< DanceItem>(
        DanceItem.Salsa
    )
    private var timer:CountDownTimer? = null
    val time = mutableStateOf(0L)
    val buttonText = mutableStateOf(
        R.string.start_the_dance
    )
    private var isGameOn = false
    fun onClick(){
        if (isGameOn) onFinish()
        else onStart()
    }
    private fun onStart(){
        isGameOn = true
        timer!!.start()
        buttonText.value = R.string.finish_the_dance
    }

    private fun onFinish(){
        isGameOn = false
        timer!!.cancel()
        time.value = danceItem.value.time
        buttonText.value = R.string.start_the_dance
    }
    init {
        id = savedStateHandle.get<String>("dance_id")?.toInt() ?: 1
        danceItem.value = DanceConverter.list[id]!!
        time.value = danceItem.value.time
        timer = object : CountDownTimer(danceItem.value.time, 60000){
            override fun onTick(p0: Long) {
                time.value = time.value - 60000
            }
            override fun onFinish() {
                onFinish()
            }
        }
    }
}