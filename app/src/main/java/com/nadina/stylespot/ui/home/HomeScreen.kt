package com.nadina.stylespot.ui.home

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.nadina.stylespot.ui.favorites.FavoriteManager
import com.nadina.stylespot.ui.profile.RepostManager

@Composable
fun HomeScreen() {

    val greatVibes = FontFamily(
        Font(R.font.greatvibesregular)
    )

    var searchText by remember {
        mutableStateOf("")
    }

    val categories = listOf(
        "Casual",
        "Elegant",
        "Streetwear",
        "Sport",
        "Vintage"
    )

    val filteredOutfits = listOf(
        Pair("Old Money", R.drawable.oldmoney),
        Pair("Minimal", R.drawable.minimal),
        Pair("Streetwear", R.drawable.streetwear),
        Pair("Elegant", R.drawable.elegant)
    ).filter {

        it.first.contains(searchText, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAF9F6))
            .padding(16.dp)
    ) {

        Text(
            text = "Hello, Nadina",
            fontSize = 42.sp,
            fontFamily = greatVibes,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = searchText,

            onValueChange = {
                searchText = it
            },

            modifier = Modifier.fillMaxWidth(),

            placeholder = {
                Text("Search outfits...")
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Categories",
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 4.dp)
        ) {

            items(categories) { category ->

                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFD8C3A5)
                    )
                ) {

                    Text(
                        text = category,
                        modifier = Modifier.padding(
                            horizontal = 20.dp,
                            vertical = 12.dp
                        ),
                        color = Color.Black
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "Trending Outfits ✨",
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (filteredOutfits.isEmpty()) {

            Text(
                text = "No outfits found ✨",
                color = Color.Gray,
                fontSize = 18.sp
            )

        } else {

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(filteredOutfits) { outfit ->

                    OutfitCard(
                        title = outfit.first,
                        image = outfit.second
                    )
                }
            }
        }
    }
}

@Composable
fun OutfitCard(
    title: String,
    image: Int
) {

    var isFavorite by remember {
        mutableStateOf(
            FavoriteManager.favoriteOutfits.contains(title)
        )
    }

    var isReposted by remember {
        mutableStateOf(
            RepostManager.repostedOutfits.contains(title)
        )
    }

    Card(
        modifier = Modifier
            .width(170.dp)
            .height(260.dp),
        shape = RoundedCornerShape(20.dp),
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
                            topStart = 20.dp,
                            topEnd = 20.dp
                        )
                    ),

                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(12.dp)
            ) {

                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = if (isReposted)
                            "Reposted"
                        else
                            "Trending",

                        color = Color.Gray
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {

                        Icon(
                            imageVector = Icons.Default.Repeat,
                            contentDescription = null,

                            tint = if (isReposted)
                                Color(0xFFD8C3A5)
                            else
                                Color.Gray,

                            modifier = Modifier.clickable {

                                isReposted = !isReposted

                                if (isReposted) {

                                    RepostManager.repostedOutfits.add(title)

                                } else {

                                    RepostManager.repostedOutfits.remove(title)
                                }
                            }
                        )

                        Icon(
                            imageVector = if (isFavorite)
                                Icons.Default.Favorite
                            else
                                Icons.Default.FavoriteBorder,

                            contentDescription = null,

                            tint = if (isFavorite)
                                Color.Red
                            else
                                Color.Gray,

                            modifier = Modifier.clickable {

                                isFavorite = !isFavorite

                                if (isFavorite) {

                                    FavoriteManager.favoriteOutfits.add(title)

                                } else {

                                    FavoriteManager.favoriteOutfits.remove(title)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}