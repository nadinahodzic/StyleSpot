package com.nadina.stylespot.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nadina.stylespot.R

@Composable
fun EditProfileScreen() {

    val greatVibes = FontFamily(
        Font(R.font.greatvibesregular)
    )

    val username = remember {
        mutableStateOf("Nadina")
    }

    val bio = remember {
        mutableStateOf("Fashion Enthusiast ✨")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAF9F6))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Edit Profile",
            fontSize = 42.sp,
            fontFamily = greatVibes,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = username.value,
            onValueChange = {
                username.value = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Username")
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = bio.value,
            onValueChange = {
                bio.value = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Bio")
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD8C3A5)
            )
        ) {

            Text(
                text = "Save Changes",
                color = Color.Black
            )
        }
    }
}