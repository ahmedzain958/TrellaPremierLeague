package com.zainco.trellapremierleague.fixtures.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zainco.trellapremierleague.fixtures.data.db.DataStoreManager
import com.zainco.trellapremierleague.fixtures.data.models.MatchesItem
import com.zainco.trellapremierleague.fixtures.domain.TeamUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class TeamViewModel @Inject constructor(
    private val usecase: TeamUsecase,
    private val db: DataStoreManager,
) : ViewModel() {
    private var _fixtures = mutableStateListOf<MatchesItem>()
    private var _favFixture = mutableStateListOf<MatchesItem>()
    private val tempList = mutableStateListOf<MatchesItem>()
    val fixtures: List<MatchesItem> get() = _fixtures

    fun filter(switchOn: Boolean) {
        usecase.filter(switchOn, _fixtures, tempList)
    }

    fun getEmptyState() = usecase.showEmptyFav

    fun getFixturesList(date: Pair<String, String>, status: String) {
        viewModelScope.launch(Dispatchers.IO) {
            usecase.loadFixtures(date, status).collect {
                val items = it.matches?.sortedBy { it.utcDate }
                withContext(Dispatchers.Main) {
                    updateFixtures(items ?: listOf())
                    tempList.addAll(items ?: listOf())
                }
                items?.forEach {
                    db.saveMatches(it)
                }

            }.runCatching {
                this.toString()
            }
        }
    }

    fun loadItems(date: Pair<String, String> = Pair("", ""), status: String = "") {
        viewModelScope.launch(Dispatchers.IO) {
            getFixturesList(date, status)
            usecase.getListFromDB().collect {
                val dbFavs = it
                _favFixture.addAll(it.list)
                cancel()
            }


        }
    }


    fun favClicked(item: MatchesItem, found: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            usecase.actionFav(db, found, _fixtures, item)
        }
    }

    fun updateFixtures(newFixtures: List<MatchesItem>) {
        val updatedFixtures = newFixtures.map { fixture ->
            fixture.copy(
                isFavorite = _favFixture.firstOrNull { it.id == fixture.id }?.isFavorite ?: false
            ) // Update isFavorite flag
        }
        _fixtures.clear()
        _fixtures.addAll(updatedFixtures)
    }
}