package com.example.valorantagents.ui

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.valorantagents.data.AgentRepository
import com.example.valorantagents.data.local.entity.FavoriteAgent
import com.example.valorantagents.di.Injection
import com.example.valorantagents.model.Agent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ValorantAgentsViewModel(private val repository: AgentRepository) : ViewModel() {
    private val _agents = MutableStateFlow(
        repository.getAgents()
    )
    val agents: StateFlow<List<Agent>> get() = _agents

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query


    fun searchAgent(query: String) {
        _query.value = query
        _agents.value = repository.searchAgents(query)
    }

    fun getAgentDetail(id: String) : Agent {
        return repository.getAgentDetail(id)
    }

    private val _favoriteAgents = MutableStateFlow<List<FavoriteAgent>>(emptyList())
    val favoriteAgents: StateFlow<List<FavoriteAgent>> get() = _favoriteAgents

    fun getFavoriteAgents()  {
        viewModelScope.launch {
            _favoriteAgents.value  = repository.getFavoriteAgents()
        }
    }

    suspend fun isFavorite(id: String) : Boolean {
        return repository.isFavorite(id)
    }

    fun toggleFavorite(id: String, name: String, role: String, image: String) {
        viewModelScope.launch {
            val isFavorite = repository.isFavorite(id)
            if (isFavorite) {
                repository.removeFromFavorite(id)
            } else {
                val agent = FavoriteAgent(id, name, role, image)
                repository.addToFavorite(agent)
            }
        }
    }

    fun removeFromFavorite(id: String) {
        viewModelScope.launch {
            repository.removeFromFavorite(id)
            _favoriteAgents.value  = repository.getFavoriteAgents()
        }
    }
}

class ViewModelFactory(private val repository: AgentRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ValorantAgentsViewModel::class.java)) {
            return ValorantAgentsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context) : ViewModelFactory =
            instance ?: synchronized(this){
                instance ?: ViewModelFactory(Injection.provideRepository(context)).also { instance = it }
            }
    }
}