package com.example.valorantagents.model


data class Agent(
    val id: String,
    val name: String,
    val role: String,
    val gender: String,
    val race : String,
    val biography : String,
    val imageUrl : String,
    val imageThumbnailUrl : String,
    val abilities : List<Ability>
)

data class Ability(
    val name: String,
    val imageUrl : String,
)