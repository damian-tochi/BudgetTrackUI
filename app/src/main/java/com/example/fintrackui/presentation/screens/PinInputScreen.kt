package com.example.fintrackui.presentation.screens


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fintrackui.navigation.Screen
import com.example.fintrackui.presentation.components.AlertDialogSignOut
import com.example.fintrackui.presentation.components.OtpCodeInput
import com.example.fintrackui.presentation.components.RoundedButton
import com.example.fintrackui.presentation.components.ScreenKeyboard
import com.google.accompanist.systemuicontroller.rememberSystemUiController




@Composable
fun PinInputScreen(navController: NavController) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = Color.White,
        darkIcons = true
    )

    Scaffold(modifier = Modifier.padding(horizontal = 1.dp),content = {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(Color.White),) {

            Spacer(modifier = Modifier.weight(0.4f))

            PinInputContent(navController)

            Spacer(modifier = Modifier.weight(1f))

            RoundedButton("Continue", onClicked = {
                navController.navigate(Screen.AccountSetup.route) { launchSingleTop = true }
                Log.d("SplashScreen: ", "Continue")
            })


            Spacer(modifier = Modifier.weight(0.51f))

        }
    })

}

@Composable
fun PinInputContent(navController: NavController) {
    val otpValue = remember { mutableStateOf("") }
    var isOtpFilled by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    var showDialog by remember { mutableStateOf(false) }


    Column (modifier = Modifier
        .padding(horizontal = 16.dp)
        .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top) {

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Welcome Back",
            style = MaterialTheme.typography.button,
            fontWeight = FontWeight.SemiBold,
            fontSize = 19.sp,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Jane Doe",
            style = MaterialTheme.typography.caption,
            color = Color(0xff333741),
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 6.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        OtpCodeInput (
            modifier = Modifier
                .padding(top = 48.dp)
                .focusRequester(focusRequester),
            otpText = otpValue.value,
            shouldCursorBlink = false,
            otpLength = 4,
            onOtpModified = { value, otpFilled ->
                otpValue.value = value
                isOtpFilled = otpFilled

            }
        )

        ScreenKeyboard(otpValue, onClick = {
            showDialog = true
        })

        Spacer(modifier = Modifier.height(15.dp))

        if (showDialog) {
            AlertDialogSignOut (onConfirmation = {
                showDialog = false
                navController.navigate(Screen.Start.route) { launchSingleTop = true }
            }, onDismissRequest = {
                showDialog = false
            })
        }

    }
}
