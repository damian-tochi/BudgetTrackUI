package com.example.fintrackui.presentation.screens

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fintrackui.R
import com.example.fintrackui.navigation.Screen
import com.example.fintrackui.ui.theme.Primary
import com.google.accompanist.systemuicontroller.rememberSystemUiController



@Composable
fun SplashScreen(navController: NavHostController,) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = Primary,
        darkIcons = false
    )

    val degrees = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        degrees.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 1800,
                delayMillis = 200
            )
        )

        navController.popBackStack()
        navController.navigate(Screen.Start.route)
        Log.d("SplashScreen: ", "Onboard route")

    }

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Primary).padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.frame_logo),
                contentDescription = "logo",
                modifier = Modifier.size(90.dp)
            )
        }
    }
}