package com.example.fintrackui.presentation.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.IconButton
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.MaterialTheme
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.example.fintrackui.R
import com.example.fintrackui.navigation.Screen
import com.example.fintrackui.presentation.components.MyTextField
import com.example.fintrackui.presentation.components.RoundedButton
import com.example.fintrackui.ui.theme.Primary


@OptIn(ExperimentalTextApi::class)
@Composable
fun SignInScreen(navController: NavController) {

    val context = LocalContext.current

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.White,
            darkIcons = true
        )
    }

    Scaffold(modifier = Modifier.padding(horizontal = 1.dp),content = {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(Color.White), horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(modifier = Modifier.weight(0.5f))

            SignInMainContent()

            Spacer(modifier = Modifier.weight(3f))

            RoundedButton("Sign In", onClicked = {
                navController.navigate(Screen.CheckEmailOtp.route) { launchSingleTop = true }
                Log.d("SplashScreen: ", "Login account clicked")
            })

            val text = buildAnnotatedString {
                append("Do not have an account? ")
                withAnnotation("tag", "annotation") {
                    withStyle(style = androidx.compose.ui.text.SpanStyle(color = Primary)) {
                        append("Sign Up")
                    }
                }
            }

            ClickableText(text) {
                text.getStringAnnotations(it, it).firstOrNull()?.tag?.let { tag ->
                    if (tag == "tag") {
                        navController.navigate(Screen.SignUp.route)
                    }
                }
            }

            Spacer(modifier = Modifier.fillMaxHeight(0.1f))

        }
    })

}

@Composable
fun SignInMainContent() {
    val emailAdd = remember { mutableStateOf("") }

    Column (modifier = Modifier.padding(horizontal = 16.dp).background(Color.White),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top) {


        Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Welcome back",
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.Bold,
                fontSize = 23.sp,
                modifier = Modifier.padding(end = 13.dp)
            )

            Image(painter = painterResource(id = R.drawable.party_opper), contentDescription = null, Modifier.size(20.dp))

        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Sign in to your account and start\nmanaging your finances with Fintrack\ntoday.",
            style = MaterialTheme.typography.caption,
            color = Color(0xff333741),
            fontWeight = FontWeight.Medium,
            fontSize = 13.sp,
            modifier = Modifier.padding(bottom = 6.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Email address",
            style = MaterialTheme.typography.caption,
            color = Color(0xff161B26),
            textAlign = TextAlign.Start, modifier = Modifier
                .padding(bottom = 10.dp)
        )

        MyTextField(
            onInputChanged = {value->  emailAdd.value = value},
            inputText = emailAdd.value,
            name = "e.g email@mail.com")

    }
}

@Preview
@Composable
fun SignInMainContentPreview() {
    SignInMainContent()
}




