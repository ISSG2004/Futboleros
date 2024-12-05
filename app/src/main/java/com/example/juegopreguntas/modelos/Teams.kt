package com.example.juegopreguntas.modelos

import java.io.Serializable

data class Teams(
    val idTeam: String,
    val strTeam: String,
    val strBadge: String,
    val strCountry: String,
    val strWebsite:String,
):Serializable
data class TeamsResponse(
    val teams: List<Teams>
)

