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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.navigation.NavHostController
import com.nadina.stylespot.R
import com.nadina.stylespot.ui.favorites.FavoriteManager
import com.nadina.stylespot.ui.profile.RepostManager

@Composable
fun HomeScreen(
    navController: NavHostController
) {

    val greatVibes = FontFamily(
        Font(R.font.greatvibesregular)
    )

    var searchText by remember {
        mutableStateOf("")
    }

    val categories = listOf(
        "old money",
        "streetwear",
        "soft glam",
        "espresso girl"
    )

    val filteredOutfits = listOf(
        Pair("soft glam", R.drawable.elegant),
        Pair("old money", R.drawable.oldmoney),
        Pair("streetwear", R.drawable.streetwear),
        Pair("espresso girl", R.drawable.espressogirl1)
    ).filter {

        it.first.contains(
            searchText,
            ignoreCase = true
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color(0xFFFFFCF8))
            .padding(20.dp)
    ) {

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "welcome back,",
            fontSize = 18.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Nadina",
            fontSize = 52.sp,
            fontFamily = greatVibes,
            color = Color(0xFF8B5E3C)
        )

        Spacer(modifier = Modifier.height(28.dp))

        Card(
            shape = RoundedCornerShape(24.dp),

            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF7F1EA)
            ),

            elevation = CardDefaults.cardElevation(
                defaultElevation = 0.dp
            )
        ) {

            OutlinedTextField(
                value = searchText,

                onValueChange = {
                    searchText = it
                },

                modifier = Modifier.fillMaxWidth(),

                placeholder = {

                    Text(
                        text = "search aesthetics",
                        color = Color.Gray
                    )
                },

                singleLine = true,

                shape = RoundedCornerShape(24.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "discover styles",
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF7A5C43),
            letterSpacing = 1.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 2.dp)
        ) {

            items(categories) { category ->

                Card(
                    modifier = Modifier.clickable {

                        navController.navigate(
                            "style_details/$category"
                        )
                    },

                    shape = RoundedCornerShape(18.dp),

                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF1E6DA)
                    )
                ) {

                    Text(
                        text = category,

                        modifier = Modifier.padding(
                            horizontal = 22.dp,
                            vertical = 12.dp
                        ),

                        color = Color(0xFF8B5E3C),
                        fontSize = 15.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(36.dp))

        Text(
            text = "trending looks",
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF7A5C43),
            letterSpacing = 1.sp
        )

        Spacer(modifier = Modifier.height(18.dp))

        if (filteredOutfits.isEmpty()) {

            Text(
                text = "no looks found",
                color = Color.Gray,
                fontSize = 16.sp
            )

        } else {

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(18.dp)
            ) {

                items(filteredOutfits) { outfit ->

                    OutfitCard(
                        title = outfit.first,
                        image = outfit.second,

                        onClick = {

                            navController.navigate(
                                "style_details/${outfit.first}"
                            )
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun OutfitCard(
    title: String,
    image: Int,
    onClick: () -> Unit
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
            .width(210.dp)
            .height(320.dp)
            .clickable {
                onClick()
            },

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
                    .height(240.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 28.dp,
                            topEnd = 28.dp
                        )
                    ),

                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = if (isReposted)
                            "reposted"
                        else
                            "trending",

                        color = Color.Gray,
                        fontSize = 14.sp
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {

                        Icon(
                            imageVector = Icons.Default.Repeat,
                            contentDescription = null,

                            tint = if (isReposted)
                                Color(0xFFB08968)
                            else
                                Color.Gray,

                            modifier = Modifier.clickable {

                                isReposted = !isReposted

                                if (isReposted) {

                                    RepostManager
                                        .repostedOutfits
                                        .add(title)

                                } else {

                                    RepostManager
                                        .repostedOutfits
                                        .remove(title)
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
                                Color(0xFFB08968)
                            else
                                Color.Gray,

                            modifier = Modifier.clickable {

                                isFavorite = !isFavorite

                                if (isFavorite) {

                                    FavoriteManager
                                        .favoriteOutfits
                                        .add(title)

                                } else {

                                    FavoriteManager
                                        .favoriteOutfits
                                        .remove(title)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}