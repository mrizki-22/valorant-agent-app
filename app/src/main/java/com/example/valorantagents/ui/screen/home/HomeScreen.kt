package com.example.valorantagents.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.valorantagents.ui.ValorantAgentsViewModel
import com.example.valorantagents.ui.component.AgentCard
import com.example.valorantagents.ui.component.SearchBar
import com.example.valorantagents.ui.navigation.Screen

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ValorantAgentsViewModel,
    navController: NavHostController
) {
    val agents by viewModel.agents.collectAsState()
    val query by viewModel.query

    Box(modifier = modifier) {

        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                SearchBar(
                    query = query, onQueryChange = viewModel::searchAgent,
                )
            }

            items(agents) { agent ->
                var isFavorite by remember {mutableStateOf(false)}

                LaunchedEffect(agent.id) {
                    isFavorite = viewModel.isFavorite(agent.id)
                }


                AgentCard(
                    agentName = agent.name,
                    agentRole = agent.role,
                    imageThumbnailUrl = agent.imageThumbnailUrl,
                    onClick = {
                        navController.navigate(Screen.Detail.createRoute(agent.id))
                    },
                    onFavoriteIconClick = {
                        viewModel.toggleFavorite(
                            agent.id,
                            agent.name,
                            agent.role,
                            agent.imageThumbnailUrl
                        )
                        isFavorite = !isFavorite
                    },
                    isFavorite = isFavorite
                )
            }
        }
    }
}




