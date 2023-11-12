package com.example.valorantagents.di

import android.content.Context
import com.example.valorantagents.data.AgentRepository
import com.example.valorantagents.data.local.room.ValorantAgentDatabase

object Injection {
    fun provideRepository(context: Context) : AgentRepository {
        val database = ValorantAgentDatabase.getDatabase(context)
        return AgentRepository.getInstance(database)
    }
}