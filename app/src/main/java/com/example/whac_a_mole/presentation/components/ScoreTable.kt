package com.example.whac_a_mole.presentation.components

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import com.example.whac_a_mole.R

@Composable
fun ScoreTable() {
    Box() {
        // Draw the image
        Image(painter = painterResource(id = R.drawable.table), contentDescription = "Score table")

        // Draw the text on top of the image
        Canvas(modifier = Modifier.fillMaxSize()) {
            val paint = Paint().apply {
                color = Color.White.toArgb()
            }
            drawContext.canvas.nativeCanvas.drawText("Score", 50f, 200f, paint)
        }
    }
}