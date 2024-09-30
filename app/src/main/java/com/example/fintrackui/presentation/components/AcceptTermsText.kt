package com.example.fintrackui.presentation.components

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp


@Composable
fun AcceptTermsText() {
    val text = buildAnnotatedString {
        append("By pressing accept below you agree to our ")

        pushStringAnnotation(tag = "terms", annotation = "terms")
        withStyle(style = androidx.compose.ui.text.SpanStyle(textDecoration = TextDecoration.Underline, color = Color.Black, fontSize = 14.sp)) {
            append("Terms and Conditions")
        }
        pop()

        append(". You can find out more about how we use your data in our ")

        pushStringAnnotation(tag = "privacy", annotation = "privacy")
        withStyle(style = androidx.compose.ui.text.SpanStyle(textDecoration = TextDecoration.Underline, color = Color.Black, fontSize = 14.sp)) {
            append("Privacy Policy")
        }
        pop()

        append(".")
    }

    ClickableText(
        text = text,
        onClick = { offset ->
            text.getStringAnnotations(offset, offset).firstOrNull()?.let { annotation ->
                when (annotation.tag) {
                    "terms" -> {
                        println("Terms and Conditions clicked!")
                    }
                    "privacy" -> {

                        println("Privacy Policy clicked!")
                    }
                }
            }
        },
        style = androidx.compose.ui.text.TextStyle(fontSize = 16.sp, color = Color.Black)
    )
}