package com.example.juegopreguntas.modelos

import java.io.Serializable

data class Teams(
    val idTeam: String,
    val strTeam: String,
    val strTeamBadge: String,
    val strCountry: String,
):Serializable
data class TeamsResponse(
    val teams: List<Teams>
)

