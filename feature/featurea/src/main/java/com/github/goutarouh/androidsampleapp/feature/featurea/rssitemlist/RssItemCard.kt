package com.github.goutarouh.androidsampleapp.feature.featurea.rssitemlist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.goutarouh.androidsampleapp.core.repository.model.rss.RssItem

@Composable
fun RssItemCard(
    rssItem: RssItem,
    onCardClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(size = 24.dp))
            .background(color = Color(0xFFf8f8ff))
            .clickable {
                onCardClick(rssItem.pageLink)
            }
            .padding(horizontal = 16.dp)
            .heightIn(min = 80.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = rssItem.title,
            modifier = Modifier.align(Alignment.Center),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
fun PreviewRssItemCard() {
    RssItemCard(
        rssItem = RssItem("title".repeat(30), "link"),
        onCardClick = {},
        modifier = Modifier
            .height(100.dp)
            .width(200.dp)
    )
}
