package com.example.valorantagents.ui.navigation

sealed class Screen(val route : String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object About : Screen("about")
    object Detail : Screen("home/{id}") {
        fun createRoute(id : String) = "home/$id"
    }
}
