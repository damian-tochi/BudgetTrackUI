package com.example.fintrackui.presentation.screens.create_budget_sequence

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fintrackui.R
import com.example.fintrackui.navigation.Screen
import com.example.fintrackui.presentation.components.MyTextField
import com.example.fintrackui.presentation.components.RoundedButton
import com.example.fintrackui.ui.theme.Black
import com.example.fintrackui.ui.theme.LightGray
import com.example.fintrackui.ui.theme.Primary
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun CreateBudgetOne(navController: NavHostController) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = Color.White,
        darkIcons = true
    )

    val emailAdd = remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White).padding(start = 15.dp, end = 15.dp, top = 5.dp)) {

        Row(Modifier.padding(end = 20.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.frame_rounded_primary_back),
                contentDescription = "back_btn",
                modifier = Modifier
                    .height(23.dp)
                    .width(23.dp).clickable {
                        navController.popBackStack()
                    },
            )
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "1 of 3",
                    style = androidx.compose.material3.MaterialTheme.typography.bodySmall,
                    fontSize = 13.sp
                )
            }

        }

        Text(text = "Create your budget", textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Black)

        Box(Modifier.height(6.dp))

        Text(text = "Setup a spending budget whether it’s for a\nmonth, a week or even a trip.", textAlign = TextAlign.Center, fontSize = 12.sp, color = Black)

        Column(horizontalAlignment = Alignment.Start) {

            Box(Modifier.height(10.dp))

            Text(
                text = "Name of budget",
                style = MaterialTheme.typography.caption,
                color = Color(0xff161B26),
                textAlign = TextAlign.Start, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(bottom = 10.dp)
            )

            MyTextField(
                onInputChanged = { value->  emailAdd.value = value},
                inputText = emailAdd.value,
                name = "Enter the name of your budget here")

            Text(
                text = "Quick picks",
                style = MaterialTheme.typography.caption,
                color = Color(0xff161B26),
                textAlign = TextAlign.Start, modifier = Modifier.padding(bottom = 10.dp)
            )

            Row {
                QuickPicks()
            }
        }

        Spacer(modifier = Modifier.weight(3f))

        RoundedButton("Next", onClicked = {
            navController.navigate(Screen.CreateBudgetSecond.route)
            Log.d("Create Budget: ", "Next clicked")
        })

    }

}

@Composable
fun QuickPicks() {
    val options = listOf("Monthly Budget", "Weekly Budget")
    var selectedOption by remember { mutableStateOf(options[0]) }

    Row {
        options.forEach { option ->
            Row(modifier = Modifier.selectable(
                        selected = (option == selectedOption),
                        onClick = { selectedOption = option },
                        role = Role.RadioButton).padding(1.dp).width(120.dp)) {
                if ((option == selectedOption)) {
                    Button(
                        modifier = Modifier
                            .padding(1.dp)
                            .height(30.dp),
                        onClick = {selectedOption = option },
                        shape = RoundedCornerShape(60),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Primary,
                            contentColor = LightGray
                        )
                    ) {
                        Text(text = option, fontSize = 10.sp, color = Color.White)
                    }
                } else {
                    Button(
                        modifier = Modifier
                            .padding(1.dp)
                            .height(30.dp),
                        onClick = {selectedOption = option },
                        shape = RoundedCornerShape(60),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = LightGray,
                            contentColor = LightGray
                        )
                    ) {
                        Text(text = option, fontSize = 10.sp, color = Black)
                    }
                }
            }
        }
    }
}