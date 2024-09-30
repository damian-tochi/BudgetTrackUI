package com.example.fintrackui.presentation.screens


//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withAnnotation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fintrackui.navigation.Screen
import com.example.fintrackui.presentation.components.MyTextField
import com.example.fintrackui.presentation.components.RoundedButton
import com.example.fintrackui.ui.theme.Primary
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay


@OptIn(ExperimentalTextApi::class)
@Composable
fun CheckEmailScreen(navController: NavController) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = Color.White,
        darkIcons = true
    )

    var timeLeft by remember { mutableStateOf(50) }
    var isRunning by remember { mutableStateOf(true) }

    LaunchedEffect(isRunning) {
        while (isRunning && timeLeft > 0) {
            delay(1000)
            timeLeft -= 1
        }
        if (timeLeft == 0) {
            isRunning = false
        }
    }

    Scaffold(modifier = Modifier.padding(horizontal = 1.dp),content = {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(Color.White), horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(modifier = Modifier.weight(0.6f))

            CheckEmailMainContent()

            Spacer(modifier = Modifier.weight(3.5f))

            RoundedButton("Continue", onClicked = {
                navController.navigate(Screen.PinInput.route) { launchSingleTop = true }
                Log.d("SplashScreen: ", "Continue")
            })

            val text = buildAnnotatedString {
                append("Didn’t recieve the email? ")
                withAnnotation("tag", "annotation") {
                    if (isRunning) {
                        withStyle(style = androidx.compose.ui.text.SpanStyle(color = Color.Black)) {
                            append("Resend code in ${timeLeft}s")
                        }
                    } else {
                        withStyle(style = androidx.compose.ui.text.SpanStyle(color = Primary)) {
                            append("Resend code")
                        }
                    }
                }
            }

            ClickableText(text) {
                text.getStringAnnotations(it, it).firstOrNull()?.tag?.let { tag ->
                    if (tag == "tag" && !isRunning) {
                        navController.navigate(Screen.SignIn.route)
                    }
                }
            }

            Spacer(modifier = Modifier.weight(0.6f))

        }
    })

}

@Composable
fun CheckEmailMainContent() {
    val firstName = remember { mutableStateOf("") }


    Column (modifier = Modifier
        .padding(horizontal = 16.dp)
        .background(Color.White),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top) {

        Text(
            text = "Check your email!",
            style = MaterialTheme.typography.caption,
            fontWeight = FontWeight.Medium,
            fontSize = 19.sp,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "We have sent an email to\njanedoe@gmail.com. Please remember to\ncheck your inbox as well as the spam\nfolder.",
            style = MaterialTheme.typography.caption,
            color = Color(0xff333741),
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            modifier = Modifier.padding(bottom = 6.dp)
        )

        Text(
            text = "Please enter the verification code below\nto continue with your account.",
            style = MaterialTheme.typography.caption,
            color = Color(0xff333741),
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            modifier = Modifier.padding(bottom = 6.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Enter verification code",
            style = MaterialTheme.typography.caption,
            color = Color(0xff161B26),
            textAlign = TextAlign.Start, modifier = Modifier
                .padding(bottom = 10.dp)
        )
        MyTextField(
            onInputChanged = {value->  firstName.value = value},
            inputText = firstName.value,
            name = "Enter verification code")

        Spacer(modifier = Modifier.height(15.dp))

    }
}

@Preview
@Composable
fun CheckEmailMainContentPreview() {
    CheckEmailMainContent()
}