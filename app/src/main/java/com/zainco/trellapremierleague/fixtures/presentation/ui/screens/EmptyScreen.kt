package com.zainco.trellapremierleague.fixtures.presentation.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.zainco.trellapremierleague.fixtures.presentation.TeamViewModel

@Composable
fun ShowEmptyScreen(viewModel: TeamViewModel) {
    AnimatedVisibility(visible = viewModel.getEmptyState().value) {
        Box(
            contentAlignment = Alignment.Center, modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(Color.White)
        ) {
            Text(text = "No favorites", color = Color.Magenta, fontSize = 20.sp)
        }
    }
}