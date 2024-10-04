package com.zainco.trellapremierleague.fixtures.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun showDate() {
    DateSection(date = "2022-02-10T19:48:37Z")
}

@Composable
fun DateSection(date: String) {
    Box(
        modifier = Modifier
            .background(Color.Cyan)
            .padding(8.dp)
    ) {
        Text(
            date,
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xff0d3b66),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(
                    start = 8.dp,
                    end = 16.dp,
                    bottom = 8.dp
                )
                .background(Color.Cyan)
                .fillMaxWidth(),
        )
    }
}