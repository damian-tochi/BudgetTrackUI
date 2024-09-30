package com.example.fintrackui.presentation.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.fintrackui.R
import com.example.fintrackui.navigation.Screen
import com.example.fintrackui.presentation.components.OtpCodeInput
import com.example.fintrackui.presentation.components.PasscodeKeyboard
import com.example.fintrackui.presentation.components.RoundedButton
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun CreatePasswordScreen(navController: NavHostController) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = Color.White,
        darkIcons = true
    )

    Scaffold(modifier = Modifier.padding(horizontal = 1.dp), content = {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(Color.White),
        ) {

            PassCodeInputContent(navController)

            Spacer(modifier = Modifier.weight(1f))

            RoundedButton("Create a Pin", onClicked = {
                navController.navigate(Screen.AccountSetup.route) { launchSingleTop = true }
                Log.d("Create Passcode Screen: ", "Create Pin")
            })


            Spacer(modifier = Modifier.fillMaxHeight(0.15f))

        }
    })

}

@Composable
fun PassCodeInputContent(navController: NavController) {
    val otpValue = remember { mutableStateOf("") }
    var isOtpFilled by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    var showDialog by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Spacer(modifier = Modifier.height(6.dp))

        Row(Modifier.padding(end = 20.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.icon_back_btn),
                contentDescription = "back_btn",
                modifier = Modifier
                    .height(20.dp)
                    .width(20.dp).clickable {
                        navController.popBackStack()
                    },
            )
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .weight(1f), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Create your passcode",
                    style = androidx.compose.material3.MaterialTheme.typography.bodySmall,
                    fontSize = 13.sp
                )
            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "For a more secure and convenient way to\nview your account, create a 4-digit\npasscode now.",
            style = MaterialTheme.typography.caption,
            color = Color(0xff333741),
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            modifier = Modifier.padding(bottom = 6.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        OtpCodeInput(
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

        PasscodeKeyboard(otpValue)

        Spacer(modifier = Modifier.height(15.dp))

    }
}
