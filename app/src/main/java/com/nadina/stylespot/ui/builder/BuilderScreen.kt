package com.nadina.stylespot.ui.builder

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun BuilderScreen() {

    val greatVibes = FontFamily(
        Font(R.font.greatvibesregular)
    )

    var selectedTop by remember {
        mutableStateOf<Int?>(null)
    }

    var selectedPants by remember {
        mutableStateOf<Int?>(null)
    }

    var selectedShoes by remember {
        mutableStateOf<Int?>(null)
    }

    val selectedAccessories = remember {
        mutableStateListOf<Int>()
    }

    val tops = listOf(
        R.drawable.blazer,

        R.drawable.croptop2,
        R.drawable.croptop3,
        R.drawable.croptop4,
        R.drawable.croptop5,

        R.drawable.shirt
    )

    val pants = listOf(
        R.drawable.pants,
        R.drawable.pants2,
        R.drawable.pants3,
        R.drawable.pants4,

        R.drawable.jeans,
        R.drawable.jeans2,
        R.drawable.jeans4,

        R.drawable.cargo,

        R.drawable.shorts,
        R.drawable.shorts2,

        R.drawable.skirt,
        R.drawable.skirt2,
        R.drawable.skirt3,
        R.drawable.skirt4,
        R.drawable.skirt5
    )

    val shoes = listOf(
        R.drawable.boots,
        R.drawable.boots2,
        R.drawable.boots3,

        R.drawable.heels,
        R.drawable.heels2,

        R.drawable.sneakers,
        R.drawable.sneakers2,

        R.drawable.loafers,
        R.drawable.loafers2,

        R.drawable.flats,
        R.drawable.flats2,
        R.drawable.flats3
    )

    val accessories = listOf(
        R.drawable.bag,
        R.drawable.bag2,
        R.drawable.bag3,
        R.drawable.bag4,
        R.drawable.bag5,

        R.drawable.watch,
        R.drawable.watch2,
        R.drawable.watch3,
        R.drawable.watch4,
        R.drawable.watch5,
        R.drawable.watch6,

        R.drawable.glasses,
        R.drawable.glasses2,
        R.drawable.glasses3,
        R.drawable.glasses4,
        R.drawable.glasses5,
        R.drawable.glasses6,

        R.drawable.jewelry
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color(0xFFFAF9F6))
            .padding(16.dp)
    ) {

        Text(
            text = "Outfit Builder",
            fontSize = 44.sp,
            fontFamily = greatVibes,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Create your perfect wardrobe.",
            fontSize = 17.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "current look",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 1.sp,
            color = Color(0xFF7A5C43)
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            selectedTop?.let {

                item {
                    OutfitPreviewCard(it)
                }
            }

            selectedPants?.let {

                item {
                    OutfitPreviewCard(it)
                }
            }

            selectedShoes?.let {

                item {
                    OutfitPreviewCard(it)
                }
            }

            items(selectedAccessories) { accessory ->

                OutfitPreviewCard(accessory)
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {

                    val outfit = mutableListOf<Int>()

                    selectedTop?.let {
                        outfit.add(it)
                    }

                    selectedPants?.let {
                        outfit.add(it)
                    }

                    selectedShoes?.let {
                        outfit.add(it)
                    }

                    outfit.addAll(selectedAccessories)

                    if (outfit.isNotEmpty()) {

                        SavedOutfitManager.savedOutfits.add(outfit)
                    }
                },

            shape = RoundedCornerShape(24.dp),

            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFB08968)
            )
        ) {

            Text(
                text = "add to wardrobe",

                modifier = Modifier.padding(20.dp),

                fontSize = 18.sp,

                fontWeight = FontWeight.Medium,

                letterSpacing = 1.sp,

                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        BuilderSection(
            title = "tops",
            items = tops,
            onItemSelected = {
                selectedTop = it
            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        BuilderSection(
            title = "pants",
            items = pants,
            onItemSelected = {
                selectedPants = it
            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        BuilderSection(
            title = "shoes",
            items = shoes,
            onItemSelected = {
                selectedShoes = it
            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        MultiSelectSection(
            title = "accessories",
            items = accessories,
            selectedAccessories = selectedAccessories
        )

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun BuilderSection(
    title: String,
    items: List<Int>,
    onItemSelected: (Int) -> Unit
) {

    Column {

        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 1.5.sp,
            color = Color(0xFF7A5C43)
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 4.dp)
        ) {

            items(items) { image ->

                var isSelected by remember {
                    mutableStateOf(false)
                }

                Card(
                    modifier = Modifier
                        .size(
                            width = 140.dp,
                            height = 190.dp
                        )
                        .clickable {

                            isSelected = !isSelected
                            onItemSelected(image)
                        },

                    shape = RoundedCornerShape(24.dp),

                    colors = CardDefaults.cardColors(
                        containerColor = if (isSelected)
                            Color(0xFFD8C3A5)
                        else
                            Color.White
                    ),

                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    )
                ) {

                    Image(
                        painter = painterResource(id = image),
                        contentDescription = null,

                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxSize()
                            .clip(RoundedCornerShape(24.dp)),

                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

@Composable
fun MultiSelectSection(
    title: String,
    items: List<Int>,
    selectedAccessories: MutableList<Int>
) {

    Column {

        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 1.5.sp,
            color = Color(0xFF7A5C43)
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 4.dp)
        ) {

            items(items) { image ->

                var isSelected by remember {
                    mutableStateOf(false)
                }

                Card(
                    modifier = Modifier
                        .size(
                            width = 140.dp,
                            height = 190.dp
                        )
                        .clickable {

                            isSelected = !isSelected

                            if (isSelected) {

                                if (!selectedAccessories.contains(image)) {
                                    selectedAccessories.add(image)
                                }

                            } else {

                                selectedAccessories.remove(image)
                            }
                        },

                    shape = RoundedCornerShape(24.dp),

                    colors = CardDefaults.cardColors(
                        containerColor = if (isSelected)
                            Color(0xFFD8C3A5)
                        else
                            Color.White
                    ),

                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    )
                ) {

                    Image(
                        painter = painterResource(id = image),
                        contentDescription = null,

                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxSize()
                            .clip(RoundedCornerShape(24.dp)),

                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

@Composable
fun OutfitPreviewCard(
    image: Int
) {

    Card(
        modifier = Modifier.size(
            width = 120.dp,
            height = 160.dp
        ),

        shape = RoundedCornerShape(24.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {

        Image(
            painter = painterResource(id = image),
            contentDescription = null,

            modifier = Modifier.fillMaxSize(),

            contentScale = ContentScale.Crop
        )
    }
}