package com.example.valorantagents.data

import com.example.valorantagents.data.local.entity.FavoriteAgent
import com.example.valorantagents.data.local.room.ValorantAgentDatabase
import com.example.valorantagents.model.Agent
import com.example.valorantagents.model.AgentsData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AgentRepository(
    private val database: ValorantAgentDatabase
) {
    fun getAgents(): List<Agent> {
        return AgentsData.agents
    }

    fun searchAgents(query: String): List<Agent> {
        return AgentsData.agents.filter {
            it.name.contains(query, ignoreCase = true) ||
                    it.role.contains(query, ignoreCase = true)
        }
    }

    fun getAgentDetail(id: String): Agent {
        return AgentsData.agents.first {
            it.id == id
        }
    }

    suspend fun getFavoriteAgents(): List<FavoriteAgent> {
        return withContext(Dispatchers.IO) {
            database.favoriteAgentDao().getAllFavoriteAgents()
        }
    }

    suspend fun isFavorite(id: String): Boolean {
        return withContext(Dispatchers.IO) {
            database.favoriteAgentDao().getFavoriteAgent(id) != null
        }
    }

    suspend fun addToFavorite(favoriteAgent: FavoriteAgent) {
        withContext(Dispatchers.IO) {
            database.favoriteAgentDao().insertFavoriteAgent(favoriteAgent)
        }
    }

    suspend fun removeFromFavorite(id: String) {
        withContext(Dispatchers.IO) {
            database.favoriteAgentDao().deleteFavoriteAgent(id)
        }
    }

    companion object {
        @Volatile
        private var instance: AgentRepository? = null

        fun getInstance(database: ValorantAgentDatabase): AgentRepository =
            instance ?: synchronized(this) {
                instance ?: AgentRepository(database).also { instance = it }
            }
    }
}