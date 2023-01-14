package com.github.goutarouh.androidsampleapp.feature.rss.rsshome.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.github.goutarouh.androidsampleapp.feature.rss.rsshome.model.RssLinkInputText

@Composable
fun RssLinkTextField(
    onCardClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var input by remember { mutableStateOf(RssLinkInputText("")) }
    Row(modifier) {
        TextField(
            value = input.input,
            onValueChange = { input = RssLinkInputText(it) },
            singleLine = true,
            modifier = Modifier.weight(1f)
        )
        IconButton(
            onClick = {
                if (input.isValid()) {
                    onCardClick(input.input)
                }
            }
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        }
    }
}