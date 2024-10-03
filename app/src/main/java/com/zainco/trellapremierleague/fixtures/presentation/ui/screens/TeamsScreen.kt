package com.zainco.trellapremierleague.fixtures.presentation.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.IconButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zainco.trellapremierleague.FilterDialog
//import com.zainco.trellapremierleague.FilterWithDatePicker
import com.zainco.trellapremierleague.R
import com.zainco.trellapremierleague.fixtures.presentation.TeamViewModel
import com.zainco.trellapremierleague.utils.formatDateToCompare

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TeamsScreen(viewModel: TeamViewModel) {

    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        var visibleToggle by remember { mutableStateOf(true) }
        var switchOn by remember { mutableStateOf(false) }
        var visible by remember { mutableStateOf(true) }
        val grouped = viewModel.fixtures.groupBy { it.utcDate.formatDateToCompare() }
        var showDialog by remember { mutableStateOf(false) }
        LaunchedEffect(Unit, block = { viewModel.loadItems() })

        ShowEmptyScreen(viewModel)
        Column {
            if (showDialog) {
                FilterDialog(
                    onApply = { date,status ->
                        viewModel.loadItems(date,status)
                        showDialog = false
                    },
                    onDismiss = { showDialog = false }
                )
            }

            Row(horizontalArrangement = Arrangement.Center) {
                AnimatedVisibility(visible = visibleToggle) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {
                            showDialog = true
                        }) {
                            Icon(
                                imageVector = Icons.Filled.FilterList,
                                contentDescription = "Filter"
                            )
                        }
                        Switch(checked = switchOn, onCheckedChange = { switchOn_ ->
                            switchOn = switchOn_
                            viewModel.filter(switchOn)
                        })
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(
                            text = if (switchOn) stringResource(id = R.string.see_all)
                            else stringResource(
                                id = R.string.show_fav
                            )
                        )

                    }
                }
            }

            LazyColumn(modifier = Modifier.fillMaxHeight()) {
                grouped.forEach { (date, matchesItems) ->
                    stickyHeader {
                        DateSection(date = date)
                    }
                    items(items = matchesItems) { item ->
                        visible = false
                        TeamsItem(item) {
                            viewModel.favClicked(item, !item.isFavorite)
                            visibleToggle = true
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
        AnimatedVisibility(visible = visible) {
            CircularIndeterminateProgressBar(visible)
        }

    }
}