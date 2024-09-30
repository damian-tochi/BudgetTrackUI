package com.example.fintrackui.presentation.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fintrackui.ui.theme.Primary


@Composable
fun RoundedButton(text: String, onClicked: ()->Unit) {
    val shape = RoundedCornerShape(60)

    Button(
        onClick = onClicked,
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()
            .height(40.dp),
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Primary,
            contentColor = Color.White
        )) {
        Text(text = text, fontSize = 11.sp)
    }

}