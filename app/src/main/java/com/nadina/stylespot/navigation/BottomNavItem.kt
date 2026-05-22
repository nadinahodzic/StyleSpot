package com.nadina.stylespot.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Checkroom
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {

    object Home : BottomNavItem(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )

    object Builder : BottomNavItem(
        route = "builder",
        title = "Builder",
        icon = Icons.Default.Checkroom
    )

    object Planner : BottomNavItem(
        route = "planner",
        title = "Planner",
        icon = Icons.Default.DateRange
    )

    object Favorites : BottomNavItem(
        route = "favorites",
        title = "Favorites",
        icon = Icons.Default.Favorite
    )

    object Profile : BottomNavItem(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Person
    )

    object EditProfile : BottomNavItem(
        route = "edit_profile",
        title = "Edit",
        icon = Icons.Default.Person
    )
}