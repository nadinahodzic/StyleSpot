package com.nadina.stylespot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.nadina.stylespot.navigation.AppNavigation
import com.nadina.stylespot.ui.theme.StyleSpotTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StyleSpotTheme {
                AppNavigation()
            }
        }
    }
}