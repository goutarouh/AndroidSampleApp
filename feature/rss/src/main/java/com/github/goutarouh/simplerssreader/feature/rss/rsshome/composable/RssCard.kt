package com.github.goutarouh.simplerssreader.feature.rss.rsshome.composable

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.github.goutarouh.simplerssreader.core.repository.model.rss.Rss
import com.github.goutarouh.simplerssreader.core.ui.annotation.DayAndNightPreviews
import com.github.goutarouh.simplerssreader.core.ui.annotation.MultiFontScalePreviews
import com.github.goutarouh.simplerssreader.core.ui.theme.SrrTheme
import com.github.goutarouh.simplerssreader.feature.rss.R

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
        val iconModifier = Modifier
            .clip(shape = RoundedCornerShape(size = 8.dp))
            .size(60.dp)
        if (rss.imageLink.isEmpty()) {
            Icon(
                painter = painterResource(id = R.drawable.rss),
                contentDescription = null,
                tint = MaterialTheme.colors.onPrimary,
                modifier = iconModifier
            )
        } else {
            AsyncImage(
                model = rss.imageLink,
                contentDescription = null,
                modifier = iconModifier
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = rss.title,
            modifier = Modifier.weight(1f),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            fontWeight = if (rss.hasUnreadItems) FontWeight.Bold else null,
            fontSize = if (rss.hasUnreadItems) 16.sp else 15.sp
        )
    }
}


@DayAndNightPreviews
@MultiFontScalePreviews
@Composable
fun PreviewRssCard(
    @PreviewParameter(PreviewRssProvider::class) rss: Rss
) {
    SrrTheme {
        Surface {
            RssCard(
                rss = rss,
                onCardClick = {},
                onCardLongClick = { _, _ -> },
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

class PreviewRssProvider: PreviewParameterProvider<Rss> {
    override val values: Sequence<Rss>
        get() = sequenceOf(
            Rss(title = "PreviewRssProvider", unReadItemCount = 0),
            Rss(title = "PreviewRssProvider".repeat(5), unReadItemCount = 1),
        )
}