package com.nadina.stylespot.ui.favorites

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import androidx.navigation.NavHostController
import com.nadina.stylespot.R

@Composable
fun FavoritesScreen(
    navController: NavHostController
) {

    val greatVibes = FontFamily(
        Font(R.font.greatvibesregular)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background (Color(0xFFFFFCF8))
            .padding(20.dp)
    ) {

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Favorites",
            fontSize = 44.sp,
            fontFamily = greatVibes,
            color = Color(0xFF8B5E3C)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "saved inspirations",
            fontSize = 17.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(28.dp))

        if (FavoriteManager.favoriteOutfits.isEmpty()) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(430.dp),

                shape = RoundedCornerShape(32.dp),

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
                        .padding(24.dp),

                    verticalArrangement = Arrangement.Center,

                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Box(
                        modifier = Modifier
                            .size(90.dp)
                            .clip(RoundedCornerShape(28.dp))
                            .background(Color(0xFFEADBCB)),

                        contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = null,
                            tint = Color(0xFFB08968),
                            modifier = Modifier.size(42.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "your wardrobe is empty",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF7A5C43)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "discover aesthetics and save your favorite looks",
                        color = Color.Gray,
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    Card(
                        modifier = Modifier.clickable {

                            navController.navigate("home")
                        },

                        shape = RoundedCornerShape(22.dp),

                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFB08968)
                        )
                    ) {

                        Text(
                            text = "explore styles",

                            modifier = Modifier.padding(
                                horizontal = 28.dp,
                                vertical = 16.dp
                            ),

                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

        } else {

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),

                verticalArrangement = Arrangement.spacedBy(16.dp),

                horizontalArrangement = Arrangement.spacedBy(16.dp),

                contentPadding = PaddingValues(bottom = 30.dp)
            ) {

                items(FavoriteManager.favoriteOutfits) { favorite ->

                    val image = when (favorite) {

                        "soft glam" -> R.drawable.softglam1

                        "old money" -> R.drawable.oldmoney1

                        "streetwear" -> R.drawable.streetwear1

                        else -> R.drawable.espressogirl1
                    }

                    Card(
                        shape = RoundedCornerShape(28.dp),

                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),

                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 3.dp
                        )
                    ) {

                        Column {

                            Image(
                                painter = painterResource(id = image),
                                contentDescription = null,

                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(240.dp),

                                contentScale = ContentScale.Crop
                            )

                            Column(
                                modifier = Modifier.padding(14.dp)
                            ) {

                                Text(
                                    text = favorite,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = "saved look",
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}