package com.example.fintrackui.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.example.fintrackui.R
import com.example.fintrackui.model.OnboardContent
import com.example.fintrackui.ui.theme.Black
import com.example.fintrackui.ui.theme.Gray
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest


private const val SCROLL_ANIMATION_DURATION = 5_000L

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalViewPager(modifier: Modifier = Modifier) {

    val pagerState = rememberPagerState()
    val pages: List<OnboardContent> = listOf(
        OnboardContent(
            "Track Your \nExpenses",
            "Get insights into where your money goes and\nmake informed financial decisions.",
            R.drawable.trading_app_coin
        ),
        OnboardContent(
            "Set Savings \nGoals",
            "Whether it’s for a vacation, a new car, or an\nemergency fund, we help you stay on track.",
            R.drawable.growing_money
        ),
        OnboardContent(
            "Set Savings \nGoals",
            "Access detailed reports and analytics to make\nbetter financial choices.",
            R.drawable.frame_phone_coin
        )
    )

    val isDraggedState = pagerState.interactionSource.collectIsDraggedAsState()
    val indicatorCount = pages.size
    val spacing = 8.dp
    val totalSpacing = (indicatorCount - 1) * 22.dp
    val availableWidth = LocalConfiguration.current.screenWidthDp.dp - totalSpacing

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(Color.White)) {

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            activeColor = Black,
            inactiveColor = Gray,
            indicatorWidth = availableWidth / indicatorCount,
            indicatorHeight = 5.dp,
            spacing = spacing
        )

        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(400.dp)) {

            HorizontalPager(
                state = pagerState,
                count = pages.size,
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color.White)
            ) { page ->

                Box(modifier = modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(Color.White),
                ) {

                    Column {
                        Text(
                            text = pages[page].header,
                            color = Color.Black,
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                            textAlign = TextAlign.Start
                        )
                        Box(Modifier.height(9.dp))

                        Text(
                            text = pages[page].body,
                            color = Color.Black,
                            textAlign = TextAlign.Start,
                            style = TextStyle(fontSize = 10.sp)
                        )

                        Box(Modifier.height(20.dp))

                        Image(
                            painter = painterResource(id = pages[page].img),
                            contentDescription = "logo",
                            modifier = Modifier
                                .aspectRatio(
                                    ratio = painterResource(id = pages[page].img).intrinsicSize.height /
                                            painterResource(id = pages[page].img).intrinsicSize.width
                                )
                                .fillMaxWidth()
                                .fillMaxHeight(),
                        )
                    }
                }
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
    }
}


@Preview
@Composable
fun OnboardPreview() {
    HorizontalViewPager()
}
