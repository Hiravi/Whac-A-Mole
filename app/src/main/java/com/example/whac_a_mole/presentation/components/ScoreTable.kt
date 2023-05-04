package com.example.whac_a_mole.presentation.components

import android.graphics.Paint
import android.graphics.Typeface
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.whac_a_mole.R
import com.example.whac_a_mole.presentation.HolesState

@Composable
fun ScoreTable(
    state: State<HolesState>,
    time: Int
) {
    Box() {
        // Draw the image
        Image(painter = painterResource(id = R.drawable.table), contentDescription = "Score table")
        val pixelTypeface = LocalContext.current.resources.getFont(R.font.pixel_font)
        // Draw the text on top of the image
        Canvas(modifier = Modifier) {

            val paint = Paint().apply {
                color = Color.White.toArgb()
                textSize = 90f
                typeface = pixelTypeface
            }
            drawContext.canvas.nativeCanvas.drawText("Score:  ${state.value.score}", 50f, 90f, paint)
            drawContext.canvas.nativeCanvas.drawText("Time  $time", 50f, 245f, paint)
        }
    }
}