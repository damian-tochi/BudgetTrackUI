package com.example.fintrackui.presentation.components

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.fintrackui.R
import com.example.fintrackui.ui.theme.Gray
import com.example.fintrackui.ui.theme.Primary
import java.time.LocalDateTime
import java.util.Calendar



@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialogCalendarDatePicker(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit) {
    val state = rememberDateRangePickerState()
    val scrollState = rememberScrollState()

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = Modifier.height(650.dp),
        containerColor = Color.White,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        content = {

            Surface(
                modifier = Modifier.fillMaxWidth().height(600.dp),
                color = Color.White,
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)) {

                Column(
                    modifier = Modifier.padding(16.dp).verticalScroll(scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally) {

                    Text(
                        text = "Pick a start and end date", style = TextStyle(
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black
                        ),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Box(modifier = Modifier
                        .padding(start = 1.dp, end = 1.dp, top = 0.dp, bottom = 5.dp)
                        .fillMaxWidth()
                        .height(430.dp)
                        .border(BorderStroke(1.dp, Gray), shape = RoundedCornerShape(9.dp))
                        .background(Color.White, shape = RoundedCornerShape(9.dp))) {

                        DateRangePickerSample(state)
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Column(modifier = Modifier.weight(1f).padding(end = 5.dp)) {
                            Text(
                                text = "Start Date",
                                style = MaterialTheme.typography.caption,
                                color = Color(0xff161B26),
                                textAlign = TextAlign.Start, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(bottom = 10.dp)
                            )

                            Box(modifier = Modifier
                                .padding(start = 1.dp, end = 1.dp, top = 0.dp, bottom = 5.dp)
                                .fillMaxWidth()
                                .height(50.dp)
                                .border(BorderStroke(1.dp, Gray), shape = RoundedCornerShape(9.dp))
                                .background(Color.White, shape = RoundedCornerShape(9.dp))) {

                                (if(state.selectedStartDateMillis!=null) state.selectedStartDateMillis?.let { getFormattedDate(it) } else "")?.let { Text(text = it, fontSize = 12.sp) }

                            }

                        }

                        Column(modifier = Modifier.weight(1f).padding(start = 5.dp)) {
                            Text(
                                text = "End Date",
                                style = MaterialTheme.typography.caption,
                                color = Color(0xff161B26),
                                textAlign = TextAlign.Start, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(bottom = 10.dp)
                            )

                            Box(modifier = Modifier
                                .padding(start = 1.dp, end = 1.dp, top = 0.dp, bottom = 5.dp)
                                .fillMaxWidth()
                                .height(50.dp)
                                .border(BorderStroke(1.dp, Gray), shape = RoundedCornerShape(9.dp))
                                .background(Color.White, shape = RoundedCornerShape(9.dp))) {

                                (if(state.selectedEndDateMillis!=null) state.selectedEndDateMillis?.let { getFormattedDate(it) } else "")?.let { Text(text = it, fontSize = 12.sp) }

                            }
                        }
                    }

                    RoundedButton(text = "Save Date", onClicked = onConfirmation)
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
        }
    )

}


fun getFormattedDate(timeInMillis: Long): String{
    val calender = Calendar.getInstance()
    calender.timeInMillis = timeInMillis
    val dateFormat = SimpleDateFormat("dd/MM/yyyy")
    return dateFormat.format(calender.timeInMillis)
}


@SuppressLint("NewApi")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePickerSample(state: DateRangePickerState){
    val calendar = Calendar.getInstance()
    val currentMonth = LocalDateTime.now().month
    val currentYear = calendar.get(Calendar.YEAR)

    DateRangePicker(state,
        modifier = Modifier,
        headline = {
            Row(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {

                Row {
                    Text(
                        text = currentMonth.getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.getDefault()), style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Primary
                        ),
                        textAlign = TextAlign.Center
                    )

                    Box(Modifier.width(6.dp))

                    Text(
                        text = "$currentYear", style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black
                        ),
                        textAlign = TextAlign.Center
                    )

                }

                Row {
                    Image(
                        painter = painterResource(id = R.drawable.chevron_left_black),
                        contentDescription = "logo",
                        modifier = Modifier.size(20.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.chevron_right_black),
                        contentDescription = "logo",
                        modifier = Modifier.size(20.dp)
                    )

                }
            }
        },
        showModeToggle = false,
        colors = DatePickerDefaults.colors(
            containerColor = Color.White,
            titleContentColor = Color.Black,
            headlineContentColor = Color.Black,
            weekdayContentColor = Color.Gray,
            subheadContentColor = Color.Black,
            yearContentColor = Color.Black,
            currentYearContentColor = Color.Red,
            selectedYearContainerColor = Color.Red,
            disabledDayContentColor = Color.Gray,
            todayDateBorderColor = Primary,
            dayInSelectionRangeContainerColor = Color(0xFFCCE6E6),
            dayInSelectionRangeContentColor = Color.White,
            selectedDayContainerColor = Primary
        )
    )
}

