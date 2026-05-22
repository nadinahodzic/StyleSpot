package com.nadina.stylespot.ui.favorites

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
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
fun FavoritesScreen() {

    val greatVibes = FontFamily(
        Font(R.font.greatvibesregular)
    )

    val favorites = remember {
        mutableStateListOf<String>().apply {
            addAll(FavoriteManager.favoriteOutfits)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAF9F6))
            .padding(16.dp)
    ) {

        Text(
            text = "Favorites",
            fontSize = 42.sp,
            fontFamily = greatVibes,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Your Saved Looks ✨",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (favorites.isEmpty()) {

            Text(
                text = "No saved outfits yet",
                color = Color.Gray,
                fontSize = 18.sp
            )

        } else {

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 4.dp)
            ) {

                items(favorites) { outfit ->

                    val image = when (outfit) {

                        "Old Money" -> R.drawable.oldmoney
                        "Minimal" -> R.drawable.minimal
                        "Streetwear" -> R.drawable.streetwear
                        "Elegant" -> R.drawable.elegant

                        else -> R.drawable.oldmoney
                    }

                    Card(
                        modifier = Modifier
                            .width(180.dp)
                            .height(260.dp),

                        shape = RoundedCornerShape(24.dp),

                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),

                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        )
                    ) {

                        Column {

                            Image(
                                painter = painterResource(id = image),
                                contentDescription = null,

                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(180.dp)
                                    .clip(
                                        RoundedCornerShape(
                                            topStart = 24.dp,
                                            topEnd = 24.dp
                                        )
                                    ),

                                contentScale = ContentScale.Crop
                            )

                            Column(
                                modifier = Modifier.padding(14.dp)
                            ) {

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {

                                    Text(
                                        text = outfit,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold
                                    )

                                    Icon(
                                        imageVector = Icons.Default.Favorite,
                                        contentDescription = null,

                                        tint = Color.Red,

                                        modifier = Modifier.clickable {

                                            favorites.remove(outfit)
                                            FavoriteManager.favoriteOutfits.remove(outfit)
                                        }
                                    )
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = "Fashion Favorite ✨",
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