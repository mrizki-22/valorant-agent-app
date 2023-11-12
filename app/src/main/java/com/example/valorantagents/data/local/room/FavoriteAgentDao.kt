package com.example.valorantagents.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.valorantagents.data.local.entity.FavoriteAgent

@Dao
interface FavoriteAgentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteAgent(favoriteAgent: FavoriteAgent)

    @Query("SELECT * FROM favorite_agent")
    fun getAllFavoriteAgents() : List<FavoriteAgent>

    @Query("DELETE FROM favorite_agent WHERE id = :id")
    fun deleteFavoriteAgent(id: String)

    @Query("SELECT * FROM favorite_agent WHERE id = :id")
    fun getFavoriteAgent(id: String) : FavoriteAgent?
}