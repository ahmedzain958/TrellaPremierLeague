package com.zainco.trellapremierleague

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun FilterDialog(
    onApply: (date: Pair<String, String>, filter: String) -> Unit,
    onDismiss: () -> Unit,
) {
    var selectedFilter by remember { mutableStateOf("") }
    var selectedDateFrom by remember { mutableStateOf("") }
    var selectedDateTo by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Filter by Status") },
        text = {
            Column {
                FilterWithDatePicker("From") {
                    selectedDateFrom = it
                }
                if (selectedDateFrom.isNotEmpty())
                    FilterWithDatePicker("To") {
                        selectedDateTo = it
                    }
                FilterOption(text = "All", selected = selectedFilter == "") {
                    selectedFilter = "All"
                }
                FilterOption(
                    text = "Scheduled",
                    selected = selectedFilter == MatchStatus.SCHEDULED.status
                ) { selectedFilter = MatchStatus.SCHEDULED.status }
                FilterOption(
                    text = "Paused",
                    selected = selectedFilter == MatchStatus.PAUSED.status
                ) { selectedFilter = MatchStatus.PAUSED.status }
                FilterOption(
                    text = "Live",
                    selected = selectedFilter == MatchStatus.LIVE.status
                ) { selectedFilter = MatchStatus.LIVE.status }
                FilterOption(
                    text = "InPlay",
                    selected = selectedFilter == MatchStatus.IN_PLAY.status
                ) { selectedFilter = MatchStatus.IN_PLAY.status }
                FilterOption(
                    text = "Finished",
                    selected = selectedFilter == MatchStatus.FINISHED.status
                ) { selectedFilter = MatchStatus.FINISHED.status }
                FilterOption(
                    text = "Postponed",
                    selected = selectedFilter == MatchStatus.POSTPONED.status
                ) { selectedFilter = MatchStatus.POSTPONED.status }
                FilterOption(
                    text = "Suspended",
                    selected = selectedFilter == MatchStatus.SUSPENDED.status
                ) { selectedFilter = MatchStatus.SUSPENDED.status }
                FilterOption(
                    text = "Cancelled",
                    selected = selectedFilter == MatchStatus.CANCELLED.status
                ) { selectedFilter = MatchStatus.CANCELLED.status }
            }
        },
        confirmButton = {
            Button(onClick = { onApply(Pair(selectedDateFrom, selectedDateTo), selectedFilter) }) {
                Text("Apply")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun FilterOption(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick
        )
        Text(
            text = text,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

enum class MatchStatus(val status: String) {
    SCHEDULED("SCHEDULED"),
    LIVE("LIVE"),
    IN_PLAY("IN_PLAY"),
    PAUSED("PAUSED"),
    FINISHED("FINISHED"),
    POSTPONED("POSTPONED"),
    SUSPENDED("SUSPENDED"),
    CANCELLED("CANCELLED")
}