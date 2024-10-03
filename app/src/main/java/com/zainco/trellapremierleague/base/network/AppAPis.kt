package com.zainco.trellapremierleague.base.network

import com.zainco.trellapremierleague.BuildConfig
import com.zainco.trellapremierleague.fixtures.data.models.ResponseData
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface AppAPis  {

    @GET("matches")
    suspend fun getFixturesList(
        @Header("X-Auth-Token") key:String = BuildConfig.API_KEY,
        @Query("status") status:String="",
        @Query("dateFrom") dateFrom:String="",
        @Query("dateTo") dateTo:String="",
    ): ResponseData

    companion object{ operator fun invoke() {} }
}