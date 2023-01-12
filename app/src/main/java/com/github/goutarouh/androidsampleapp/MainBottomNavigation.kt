package com.github.goutarouh.androidsampleapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.goutarouh.androidsampleapp.feature.featurea.RSS_HOME_ROUTE
import com.github.goutarouh.androidsampleapp.feature.featureb.featureBRoute
import com.github.goutarouh.androidsampleapp.feature.featurec.featureCRoute

enum class BottomItem(
    val displayName: String,
    val route: String,
) {
    RSS_HOME("RSS", RSS_HOME_ROUTE),
    FEATURE_B("feature-b", featureBRoute),
    FEATURE_C("feature-c", featureCRoute);

    companion object {
        fun ordered(): List<BottomItem> {
            return listOf(RSS_HOME, FEATURE_B, FEATURE_C)
        }
    }
}

@Composable
fun MainBottomNavigation(
    navigate: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            BottomItem.ordered().forEach {
                BottomNavItem(bottomItem = it) {
                    navigate(it.route)
                }
            }
        }
    }
}

@Composable
private fun BottomNavItem(
    bottomItem: BottomItem,
    navigate: () -> Unit
) {
    Column(
        modifier = Modifier.clickable {
            navigate()
        }
    ) {
        Text(text = bottomItem.displayName)
    }
}