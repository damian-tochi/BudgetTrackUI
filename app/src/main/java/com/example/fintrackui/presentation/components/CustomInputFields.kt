package com.example.fintrackui.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fintrackui.ui.theme.Gray


@Composable
fun MyTextField(
    onInputChanged: (String) -> Unit,
    inputText: String,
    name: String, ) {

    TextField(
        value = inputText,
        onValueChange = { onInputChanged(it) },
        textStyle = TextStyle(fontSize = 13.sp),
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxWidth()
            .padding(bottom = 13.dp)
            .border(BorderStroke(1.dp, Gray), shape = RoundedCornerShape(10.dp)),
        shape = RoundedCornerShape(10.dp),
        colors = fieldColors(),
        singleLine = true,
        keyboardOptions = myKeyboardOptions,
        placeholder = { TextFieldLabel(name = name) }
    )

}

@Composable
fun TextFieldLabel(name: String) {
    Text(
        text = name, style =TextStyle(fontSize = 13.sp),
        color = Color(0xff727272),
        textAlign = TextAlign.Start,
        lineHeight = 1.sp)
}

val myKeyboardOptions = KeyboardOptions(
    keyboardType = KeyboardType.Email,
    imeAction = ImeAction.Done
)

@Composable
fun ButtonLoading(
    name: String,
    isLoading: Boolean,
    enabled: Boolean,
    onClicked: () -> Unit,
) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
        if (!isLoading) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                enabled = enabled,
                shape = RoundedCornerShape(30),
                onClick = {
                    onClicked()
                }
            ) {
                Text(text = name, style = MaterialTheme.typography.button)
            }
        } else {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun fieldColors() = TextFieldDefaults.textFieldColors(
    backgroundColor = Color(0xfffcfcfc),
    cursorColor = Color(0xff7c7c7c),
    focusedIndicatorColor = Color(0xffe2e2e2),
    unfocusedIndicatorColor = Color(0xffe2e2e2)
)
