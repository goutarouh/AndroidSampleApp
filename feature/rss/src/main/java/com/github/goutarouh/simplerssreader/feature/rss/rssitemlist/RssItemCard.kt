package com.github.goutarouh.simplerssreader.feature.rss.rssitemlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.github.goutarouh.simplerssreader.core.repository.model.rss.RssItem
import com.github.goutarouh.simplerssreader.core.ui.annotation.DayAndNightPreviews
import com.github.goutarouh.simplerssreader.core.ui.theme.SrrTheme

@Composable
fun RssItemCard(
    rssItem: RssItem,
    onCardClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .clip(shape = RoundedCornerShape(size = 12.dp))
            .clickable {
                onCardClick(rssItem.pageLink)
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 4.dp)
        ) {
            Text(
                text = rssItem.title,
                modifier = Modifier.align(Alignment.CenterStart),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@DayAndNightPreviews
@Composable
fun PreviewRssItemCard(
    @PreviewParameter(PreviewRssItemProvider::class) rssItem: RssItem
) {
    SrrTheme {
        Surface {
            RssItemCard(
                rssItem = rssItem,
                onCardClick = {},
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}


class PreviewRssItemProvider: PreviewParameterProvider<RssItem> {
    override val values: Sequence<RssItem>
        get() = sequenceOf(
            RssItem("title"),
            RssItem("title".repeat(30))
        )
}