package com.nadina.stylespot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.nadina.stylespot.navigation.AppNavigation
import com.nadina.stylespot.ui.splash.SplashScreen
import com.nadina.stylespot.ui.theme.StyleSpotTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            StyleSpotTheme {

                var showSplash by remember {
                    mutableStateOf(true)
                }

                LaunchedEffect(true) {

                    delay(3000)

                    showSplash = false
                }

                if (showSplash) {

                    SplashScreen(
                        onNavigate = {}
                    )

                } else {

                    AppNavigation()
                }
            }
        }
    }
}