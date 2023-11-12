package com.example.valorantagents.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.valorantagents.ui.ValorantAgentsViewModel
import com.example.valorantagents.ui.screen.about.AboutScreen
import com.example.valorantagents.ui.screen.detail.DetailScreen
import com.example.valorantagents.ui.screen.favorite.FavoriteScreen
import com.example.valorantagents.ui.screen.home.HomeScreen

@Composable
fun NavGraph(
    modifier: Modifier,
    navController: NavHostController,
    startDestination: String,
    viewModel: ValorantAgentsViewModel
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                modifier = modifier,
                viewModel = viewModel,
                navController = navController
            )
        }
        composable(Screen.Favorite.route) {
            FavoriteScreen(modifier = modifier, viewModel = viewModel, navController = navController)
        }
        composable(Screen.About.route) {
            AboutScreen(modifier = modifier)
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("id") {type = NavType.StringType})
        ) {
            val id = it.arguments?.getString("id") ?: "1"
            DetailScreen(modifier = modifier, agentId = id, viewModel = viewModel)
        }
    }

}