package com.example.fintrackui.presentation.screens.create_budget_sequence

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.fintrackui.presentation.components.AlertDialogCalendarDatePicker
import com.example.fintrackui.presentation.components.AlertDialogSimple
import com.example.fintrackui.presentation.components.MyTextField
import com.example.fintrackui.presentation.components.RoundedButton
import com.example.fintrackui.ui.theme.Black
import com.example.fintrackui.ui.theme.Gray
import com.example.fintrackui.ui.theme.LightGray
import com.example.fintrackui.ui.theme.Primary
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreateBudgetThree(navController: NavHostController) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = Color.White,
        darkIcons = true
    )

    val emailAdd = remember { mutableStateOf("") }

    var showDialog by remember { mutableStateOf(false) }

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
                    text = "Budget preview",
                    style = androidx.compose.material3.MaterialTheme.typography.bodySmall,
                    fontSize = 13.sp
                )
            }

        }

        Text(text = "Create your budget", textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Black)

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

            Box(Modifier.height(5.dp))

            Text(
                text = "Set a budget amount",
                style = MaterialTheme.typography.caption,
                color = Color(0xff161B26),
                textAlign = TextAlign.Start, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(bottom = 10.dp)
            )

            MyTextField(
                onInputChanged = { value->  emailAdd.value = value},
                inputText = emailAdd.value,
                name = "₦ 200,000")

            Box(Modifier.height(5.dp))

            Text(
                text = "Cycle of budget",
                style = MaterialTheme.typography.caption,
                color = Color(0xff161B26),
                textAlign = TextAlign.Start, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(bottom = 10.dp)
            )

            Box(modifier = Modifier
                .padding(start = 1.dp, end = 1.dp, top = 0.dp, bottom = 5.dp)
                .fillMaxWidth()
                .height(55.dp)
                .clickable {
                    showDialog = true
                }
                .border(BorderStroke(1.dp, Gray), shape = RoundedCornerShape(9.dp))
                .background(Color.White, shape = RoundedCornerShape(9.dp))) {

                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxSize().padding(14.dp)) {

                    Text(
                        text = "Pick a start and end date",
                        style = MaterialTheme.typography.caption,
                        color = Color.Gray,
                        textAlign = TextAlign.Start, fontWeight = FontWeight.Normal
                    )

                    Image(
                        painter = painterResource(R.drawable.iconly_calendar),
                        contentDescription = "back_btn",
                        modifier = Modifier
                            .height(23.dp)
                            .width(23.dp)
                    )
                }
            }

            Box(Modifier.height(5.dp))


        }

        Spacer(modifier = Modifier.weight(3f))

        RoundedButton("Next", onClicked = {
            navController.navigate(Screen.CheckEmailOtp.route) { launchSingleTop = true }
            Log.d("Create Budget: ", "Next clicked")
        })


        if (showDialog) {
            AlertDialogCalendarDatePicker(onConfirmation = {
                showDialog = false
                navController.navigate(Screen.SignUp.route) { launchSingleTop = true }
            }, onDismissRequest = {
                showDialog = false
            })
        }

    }

}
