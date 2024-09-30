package com.example.fintrackui.presentation.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withAnnotation
import androidx.compose.ui.text.withStyle
import androidx.navigation.NavHostController
import com.example.fintrackui.navigation.Screen
import com.example.fintrackui.presentation.components.AlertDialogSimple
import com.example.fintrackui.presentation.components.HorizontalViewPager
import com.example.fintrackui.presentation.components.RoundedButton
import com.example.fintrackui.ui.theme.Primary
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@OptIn(ExperimentalTextApi::class)
@Composable
fun OnBoardingScreen(navController: NavHostController) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = Color.White,
        darkIcons = true
    )
    var showDialog by remember { mutableStateOf(false) }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().background(Color.White)) {

            HorizontalViewPager(modifier = Modifier.background(color = Color.White))

            Spacer(modifier = Modifier.weight(1f))

            RoundedButton("Create an Account", onClicked = {
                showDialog = true
                Log.d("SplashScreen: ", "Create account clicked") })

            val text = buildAnnotatedString {
                append("Already have an account? ")
                withAnnotation("tag", "annotation") {
                    withStyle(style = androidx.compose.ui.text.SpanStyle(color = Primary)) {
                        append("Sign in")
                    }
                }
            }

            ClickableText(text) {
                text.getStringAnnotations(it, it).firstOrNull()?.tag?.let { tag ->
                    if (tag == "tag") {
                        navController.navigate(Screen.SignIn.route)
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            if (showDialog) {
                AlertDialogSimple(onConfirmation = {
                    showDialog = false
                    navController.navigate(Screen.SignUp.route) { launchSingleTop = true }
                }, onDismissRequest = {
                    showDialog = false
                })
            }
        }

}

