package com.github.goutarouh.androidsampleapp.feature.rss.rsshome.composable

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import com.github.goutarouh.androidsampleapp.core.ui.theme.Red600
import com.github.goutarouh.androidsampleapp.feature.rss.rsshome.model.RssLinkInputText

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RssLinkTextField(
    searchClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var field by remember { mutableStateOf(RssLinkInputText()) }
    var isOnFocus by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    
    BackHandler {
        focusManager.clearFocus()
    }

    Row(modifier) {
        OutlinedTextField(
            value = field.input,
            onValueChange = { field = RssLinkInputText(it) },
            singleLine = true,
            modifier = Modifier
                .weight(1f)
                .focusRequester(focusRequester)
                .onFocusChanged {
                    isOnFocus = it.hasFocus
                },
            placeholder = {
                Text(text = "Rssのリンク")
            },
            label = {
                Text(
                    text = field.label,
                    color = Red600
                )
            },
            leadingIcon = {
                if (isOnFocus) {
                    IconButton(onClick = {
                        focusManager.clearFocus()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                } else {
                    IconButton(onClick = {
                        keyboardController?.show()
                        focusRequester.requestFocus()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    }
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors()
        )
    }
}

@Preview
@Composable
fun PreviewRssLinkTextField() {
    RssLinkTextField(searchClick = {})
}
