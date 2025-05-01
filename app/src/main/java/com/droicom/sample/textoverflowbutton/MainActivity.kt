package com.droicom.sample.textoverflowbutton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.tooling.preview.Preview
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
        HorizontalBox(modifier = modifier, onTextOverflow = { isOverflowing = it })
    } else {
        VerticalBox(modifier = modifier)
    }
}

@Composable
fun VerticalBox(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Button(onClick = {}) {
            Text(
                text = "Very long text that might overflow",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Button(onClick = {}) {
            Text(
                text = "Another Button",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
fun HorizontalBox(modifier: Modifier = Modifier, onTextOverflow: (Boolean) -> Unit) {
    Row(modifier = modifier) {
        OverflowAwareButton(
            text = "Very long text that might overflow",
            onTextOverflow = onTextOverflow
        )
        OverflowAwareButton(text = "Another Button", onTextOverflow = onTextOverflow)
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

@Preview(showBackground = true)
@Composable
fun DynamicBoxWithButtonPreview() {
    TextOverflowButtonTheme {
        DynamicBoxWithButton()
    }
}