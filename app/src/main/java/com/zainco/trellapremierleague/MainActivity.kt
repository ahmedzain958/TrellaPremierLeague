package com.zainco.trellapremierleague

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.zainco.trellapremierleague.fixtures.presentation.TeamViewModel
import com.zainco.trellapremierleague.fixtures.presentation.ui.screens.TeamsScreen
import com.zainco.trellapremierleague.ui.theme.TrellaPremierLeagueTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<TeamViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrellaPremierLeagueTheme {
                TeamsScreen(viewModel)
            }
        }
    }
}
