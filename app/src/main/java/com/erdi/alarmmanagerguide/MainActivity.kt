package com.erdi.alarmmanagerguide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.erdi.alarmmanagerguide.ui.theme.AlarmManagerGuideTheme
import java.time.LocalDateTime

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val scheduler = AndroidAlarmScheduler(this)
        var alarmItem: AlarmItem? = null
        setContent {
            AlarmManagerGuideTheme {
                var secondsText by remember {
                    mutableStateOf("")
                }
                var message by remember {
                    mutableStateOf("")
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    OutlinedTextField(
                        value = secondsText,
                        onValueChange = { secondsText = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(text = "Trigger alarm in seconds")
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = message,
                        onValueChange = { message = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(text = "Message")
                        }
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(onClick = {
                            alarmItem = AlarmItem(
                                time = LocalDateTime.now()
                                    .plusSeconds(secondsText.toLong()),
                                message = message
                            )
                            alarmItem?.let(scheduler::schedule)
                            secondsText = ""
                            message = ""
                        }) {
                            Text(text = "Schedule")
                        }
                        Button(onClick = {
                            alarmItem?.let(scheduler::cancel)
                        }) {
                            Text(text = "Cancel")
                        }
                    }
                }
            }
        }
    }
}