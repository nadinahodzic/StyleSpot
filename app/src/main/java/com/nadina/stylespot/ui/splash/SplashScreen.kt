package com.nadina.stylespot.ui.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.nadina.stylespot.R


@Composable
fun SplashScreen(
    onNavigate: () -> Unit
) {

    val alpha = remember {
        Animatable(0f)
    }

    val greatVibes = FontFamily(
        Font(R.font.greatvibesregular)
    )

    LaunchedEffect(true) {

        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(2000)
        )

        kotlinx.coroutines.delay(2000)

        onNavigate.invoke()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAF9F6))
            .alpha(alpha.value),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "StyleSpot",
            fontSize = 72.sp,
            fontFamily = greatVibes,
            color = Color.Black
        )
    }
}