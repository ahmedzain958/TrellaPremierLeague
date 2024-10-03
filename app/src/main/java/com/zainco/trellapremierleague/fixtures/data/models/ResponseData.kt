package com.zainco.trellapremierleague.fixtures.data.models

data class ResponseData(val competition: Competition,
                        val filters: Filters,
                        val matches: List<MatchesItem>?,
                        val resultSet: ResultSet
)