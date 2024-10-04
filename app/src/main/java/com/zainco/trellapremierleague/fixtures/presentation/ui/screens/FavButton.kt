package com.zainco.trellapremierleague.fixtures.presentation.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    color: Color = Color(0xffE91E63),
    isFavorite: Boolean,
    onFavClicked: () -> Unit,
) {

    var isFavorite by remember { mutableStateOf(isFavorite) }
    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = {
            isFavorite = !isFavorite
            onFavClicked()
        },
    ) {
        Icon(
            tint = color,
            modifier = modifier.graphicsLayer {
                scaleX = 1.1f
                scaleY = 1.1f
            },
            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = null
        )
    }
}