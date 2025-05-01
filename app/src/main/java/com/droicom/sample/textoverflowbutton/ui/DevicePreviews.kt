package com.droicom.sample.textoverflowbutton.ui

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "Phone Light",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Phone Dark",
    showBackground = true,
    backgroundColor = 0xFF121212,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
annotation class PhonePreview

@Preview(
    name = "Tablet Light",
    device = "spec:width=1280dp,height=800dp",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Tablet Dark",
    device = "spec:width=1280dp,height=800dp",
    showBackground = true,
    backgroundColor = 0xFF121212,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
annotation class TabletPreview