package com.andreisingeleytsev.danceclasses.ui.utils

import com.andreisingeleytsev.danceclasses.R

sealed class DanceItem(
    val nameId: Int = R.string.salsa,
    val titleId: Int = R.string.salsa_title,
    val imageId: Int = R.drawable.salsa,
    val time: Long = 3600000
) {
    object Salsa :
        DanceItem(nameId = R.string.salsa, R.string.salsa_title, R.drawable.salsa, 3600000)

    object Tango :
        DanceItem(nameId = R.string.tango, R.string.tango_title, R.drawable.tango, 5400000)

    object Rumba :
        DanceItem(nameId = R.string.rumba, R.string.rumba_title, R.drawable.rumba, 3600000)

    object Bachata :
        DanceItem(nameId = R.string.bachata, R.string.bachata_title, R.drawable.bachata, 3600000)

    object Zumba :
        DanceItem(nameId = R.string.zumba, R.string.zumba_title, R.drawable.zumba, 5400000)

    object HipHop :
        DanceItem(nameId = R.string.hip_hop, R.string.hip_hop_title, R.drawable.hip_hop, 3600000)
}
