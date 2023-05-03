package com.example.whac_a_mole.presentation.components

import android.media.SoundPool
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.whac_a_mole.R
import com.example.whac_a_mole.presentation.HolesEvent
import com.example.whac_a_mole.presentation.HolesState


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HolesGrid(
    state: State<HolesState>,
    onEvent: (HolesEvent) -> Unit,
) {
    val context = LocalContext.current
    val soundPool = remember {
        SoundPool.Builder()
            .setMaxStreams(5) // Number of streams that can play simultaneously
            .build()
    }

    // Load the click sound into the SoundPool
    val clickSoundId = remember {
        soundPool.load(context, R.raw.punch, 1)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 60.dp, horizontal = 16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        LazyVerticalStaggeredGrid(
            horizontalArrangement = Arrangement.SpaceEvenly,
            columns = StaggeredGridCells.Fixed(3),
            content = {
                items(state.value.holes) { hole ->
                    HoleGridItem(
                        hole = hole,
                        onClick = {
                            onEvent.invoke(HolesEvent.MolePunched(hole.holeNumber))
                            soundPool.play(clickSoundId, 1.0f, 1.0f, 0, 0, 1.0f)
                        }
                    )
                }
            },
        )
    }
}
