package com.nadina.stylespot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.google.firebase.auth.FirebaseAuth
import com.nadina.stylespot.navigation.AppNavigation
import com.nadina.stylespot.ui.auth.LoginScreen
import com.nadina.stylespot.ui.auth.RegisterScreen
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

                var isLoggedIn by remember {
                    mutableStateOf(false)

                }

                var showRegister by remember {
                    mutableStateOf(false)
                }

                LaunchedEffect(true) {

                    delay(3000)

                    showSplash = false
                }

                when {

                    showSplash -> {

                        SplashScreen(
                            onNavigate = {}
                        )
                    }

                    isLoggedIn -> {

                        AppNavigation()
                    }

                    showRegister -> {

                        RegisterScreen(

                            onRegisterSuccess = {

                                isLoggedIn = true
                            },

                            onBackToLogin = {

                                showRegister = false
                            }
                        )
                    }

                    else -> {

                        LoginScreen(

                            onLoginSuccess = {

                                isLoggedIn = true
                            },

                            onCreateAccount = {

                                showRegister = true
                            }
                        )
                    }
                }
            }
        }
    }
}