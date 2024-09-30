package com.example.fintrackui.model

import androidx.compose.ui.graphics.Color

data class HomeCardItems(
    val title: String,
    val leftAction: String,
    val sum: String,
    val progress: Float,
    val progressColor: Color,
    val description: String,
    val img: Int,
    val color: Color,
    val contentColor: Color,
    val leftBtnColor: Color
)