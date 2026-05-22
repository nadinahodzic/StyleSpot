package com.nadina.stylespot.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.nadina.stylespot.navigation.BottomNavItem

@Composable
fun BottomBar(
    navController: NavController
) {

    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Builder,
        BottomNavItem.Planner,
        BottomNavItem.Favorites,
        BottomNavItem.Profile
    )

    NavigationBar {

        items.forEach { item ->

            NavigationBarItem(
                selected = false,
                onClick = {
                    navController.navigate(item.route)
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(text = item.title)
                }
            )
        }
    }
}