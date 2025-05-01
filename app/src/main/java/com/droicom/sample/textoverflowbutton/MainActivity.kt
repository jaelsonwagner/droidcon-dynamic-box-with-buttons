package com.droicom.sample.textoverflowbutton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.droicom.sample.textoverflowbutton.data.ButtonText
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
                    DynamicBoxWithButton(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun DynamicBoxWithButton(modifier: Modifier = Modifier) {
    var isOverflowing by remember { mutableStateOf(false) }
    if (!isOverflowing) {
        HorizontalBox(
            buttonsText = sampleButtonsText,
            modifier = modifier,
            onTextOverflow = { isOverflowing = it }
        )
    } else {
        VerticalBox(buttonsText = sampleButtonsText, modifier = modifier)
    }
}

@Composable
fun VerticalBox( buttonsText: List<ButtonText>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        buttonsText.forEach {
            Button(onClick = {}) {
                Text(
                    text = it,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Composable
fun HorizontalBox(
    buttonsText: List<ButtonText>,
    modifier: Modifier = Modifier,
    onTextOverflow: (Boolean) -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        buttonsText.forEach { OverflowAwareButton(it, onTextOverflow) }
    }
}

@Composable
fun OverflowAwareButton(
    text: String,
    onTextOverflow: (Boolean) -> Unit
) {
    Button(onClick = {}) {
        Text(
            text = text,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            onTextLayout = { textLayoutResult ->
                onTextOverflow(textLayoutResult.hasVisualOverflow)
            }
        )
    }
}

@PhonePreview
@Composable
fun DynamicBoxWithButtonPhonePreview() {
    TextOverflowButtonTheme {
        DynamicBoxWithButton()
    }
}

@TabletPreview
@Composable
fun DynamicBoxWithButtonTabletPreview() {
    TextOverflowButtonTheme {
        DynamicBoxWithButton()
    }
}
