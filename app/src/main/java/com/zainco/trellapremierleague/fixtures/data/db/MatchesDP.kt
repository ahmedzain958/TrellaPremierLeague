package com.zainco.trellapremierleague.fixtures.data.db

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.zainco.trellapremierleague.fixtures.data.models.MatchesItem
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


private val Context.dataStore by preferencesDataStore("settings")

@Singleton
class DataStoreManager @Inject constructor(@ApplicationContext val appContext: Context) {
    private val dataStore = appContext.dataStore


    suspend fun saveMatches(matchesItem: MatchesItem) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey("${matchesItem.id}")] = Gson().toJson(matchesItem)
        }
    }

    fun getFavs() = dataStore.data
}