package com.example.fintrackui.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fintrackui.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialogSimple(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        dragHandle = null,
        content = {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color.White,
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(top = 16.dp, start = 15.dp, end = 15.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween) {
                        Column {
                            Text(
                                text = "Create an account", style = TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black
                                ),
                                textAlign = TextAlign.Center
                            )

                        }
                        Column(modifier = Modifier.padding(end = 8.dp)) {
                            Image(
                                painter = painterResource(id = R.drawable.close_modal),
                                contentDescription = "logo",
                                modifier = Modifier.size(30.dp).clickable(onClick = onDismissRequest)
                            )
                        }

                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Box( modifier = Modifier.padding(start = 15.dp, end = 15.dp)) {AcceptTermsText()}
                    Spacer(modifier = Modifier.height(15.dp))
                    RoundedButton(text = "Accept and Continue", onClicked = onConfirmation)
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
        }
    )
}