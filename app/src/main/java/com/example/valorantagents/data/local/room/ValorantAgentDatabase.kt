package com.example.valorantagents.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.valorantagents.data.local.entity.FavoriteAgent

@Database(entities = [FavoriteAgent::class], version = 1, exportSchema = false)
abstract class ValorantAgentDatabase : RoomDatabase() {
    abstract fun favoriteAgentDao(): FavoriteAgentDao

    companion object {
        @Volatile
        private var INSTANCE: ValorantAgentDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): ValorantAgentDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    ValorantAgentDatabase::class.java, "valorant_agent.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}