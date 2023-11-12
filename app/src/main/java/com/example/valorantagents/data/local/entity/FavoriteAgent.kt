package com.example.valorantagents.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_agent")
data class FavoriteAgent(
    @PrimaryKey
    val id: String,

    val name : String,

    val role : String,

    val imageThumbnailUrl : String
)
