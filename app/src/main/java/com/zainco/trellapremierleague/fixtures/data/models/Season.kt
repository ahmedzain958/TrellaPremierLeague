package com.zainco.trellapremierleague.fixtures.data.models

import kotlin.random.Random

data class Season(val winner: Winner=Winner(),
                  val currentMatchday: Int = 0,
                  val endDate: String = "",
                  val id: Int = 0,
                  val startDate: String = ""){
    override fun hashCode(): Int {
        return Random(3500).nextInt()
    }
}