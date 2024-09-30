package com.example.fintrackui.presentation.screens.bottom_nav_screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fintrackui.R
import com.example.fintrackui.navigation.Screen
import com.example.fintrackui.presentation.components.RoundedButton
import com.example.fintrackui.ui.theme.Gray
import com.google.accompanist.systemuicontroller.rememberSystemUiController



@Composable
fun BudgetScreen(navController: NavHostController) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = Color.White,
        darkIcons = true
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)) {

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "My Budget",
            style = MaterialTheme.typography.body2,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xff161B26),
            textAlign = TextAlign.Start, modifier = Modifier.padding(bottom = 1.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(modifier = Modifier
            .padding(start = 16.dp, end = 15.dp, top = 5.dp, bottom = 5.dp)
            .fillMaxWidth()
            .weight(1f)
            .border(BorderStroke(1.dp, Gray), shape = RoundedCornerShape(0.dp))
            .background(Color.White, shape = RoundedCornerShape(9.dp))) {

            Surface {

                Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {

                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        Image(painter = painterResource(id = R.drawable.target_top_right),
                            contentDescription = null,
                            Modifier.size(100.dp)
                        )

                    }


                    Row {
                        Image(painter = painterResource(id = R.drawable.target_bottom_left),
                            contentDescription = null,
                            Modifier.size(100.dp)
                        )

                    }

                }

                Column(Modifier.fillMaxSize().padding(10.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

                    Text(
                        text = "Nothing to see here yet.",
                        style = MaterialTheme.typography.body2,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xff161B26),
                        textAlign = TextAlign.Center, modifier = Modifier.padding(bottom = 1.dp)
                    )

                    Text(
                        text = "Hi there, create a budget to\nget started.",
                        style = MaterialTheme.typography.caption,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light,
                        color = Color(0xff161B26),
                        textAlign = TextAlign.Center, modifier = Modifier.padding(bottom = 1.dp)
                    )

                    RoundedButton("Create a budget", onClicked = {
                        navController.navigate(Screen.CreateBudgetFirst.route)
                        Log.d("Account setup: ", "Skip clicked")
                    })


                }


            }

        }

        Spacer(modifier = Modifier.height(12.dp))

    }


}