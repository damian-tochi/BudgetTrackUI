package com.example.fintrackui.presentation.screens.bottom_nav_screens


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fintrackui.R
import com.example.fintrackui.model.HomeCardItems
import com.example.fintrackui.ui.theme.AccentColor
import com.example.fintrackui.ui.theme.Black
import com.example.fintrackui.ui.theme.Gray
import com.example.fintrackui.ui.theme.HomeHeader
import com.example.fintrackui.ui.theme.HomeYellow
import com.example.fintrackui.ui.theme.LightGray
import com.example.fintrackui.ui.theme.Primary
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

private const val SCROLL_ANIMATION_DURATION = 7_900L



@Composable
fun HomeScreen(navController: NavHostController) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = Color.White,
        darkIcons = true
    )
    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)) {

        HomeHeaderComponent()

        Column(
            modifier = Modifier.verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)) {

            TopPagerCarousel()

            Box(modifier = Modifier
                .padding(start = 16.dp, end = 15.dp, top = 5.dp, bottom = 5.dp)
                .fillMaxWidth()
                .border(BorderStroke(1.dp, Gray), shape = RoundedCornerShape(9.dp))
                .background(Color.White, shape = RoundedCornerShape(9.dp))) {

                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(12.dp)) {
                    Column (Modifier.weight(1f)) {
                        val header = buildAnnotatedString {
                            withStyle(
                                style = androidx.compose.ui.text.SpanStyle(
                                    color = Black,
                                    fontSize = 18.sp
                                )
                            ) {
                                append("your ")
                            }

                            withStyle(
                                style = androidx.compose.ui.text.SpanStyle(
                                    color = Color.Black,
                                    fontSize = 25.sp
                                )
                            ) {
                                append("Week")
                            }
                            withStyle(
                                style = androidx.compose.ui.text.SpanStyle(
                                    color = Black,
                                    fontSize = 18.sp
                                )
                            ) {
                                append("\nin ")
                            }
                            withStyle(
                                style = androidx.compose.ui.text.SpanStyle(
                                    color = AccentColor,
                                    fontSize = 28.sp
                                )
                            ) {
                                append("Money")
                            }

                        }

                        ClickableText(header) {
                            header.getStringAnnotations(it, it).firstOrNull()?.tag?.let {}
                        }

                        Text(
                            text = "See your past week in money",
                            style = MaterialTheme.typography.caption,
                            color = Color(0xff161B26),
                            textAlign = TextAlign.Start, modifier = Modifier.padding(bottom = 1.dp)
                        )

                    }

                    Image(
                        painter = painterResource(id = R.drawable.frame_right_arrow),
                        contentDescription = null,
                        Modifier.size(30.dp)
                    )
                }


            }


            Box(modifier = Modifier
                .padding(start = 16.dp, end = 15.dp, top = 5.dp, bottom = 5.dp)
                .fillMaxWidth()
                .border(BorderStroke(1.dp, Gray), shape = RoundedCornerShape(9.dp))
                .background(Color.White, shape = RoundedCornerShape(9.dp))) {

                Column {
                    Row(verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth().padding(start = 15.dp, end = 15.dp, top = 10.dp, bottom = 10.dp)) {

                        Text(text = "Recent Activities", fontSize = 13.sp, color = Black)

                        Button(modifier = Modifier
                            .padding(1.dp)
                            .height(30.dp),
                            onClick = {},
                            shape = RoundedCornerShape(60),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFCCE6E6),
                                contentColor = Color(0xFF008080)
                            )) {
                            Text(text = "View All", fontSize = 13.sp, color = Color(0xFF008080))
                        }
                    }

                    Text(
                        text = "Today, 14/07/2024",
                        style = MaterialTheme.typography.caption,
                        color = Color(0xFF61646C),
                        textAlign = TextAlign.Start, modifier = Modifier.padding(bottom = 1.dp, start = 15.dp, end = 15.dp)
                    )

                    Row(verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth().padding(start = 15.dp, end = 15.dp, top = 10.dp, bottom = 10.dp)) {

                        Column {
                            Row {
                                Image(
                                    painter = painterResource(id = R.drawable.frame_rounded_pie),
                                    contentDescription = null,
                                    Modifier.size(20.dp)
                                )

                                Box(Modifier.width(5.dp))

                                Column {
                                    Text(text = "Created a new budget “Trip to\nNairobi”", fontSize = 13.sp, color = Black)
                                    Text(text = "a day ago ", fontSize = 13.sp, color = Color(0xFF61646C))
                                }

                            }
                        }

                        Text(text = "₦200,000.00", fontSize = 13.sp, color = Black)

                    }

                    Row(verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth().padding(start = 15.dp, end = 15.dp, top = 5.dp, bottom = 10.dp)) {

                        Column {
                            Row {
                                Image(
                                    painter = painterResource(id = R.drawable.frame_jay_rounded),
                                    contentDescription = null,
                                    Modifier.size(20.dp)
                                )

                                Box(Modifier.width(5.dp))

                                Column {
                                    Text(text = "John Ogaga", fontSize = 13.sp, color = Black)
                                    Text(text = "Zenith Bank 12:03 AM", fontSize = 13.sp, color = Color(0xFF61646C))
                                }

                            }
                        }

                        Text(text = "₦10,000.00", fontSize = 13.sp, color = Black)
                    }

                    Text(
                        text = "Yesterday, 13/07/2024",
                        style = MaterialTheme.typography.caption,
                        color = Color(0xFF61646C),
                        textAlign = TextAlign.Start, modifier = Modifier.padding(bottom = 19.dp, start = 15.dp, end = 15.dp,)
                    )
                }
            }
        }
    }
}


