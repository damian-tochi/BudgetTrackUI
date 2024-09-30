package com.example.fintrackui.presentation.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.navigation.NavHostController
import com.example.fintrackui.R
import com.example.fintrackui.model.CardItem
import com.example.fintrackui.navigation.Screen
import com.example.fintrackui.presentation.components.RoundedButton
import com.example.fintrackui.ui.theme.Black
import com.example.fintrackui.ui.theme.Gray
import com.example.fintrackui.ui.theme.Primary
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController



@Composable
fun AccountSetupScreen(navController: NavHostController) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = Color.White,
        darkIcons = true
    )

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start) {

            Spacer(modifier = Modifier.weight(0.2f))

            val header = buildAnnotatedString {
                withStyle(
                    style = androidx.compose.ui.text.SpanStyle(
                        color = Black,
                        fontSize = 18.sp
                    )
                ) {
                    append("Welcome to ")
                }

                withStyle(
                    style = androidx.compose.ui.text.SpanStyle(
                        color = Primary,
                        fontSize = 18.sp
                    )
                ) {
                    append("Fintrack")
                }
                withStyle(
                    style = androidx.compose.ui.text.SpanStyle(
                        color = Black,
                        fontSize = 18.sp
                    )
                ) {
                    append("!\nLet's get you set up.")
                }

            }

            ClickableText(header) {
                header.getStringAnnotations(it, it).firstOrNull()?.tag?.let {}
            }

            Spacer(modifier = Modifier.weight(0.2f))

            VerticalCardList(navController)

            Spacer(modifier = Modifier.weight(2f))

            RoundedButton("Skip for now", onClicked = {
                navController.popBackStack()
                navController.navigate(Screen.Home.route)
                Log.d("Account setup: ", "Skip clicked")
            })

            Spacer(modifier = Modifier.weight(0.2f))

        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun VerticalCardList(navController: NavHostController) {

    val cardItems = remember {
        listOf(
            CardItem("Set up a pin", "Enhance your  account\nsecurity.", R.drawable.electronic_wallet_banking),
            CardItem("Link your bank\naccounts.", "Link your bank accounts to\nstart tracking your expenses.",  R.drawable.frame_phone_coin),
            CardItem("Create a savings goal", "What are your financial goals?", R.drawable.growing_money),
        )
    }

    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .background(Color.White), verticalArrangement = Arrangement.spacedBy(8.dp)) { items(cardItems.size) { item ->

            Card(modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .height(130.dp)
                .padding(5.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                onClick = {
                    if (item == 0) {
                        navController.navigate(Screen.CreatePassword.route) { launchSingleTop = true }
                        Log.d("Account setup: ", "Create Password")
                    }
                }
            ) {

                Row (modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {

                    Column(modifier = Modifier
                        .padding(10.dp)
                        .weight(1f)) {
                        Text(text = cardItems[item].title, style = MaterialTheme.typography.bodySmall, fontSize = 13.sp)
                        Spacer(modifier = Modifier.height(1.dp))
                        Text(text = cardItems[item].description, fontSize = 13.sp)
                    }

                    Image(
                        painter = painterResource(id = cardItems[item].image),
                        contentDescription = "logo",
                        modifier = Modifier
                            .height(120.dp)
                            .width(100.dp),
                    )
                }

            }

    }
    }
}

