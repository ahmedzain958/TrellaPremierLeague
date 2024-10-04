package com.zainco.trellapremierleague.fixtures.domain

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.zainco.trellapremierleague.fixtures.data.db.DataStoreManager
import com.zainco.trellapremierleague.fixtures.data.models.MatchesItem
import javax.inject.Inject

class TeamUsecase @Inject constructor(
    private val teamsRepo: TeamsRepo,
) {
    val showEmptyFav = mutableStateOf(false)
    fun loadFixtures(date: Pair<String, String>, status: String) =
        (teamsRepo.loadFixturesList(date, status))

    fun filter(
        switchOn: Boolean,
        _fixtures: SnapshotStateList<MatchesItem>,
        tempList: SnapshotStateList<MatchesItem>,
    ) {
        if (switchOn) {
            _fixtures.clear()
            val nList = tempList.filter { it.isFavorite }.distinct()
            _fixtures.addAll(nList.ifEmpty { listOf() })
        } else {
            _fixtures.clear()
            try {
                _fixtures.addAll(tempList.distinct())
            } catch (e: NullPointerException) {
                _fixtures.addAll(tempList)
                e.printStackTrace()
            }
        }
        showEmptyFav.value = _fixtures.isEmpty()
    }

    suspend fun getListFromDB() = teamsRepo.getMatchesFromDB()

    suspend fun actionFav(
        db: DataStoreManager,
        found: Boolean,
        _fixtures: SnapshotStateList<MatchesItem>,
        item: MatchesItem,
    ) {
        if (!found) {
            val c = item.copy(isFavorite = false)
            db.saveMatches(c)
            _fixtures.find { it.id == item.id }?.isFavorite = false
        } else {
            val c = item.copy(isFavorite = true)
            db.saveMatches(c)
            _fixtures.find { it.id == item.id }?.isFavorite = true
        }
    }

    sealed class DataType {
        data class DBData(val list: List<MatchesItem>) : DataType()
    }
}