@Composable
fun HomeHeaderComponent() {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 15.dp, end = 15.dp)) {

        Column {
            Text(
                text = "Hello, Jane ",
                style = MaterialTheme.typography.caption,
                color = Color(0xff161B26),
                textAlign = TextAlign.Start, modifier = Modifier.padding(bottom = 1.dp)
            )

            Text(
                text = "Your financial journey starts here.",
                style = MaterialTheme.typography.caption,
                color = Color(0xff161B26),
                textAlign = TextAlign.Start, modifier = Modifier.padding(bottom = 1.dp)
            )

        }

        Row {
            Image(
                painter = painterResource(id = R.drawable.frame_rounded_user),
                contentDescription = null,
                Modifier.size(20.dp)
            )

            Box(Modifier.width(10.dp))

            Image(
                painter = painterResource(id = R.drawable.frame_rounded_notify),
                contentDescription = null,
                Modifier.size(20.dp)
            )
        }

    }

}


@Composable
fun TopPagerCarousel() {
    HorizontalCardList()
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalCardList() {

    val pagerState = rememberPagerState()

    val cardItems = remember {
        listOf(
            HomeCardItems(
                "Account balance",
                "Manage Accounts",
                "1,000,500.55",
                0f,
                Color.White,
                "The total balance from your linked accounts",
                R.drawable.vault_half,
                Primary,
                Color.White,
                Color.White
            ),
            HomeCardItems(
                "Total savings",
                "View Savings",
                "50,530.00",
                0.5f,
                Color.White,
                "You need N250,000 to meet your targets.",
                R.drawable.piggy_bank_piggy,
                HomeYellow,
                Color.White,
                Color.White
            ),
            HomeCardItems(
                "Monthly budget",
                "Manage Budget",
                "1,000,500.55",
                0.8f,
                AccentColor,
                "left out of N200,000,000 budgeted.",
                R.drawable.target_top_half,
                Color.White,
                Black,
                Primary
            ),
            HomeCardItems(
                "Total expenses",
                "View expenses",
                "1,000,500.55",
                1.0f,
                AccentColor,
                "spent in the last 7 days",
                R.drawable.cash_1,
                HomeHeader,
                Black,
                Primary
            )
        )
    }

    val isDraggedState = pagerState.interactionSource.collectIsDraggedAsState()


    Column(modifier = Modifier.fillMaxSize().padding(6.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start) {

        HorizontalPager(
            state = pagerState,
            count = cardItems.size,
            itemSpacing = 10.dp,
            modifier = Modifier.background(Color.White)) { page ->

            Card(
                modifier = Modifier.fillMaxWidth().height(150.dp).padding(10.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                colors = CardDefaults.cardColors(containerColor = cardItems[page].color, contentColor = cardItems[page].contentColor),) {

                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.Start) {

                    Row(verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth().padding(start = 15.dp, end = 15.dp, top = 15.dp)) {

                        Text(text = cardItems[page].title, fontSize = 13.sp, color = cardItems[page].contentColor)

                        Button(modifier = Modifier
                            .padding(1.dp)
                            .height(30.dp),
                            onClick = {},
                            shape = RoundedCornerShape(60),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = cardItems[page].leftBtnColor,
                                contentColor = cardItems[page].color
                            )) {
                            Text(text = cardItems[page].leftAction, fontSize = 13.sp, color = cardItems[page].color)
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth().padding(start = 15.dp, end = 15.dp, top = 10.dp)) {

                        Text(text = "₦", fontSize = 13.sp, color = cardItems[page].contentColor)

                        Box(Modifier.width(2.dp))

                        Text(text = cardItems[page].sum, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = cardItems[page].contentColor)

                    }

                    Surface(modifier = Modifier.background(cardItems[page].color)) {

                        Row (modifier = Modifier.fillMaxWidth().background(cardItems[page].color),horizontalArrangement = Arrangement.Absolute.Right){
                            Image(
                                painter = painterResource(id = cardItems[page].img),
                                contentDescription = null,
                                Modifier.size(100.dp)
                            )
                        }

                        Column(modifier = Modifier.padding(start = 15.dp, top = 10.dp, end = 15.dp,), verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.Start) {

                            Text(text = cardItems[page].description, fontSize = 13.sp, color = cardItems[page].contentColor)

                            if (cardItems[page].progress > 0f) {

                                Box(Modifier.height(10.dp))

                                LinearProgressIndicator(
                                    progress = cardItems[page].progress,
                                    modifier = Modifier.fillMaxWidth().height(3.dp),
                                    color = cardItems[page].progressColor,
                                    trackColor = LightGray,
                                    strokeCap = StrokeCap.Round
                                )
                            }
                        }
                    }
                }
            }
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier.padding(3.dp).align(Alignment.CenterHorizontally),
            activeColor = Primary,
            inactiveColor = Gray,
            indicatorWidth = 25.dp,
            indicatorHeight = 2.dp,
            spacing = 2.dp
        )

    }

    LaunchedEffect(isDraggedState) {
        snapshotFlow { isDraggedState.value }
            .collectLatest { isDragged ->
                if (!isDragged) {
                    while (true) {
                        delay(SCROLL_ANIMATION_DURATION)
                        runCatching {
                            pagerState.animateScrollToPage(pagerState.currentPage.inc() % pagerState.pageCount)
                        }
                    }
                }
            }
    }

}

@Preview
@Composable
fun homeScreen() {

    HomeHeaderComponent()

    HorizontalCardList()
}