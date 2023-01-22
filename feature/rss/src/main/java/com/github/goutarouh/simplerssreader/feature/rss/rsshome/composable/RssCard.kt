package com.github.goutarouh.simplerssreader.feature.rss.rsshome.composable

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.github.goutarouh.simplerssreader.core.repository.model.rss.Rss
import java.time.LocalDateTime

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RssCard(
    rss: Rss,
    onCardClick: (String) -> Unit,
    onCardLongClick: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .clip(shape = RoundedCornerShape(size = 24.dp))
            .background(color = MaterialTheme.colors.primary)
            .clickable {
                onCardClick(rss.rssLink)
            }
            .combinedClickable(
                onClick = {
                          onCardClick(rss.rssLink)
                },
                onLongClick = {
                      onCardLongClick(rss.rssLink, rss.title)
                }
            )
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
    RssCard(rss = Rss("a".repeat(1000),"", "", listOf(), false, LocalDateTime.now()), onCardClick = {}, onCardLongClick = { link, title ->})
}