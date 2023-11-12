package com.example.valorantagents.ui.screen.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.valorantagents.ui.ValorantAgentsViewModel
import com.example.valorantagents.ui.component.FavoriteAgentCard
import com.example.valorantagents.ui.navigation.Screen

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: ValorantAgentsViewModel,
    navController: NavHostController
) {
    val favoriteAgents by viewModel.favoriteAgents.collectAsState()

    LaunchedEffect(Unit){
        viewModel.getFavoriteAgents()
    }

    Box(modifier = modifier.fillMaxSize()) {
        if(favoriteAgents.isEmpty()) {
            Text(text = "No Favorite Agents", modifier = Modifier.align(Alignment.Center))
        }else {
            LazyColumn(
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(favoriteAgents) { agent ->
                    FavoriteAgentCard(
                        agentName = agent.name,
                        agentRole = agent.role,
                        imageThumbnailUrl = agent.imageThumbnailUrl,
                        onClick = {
                            navController.navigate(Screen.Detail.createRoute(agent.id))
                        },
                        onClickRemove = {
                            viewModel.removeFromFavorite(agent.id)
                        }
                    )
                }
            }
        }
    }
}