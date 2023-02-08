package com.github.goutarouh.simplerssreader.core.ui.annotation

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "light mode",
    group = "ui mode",
    uiMode = UI_MODE_NIGHT_NO
)
@Preview(
    name = "dark mode",
    group = "ui mode",
    uiMode = UI_MODE_NIGHT_YES
)
annotation class DayAndNightPreviews()

