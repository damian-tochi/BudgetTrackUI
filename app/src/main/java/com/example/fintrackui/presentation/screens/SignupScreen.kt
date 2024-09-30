package com.example.fintrackui.presentation.screens


import android.media.Image
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.MaterialTheme
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.fintrackui.R
import com.example.fintrackui.navigation.Screen
import com.example.fintrackui.presentation.components.MyTextField
import com.example.fintrackui.presentation.components.RoundedButton
import com.example.fintrackui.ui.theme.Primary
import com.google.accompanist.systemuicontroller.rememberSystemUiController



@OptIn(ExperimentalTextApi::class)
@Composable
fun SignUpScreen(
    navController: NavController, ) {

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
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,) {

            Spacer(modifier = Modifier.weight(1.0f))

            MainContent()

            Spacer(modifier = Modifier.weight(2f))

            RoundedButton("Create an Account", onClicked = {
                navController.navigate(Screen.CheckEmail.route) { launchSingleTop = true }
                Log.d("SplashScreen: ", "Create account clicked")
            })

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

        }
    })

}

@Composable
fun MainContent() {
    val firstName = remember { mutableStateOf("") }
    val emailAdd = remember { mutableStateOf("") }
    val refCode = remember { mutableStateOf("") }

    Column (modifier = Modifier.padding(horizontal = 16.dp).background(Color.White),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top) {


        Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Let’s get started!",
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.Bold,
                fontSize = 23.sp,
                modifier = Modifier.padding(end = 13.dp)
            )

            Image(painter = painterResource(id = R.drawable.party_opper), contentDescription = null, Modifier.size(20.dp))

        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Join us and start managing your finances with Fintrack today.",
            style = MaterialTheme.typography.caption,
            color = Color(0xff333741),
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            modifier = Modifier.padding(bottom = 6.dp)
        )

        Spacer(modifier = Modifier.height(13.dp))

        Text(
            text = "First & Last Name",
            style = MaterialTheme.typography.caption,
            color = Color(0xff161B26),
            textAlign = TextAlign.Start, modifier = Modifier
                .padding(bottom = 10.dp)
        )
        MyTextField(
            onInputChanged = {value->  firstName.value = value},
            inputText = firstName.value,
            name = "e.g John Doe")

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Email address",
            style = MaterialTheme.typography.caption,
            color = Color(0xff161B26),
            textAlign = TextAlign.Start, modifier = Modifier
                .padding(bottom = 7.dp)
        )

        MyTextField(
            onInputChanged = {value->  emailAdd.value = value},
            inputText = emailAdd.value,
            name = "e.g email@mail.com")


        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Enter a referral code(optional)",
            style = MaterialTheme.typography.caption,
            color = Color(0xff161B26),
            textAlign = TextAlign.Start, modifier = Modifier
                .padding(bottom = 7.dp)
        )

        MyTextField(
            onInputChanged = {value->  refCode.value = value},
            inputText = refCode.value,
            name = "e.g ABC123")

    }
}

@Preview
@Composable
fun MainContentPreview() {
    MainContent()
}