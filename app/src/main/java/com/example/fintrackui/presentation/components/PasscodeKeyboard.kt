package com.example.fintrackui.presentation.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fintrackui.R
import com.example.fintrackui.ui.theme.Black
import com.example.fintrackui.ui.theme.LightGray
import com.example.fintrackui.ui.theme.Primary


/** Generate Keyboard Key items **/
@Composable
fun PasscodeKeyboard(inputTxt: MutableState<String>) {
    val keysMatrix = arrayOf(
        arrayOf("1", "2", "3"),
        arrayOf("4", "5", "6"),
        arrayOf("7", "8", "9"),
        arrayOf(".", "0", "CLEAR")
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        keysMatrix.forEach { row ->
            PasscodeFixedHeightBox(modifier = Modifier.fillMaxWidth(), height = 56.dp) {
                Row(Modifier) {
                    row.forEach { key ->
                        PasscodeKeyboardKey(inputTxt, keyboardKey = key, modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }

}

/** Keyboard Key UI **/
@Composable
fun PasscodeFixedHeightBox(modifier: Modifier, height: Dp, content: @Composable () -> Unit) {
    Layout(modifier = modifier, content = content) { measurables, constraints ->
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }
        val h = height.roundToPx()
        layout(constraints.maxWidth, h) {
            placeables.forEach { placeable ->
                placeable.place(x = 0, y = 0.coerceAtMost(h - placeable.height))
            }
        }
    }
}

/** Keyboard Key Function **/
@Composable
fun PasscodeKeyboardKey(inputTxt: MutableState<String>, keyboardKey: String, modifier: Modifier) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed = interactionSource.collectIsPressedAsState()
    Box(modifier = modifier.fillMaxHeight(), contentAlignment = Alignment.BottomCenter) {
        when (keyboardKey) {
            "." ->  Box(
                modifier = Modifier
                    .padding(4.dp)
                    .background(Color.White, shape = RoundedCornerShape(6.dp))) {Text(keyboardKey, Modifier
                .fillMaxWidth()
                .padding(2.dp)
                .padding(
                    start = 10.dp,
                    end = 10.dp,
                    top = 12.dp,
                    bottom = 12.dp
                ),
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Black
            )}

            "CLEAR" -> Box(
                modifier = Modifier
                    .padding(10.dp)
                    .background(LightGray, shape = RoundedCornerShape(16.dp))) {
                Image(
                    painter = painterResource(id = R.drawable.ico_delete),
                    contentDescription = "delete",
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.TopCenter).padding(bottom = 10.dp)
                        .clickable(onClick = {
                            val resultString: String = if (inputTxt.value.length > 1) {
                                inputTxt.value.substring(0, inputTxt.value.length - 1)
                            } else {
                                ""
                            }
                            inputTxt.value = resultString
                            //inputTxt.value = ""
                        }
                        )
                )
            }

            else -> Box(
                modifier = Modifier
                    .padding(4.dp)
                    .background(LightGray, shape = RoundedCornerShape(6.dp))) {
                Text(
                    keyboardKey, Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .clickable(interactionSource = interactionSource, indication = null) {
                            inputTxt.value += keyboardKey
                        },
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    color = Black
                )
            }
        }

        if (pressed.value) {
            when (keyboardKey) {
                "Sign Out" -> Text(keyboardKey, Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                        top = 12.dp,
                        bottom = 12.dp
                    ),
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Primary
                )

                "CLEAR" -> Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .background(Primary, shape = RoundedCornerShape(16.dp))) {
                    Image(
                        painter = painterResource(id = R.drawable.ico_delete),
                        contentDescription = "delete",
                        modifier = Modifier
                            .size(20.dp)
                            .align(Alignment.TopCenter).padding(bottom = 10.dp)

                    )
                }

                else -> Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .background(Primary, shape = RoundedCornerShape(6.dp))) {
                    Text(
                        keyboardKey, Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        color = Color.White
                    )
                }
            }


        }
    }
}