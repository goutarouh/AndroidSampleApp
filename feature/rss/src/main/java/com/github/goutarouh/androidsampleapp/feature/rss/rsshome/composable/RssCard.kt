package com.github.goutarouh.androidsampleapp.feature.rss.rsshome

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.github.goutarouh.androidsampleapp.core.repository.model.rss.Rss
import com.github.goutarouh.androidsampleapp.core.ui.theme.BlueGray50

@Composable
fun RssCard(
    rss: Rss,
    onCardClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(size = 24.dp))
            .background(color = BlueGray50)
            .clickable {
                onCardClick(rss.rssLink)
            }
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .heightIn(80.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = rss.title,
            modifier = Modifier.align(Alignment.CenterStart),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
    }
}