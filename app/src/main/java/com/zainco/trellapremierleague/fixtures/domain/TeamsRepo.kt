package com.zainco.trellapremierleague.fixtures.domain

import com.google.gson.Gson
import com.zainco.trellapremierleague.base.network.AppAPis
import com.zainco.trellapremierleague.fixtures.data.db.DataStoreManager
import com.zainco.trellapremierleague.fixtures.data.models.MatchesItem
import com.zainco.trellapremierleague.fixtures.data.models.ResponseData
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TeamsRepo @Inject constructor(
    private val appAPis: AppAPis,
    private val db: DataStoreManager,
) {

    fun loadFixturesList(date: Pair<String, String>, status: String) = flow<ResponseData> {
        runCatching {
            emit(
                appAPis.getFixturesList(
                    dateFrom = date.first,
                    dateTo = date.second,
                    status = status
                )
            )
        }
    }

    fun getMatchesFromDB() = flow {
        val arr = arrayListOf<MatchesItem>()
        db.getFavs().collect {
            if (it.asMap().isNotEmpty()) {
                it.asMap().map {
                    val obj = Gson().fromJson(it.value.toString(), MatchesItem::class.java)
                    if (arr.find { i -> i.id == obj.id && i.isFavorite } == null)
                        arr.add(obj)
                }
                emit(TeamUsecase.DataType.DBData(arr))
            }
        }
    }

}