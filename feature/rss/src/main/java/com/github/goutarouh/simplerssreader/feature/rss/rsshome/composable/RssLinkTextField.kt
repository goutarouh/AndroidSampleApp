package com.github.goutarouh.simplerssreader.feature.rss.rsshome.composable

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.github.goutarouh.simplerssreader.feature.rss.R
import com.github.goutarouh.simplerssreader.feature.rss.rsshome.model.RssLinkInputStatus
import com.github.goutarouh.simplerssreader.feature.rss.rsshome.model.RssLinkInputText

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
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

    BackHandler(isOnFocus) {
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
                Text(
                    text = stringResource(id = R.string.rss_home_text_field_outline)
                )
            },
            isError = field.isError,
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
            trailingIcon = {
                if (field.input.isNotEmpty() && isOnFocus) {
                    IconButton(onClick = {
                        field = RssLinkInputText(input = "")
                    }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = null
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    when (field.getStatus()) {
                        RssLinkInputStatus.Valid -> {
                            searchClick(field.input)
                        }
                        RssLinkInputStatus.ShouldNotEmpty -> {}
                        RssLinkInputStatus.ShouldStartWithHttps -> {
                            field = field.copy(isError = true)
                        }
                    }
                }
            )
        )
    }
}

@Preview
@Composable
fun PreviewRssLinkTextField() {
    RssLinkTextField(searchClick = {})
}
