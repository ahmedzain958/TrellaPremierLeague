package com.zainco.trellapremierleague.fixtures.data.models

import com.zainco.trellapremierleague.fixtures.data.models.Area

data class MatchesItem(val area: Area = Area(),
                       val matchday: Int = 0,
                       val awayTeam: AwayTeam=AwayTeam(),
                       val competition: Competition=Competition(),
                       val utcDate: String = "",
                       val lastUpdated: String = "",
                       val score: Score?=null,
                       val stage: String = "",
                       val odds: Odds=Odds(),
                       val season: Season=Season(),
                       val homeTeam: HomeTeam = HomeTeam(),
                       val id: Int = 0,
                       val status: String = "",
                       var isFavorite:Boolean = false,
                       val group: Any? = null){


}