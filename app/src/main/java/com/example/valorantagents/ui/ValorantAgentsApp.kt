package com.example.valorantagents.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.valorantagents.ui.navigation.NavGraph
import com.example.valorantagents.ui.navigation.NavigationBar
import com.example.valorantagents.ui.navigation.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ValorantAgentsApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel : ValorantAgentsViewModel
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route



    Scaffold(
        modifier = modifier,
        topBar = {
            when(currentRoute) {
                Screen.Home.route -> TopAppBar(title = "Home")
                Screen.Favorite.route -> TopAppBar(title = "Favorite Agents")
                Screen.About.route -> TopAppBar(title = "About me")
                Screen.Detail.route -> TopAppBar(title = "Detail Agent")
                else -> TopAppBar(title = "Valorant Agents")
            }
        },
        bottomBar = {
            if (currentRoute != Screen.Detail.route) {
                BottomBar(navController)
            }
        }
    ) {
        NavGraph(
            modifier = modifier.padding(it),
            navController = navController,
            startDestination = Screen.Home.route,
            viewModel = viewModel
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBar(
    modifier: Modifier = Modifier,
    title: String,
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(text = title)
        },
    )
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier, navController = navController)
}
