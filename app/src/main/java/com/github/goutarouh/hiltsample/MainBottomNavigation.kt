package com.github.goutarouh.hiltsample

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.goutarouh.feature.featurea.featureARoute
import com.github.goutarouh.feature.featureb.featureBRoute

enum class BottomItem(
    val displayName: String,
    val route: String,
) {
    FEATURE_A("feature-a", featureARoute),
    FEATURE_B("feature-b", featureBRoute);

    companion object {
        fun ordered(): List<BottomItem> {
            return listOf(FEATURE_A, FEATURE_B)
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