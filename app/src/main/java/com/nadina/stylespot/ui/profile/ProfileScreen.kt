package com.nadina.stylespot.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Style
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.nadina.stylespot.R
import com.nadina.stylespot.ui.favorites.FavoriteManager

@Composable
fun ProfileScreen(
    navController: NavController
) {

    val greatVibes = FontFamily(
        Font(R.font.greatvibesregular)
    )

    val username = ProfileDataManager.username

    val bio = ProfileDataManager.bio

    val aesthetic = ProfileDataManager.aesthetic

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color(0xFFFFFCF8))
            .padding(20.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(12.dp))

        Card(
            modifier = Modifier.size(140.dp),

            shape = CircleShape,

            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF1E6DA)
            ),

            elevation = CardDefaults.cardElevation(
                defaultElevation = 3.dp
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
            text = username.value,
            fontSize = 46.sp,
            fontFamily = greatVibes,
            color = Color(0xFF8B5E3C)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = bio.value,
            fontSize = 17.sp,
            color = Color.Gray,
            letterSpacing = 0.5.sp
        )

        Spacer(modifier = Modifier.height(34.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            StatCard(
                title = "favorites",
                value = FavoriteManager
                    .favoriteOutfits
                    .size
                    .toString(),

                icon = Icons.Default.Favorite
            )

            StatCard(
                title = "reposts",
                value = RepostManager
                    .repostedOutfits
                    .size
                    .toString(),

                icon = Icons.Default.Style
            )

            StatCard(
                title = "style",
                value = "1",
                icon = Icons.Default.Style
            )
        }

        Spacer(modifier = Modifier.height(34.dp))

        Text(
            text = "favorite aesthetic",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF7A5C43)
        )

        Spacer(modifier = Modifier.height(14.dp))

        Card(
            shape = RoundedCornerShape(18.dp),

            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFB08968)
            )
        ) {

            Text(
                text = aesthetic.value,

                modifier = Modifier.padding(
                    horizontal = 20.dp,
                    vertical = 12.dp
                ),

                color = Color.White,
                fontSize = 15.sp
            )
        }

        Spacer(modifier = Modifier.height(34.dp))

        ProfileOption(
            title = "edit profile",
            onClick = {
                navController.navigate("edit_profile")
            }
        )

        ProfileOption(
            title = "my wardrobe",
            onClick = {
                navController.navigate("saved_looks")
            }
        )

        ProfileOption(
            title = "style preferences"
        )

        ProfileOption(
            title = "settings"
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .clickable {

                    FirebaseAuth
                        .getInstance()
                        .signOut()

                    navController.navigate("login")
                },

            shape = RoundedCornerShape(22.dp),

            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFB08968)
            )
        ) {

            Row(
                modifier = Modifier.padding(20.dp),

                verticalAlignment = Alignment.CenterVertically,

                horizontalArrangement = Arrangement.Center
            ) {

                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = null,
                    tint = Color.White
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "sign out",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 1.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(36.dp))

        Text(
            text = "my reposts",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF7A5C43)
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (RepostManager.repostedOutfits.isEmpty()) {

            Text(
                text = "no reposts yet",
                color = Color.Gray
            )

        } else {

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {

                items(RepostManager.repostedOutfits) { repost ->

                    Card(
                        modifier = Modifier
                            .width(170.dp)
                            .height(210.dp),

                        shape = RoundedCornerShape(26.dp),

                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),

                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 3.dp
                        )
                    ) {

                        Column {

                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(140.dp)
                                    .background(Color(0xFFF1E6DA))
                            )

                            Column(
                                modifier = Modifier.padding(14.dp)
                            ) {

                                Text(
                                    text = repost,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 18.sp
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = "reposted",
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(50.dp))
    }
}

@Composable
fun StatCard(
    title: String,
    value: String,
    icon: ImageVector
) {

    Card(
        modifier = Modifier
            .height(110.dp)
            .width(110.dp),

        shape = RoundedCornerShape(24.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF7F1EA)
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),

            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color(0xFFB08968)
            )

            Column {

                Text(
                    text = value,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = title,
                    color = Color.Gray,
                    fontSize = 13.sp
                )
            }
        }
    }
}

@Composable
fun ProfileOption(
    title: String,
    onClick: () -> Unit = {}
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 14.dp)
            .clickable {
                onClick()
            },

        shape = RoundedCornerShape(22.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Text(
            text = title,

            modifier = Modifier.padding(22.dp),

            fontSize = 17.sp,

            color = Color.Black
        )
    }
}