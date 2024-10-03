package com.zainco.trellapremierleague

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.zainco.trellapremierleague.fixtures.presentation.TeamViewModel
import com.zainco.trellapremierleague.fixtures.presentation.ui.screens.TeamsScreen
import com.zainco.trellapremierleague.fixtures.presentation.ui.theme.MohamedDawoodTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<TeamViewModel>()
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MohamedDawoodTaskTheme {
                TeamsScreen(viewModel)
            }
        }
    }
}
