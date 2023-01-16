package com.github.goutarouh.androidsampleapp.feature.rss.rsshome

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.github.goutarouh.androidsampleapp.core.repository.model.rss.Rss
import com.github.goutarouh.androidsampleapp.core.ui.theme.BlueGray50
import com.github.goutarouh.androidsampleapp.core.ui.theme.BlueGray800
import java.time.LocalDateTime

@Composable
fun RssCard(
    rss: Rss,
    onCardClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .clip(shape = RoundedCornerShape(size = 24.dp))
            .background(color = BlueGray50)
            .clickable {
                onCardClick(rss.rssLink)
            }
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .heightIn(80.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = rss.imageLink,
            contentDescription = null,
            modifier = Modifier
                .clip(shape = RoundedCornerShape(size = 8.dp))
                .size(60.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = rss.title,
            modifier = Modifier.weight(1f),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun PreviewRssCard() {
    RssCard(rss = Rss("a".repeat(1000),"", "", listOf(), false, LocalDateTime.now()), onCardClick = {})
}