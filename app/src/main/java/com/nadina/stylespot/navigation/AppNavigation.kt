package com.nadina.stylespot.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nadina.stylespot.ui.builder.BuilderScreen
import com.nadina.stylespot.ui.builder.SavedLooksScreen
import com.nadina.stylespot.ui.components.BottomBar
import com.nadina.stylespot.ui.favorites.FavoritesScreen
import com.nadina.stylespot.ui.home.HomeScreen
import com.nadina.stylespot.ui.home.StyleDetailsScreen
import com.nadina.stylespot.ui.planner.PlannerScreen
import com.nadina.stylespot.ui.profile.EditProfileScreen
import com.nadina.stylespot.ui.profile.ProfileScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues)
        ) {

            composable("home") {
                HomeScreen(navController)
            }

            composable("builder") {
                BuilderScreen()
            }

            composable("saved_looks") {
                SavedLooksScreen()
            }

            composable("planner") {
                PlannerScreen()
            }

            composable("favorites") {
                FavoritesScreen(navController)

            }

            composable("profile") {
                ProfileScreen(navController)
            }

            composable("edit_profile") {
                EditProfileScreen()
            }

            composable(
                route = "style_details/{style}",

                arguments = listOf(
                    navArgument("style") {
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->

                val style =
                    backStackEntry.arguments
                        ?.getString("style") ?: ""

                StyleDetailsScreen(
                    style = style
                )
            }
        }
    }
}