package com.zainco.trellapremierleague.fixtures.data.models

import kotlin.random.Random

data class Score(
    val duration: String = "",
    val winner: String = "",
    val halfTime: HalfTime? = null,
    val fullTime: FullTime? = null,
) {
    override fun hashCode(): Int {
        return Random(1500).nextInt()
    }
}