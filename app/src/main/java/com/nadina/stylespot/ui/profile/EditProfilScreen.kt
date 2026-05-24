package com.nadina.stylespot.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nadina.stylespot.R

@Composable
fun EditProfileScreen() {

    val greatVibes = FontFamily(
        Font(R.font.greatvibesregular)
    )

    val username = ProfileDataManager.username

    val bio = ProfileDataManager.bio

    val selectedStyle = ProfileDataManager.aesthetic

    val styles = listOf(
        "soft glam",
        "old money",
        "streetwear",
        "espresso girl"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFCF8))
            .padding(20.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.size(130.dp),

            shape = CircleShape,

            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF1E6DA)
            )
        ) {

            Image(
                painter = painterResource(
                    id = R.drawable.profilegirl
                ),

                contentDescription = null,

                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape),

                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Edit Profile",
            fontSize = 44.sp,
            fontFamily = greatVibes,
            color = Color(0xFF8B5E3C)
        )

        Spacer(modifier = Modifier.height(36.dp))

        OutlinedTextField(
            value = username.value,

            onValueChange = {
                username.value = it
            },

            modifier = Modifier.fillMaxWidth(),

            label = {
                Text("username")
            },

            shape = RoundedCornerShape(20.dp),

            singleLine = true
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = bio.value,

            onValueChange = {
                bio.value = it
            },

            modifier = Modifier.fillMaxWidth(),

            label = {
                Text("bio")
            },

            shape = RoundedCornerShape(20.dp)
        )

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "favorite aesthetic",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF7A5C43)
        )

        Spacer(modifier = Modifier.height(14.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = 2.dp)
        ) {

            items(styles) { style ->

                Card(
                    onClick = {

                        selectedStyle.value = style
                    },

                    shape = RoundedCornerShape(18.dp),

                    colors = CardDefaults.cardColors(
                        containerColor =
                            if (selectedStyle.value == style)
                                Color(0xFFB08968)
                            else
                                Color(0xFFF1E6DA)
                    )
                ) {

                    Text(
                        text = style,

                        modifier = Modifier.padding(
                            horizontal = 18.dp,
                            vertical = 10.dp
                        ),

                        color =
                            if (selectedStyle.value == style)
                                Color.White
                            else
                                Color(0xFF8B5E3C),

                        fontSize = 14.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = { },

            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),

            shape = RoundedCornerShape(20.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFB08968)
            )
        ) {

            Text(
                text = "save changes",
                color = Color.White,
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}