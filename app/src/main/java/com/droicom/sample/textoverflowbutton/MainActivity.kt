package com.droicom.sample.textoverflowbutton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.droicom.sample.textoverflowbutton.data.sampleButtonsText
import com.droicom.sample.textoverflowbutton.ui.PhonePreview
import com.droicom.sample.textoverflowbutton.ui.TabletPreview
import com.droicom.sample.textoverflowbutton.ui.theme.TextOverflowButtonTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TextOverflowButtonTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FlowLayoutButtons(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowLayoutButtons(modifier: Modifier = Modifier) {
    FlowRow(
        modifier = modifier.padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        maxItemsInEachRow = Int.MAX_VALUE // Let FlowRow decide based on available space
    ) {
        sampleButtonsText.forEach { buttonText ->
            Button(onClick = {}) {
                Text(
                    text = buttonText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@PhonePreview
@Composable
fun DynamicBoxWithButtonPhonePreview() {
    TextOverflowButtonTheme {
        FlowLayoutButtons()
    }
}

@TabletPreview
@Composable
fun DynamicBoxWithButtonTabletPreview() {
    TextOverflowButtonTheme {
        FlowLayoutButtons()
    }
}
