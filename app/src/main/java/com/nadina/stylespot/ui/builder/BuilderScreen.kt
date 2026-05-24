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

    var selectedBottom by remember {
        mutableStateOf<Int?>(null)
    }

    var selectedDress by remember {
        mutableStateOf<Int?>(null)
    }

    var selectedShoes by remember {
        mutableStateOf<Int?>(null)
    }

    val selectedBags = remember {
        mutableStateListOf<Int>()
    }

    val selectedJewelry = remember {
        mutableStateListOf<Int>()
    }

    val tops = listOf(
        R.drawable.croptop3,
        R.drawable.croptop4,
        R.drawable.croptop5,
        R.drawable.shirt,
        R.drawable.shirt2,
        R.drawable.shirt3,

    )

    val bottoms = listOf(
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

    val dresses = listOf(
        R.drawable.dress,
        R.drawable.dress2,
        R.drawable.dress3,
        R.drawable.dress4,
        R.drawable.dress5,
        R.drawable.dress6,
        R.drawable.dress7
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

    val bags = listOf(
        R.drawable.bag,
        R.drawable.bag2,
        R.drawable.bag3,
        R.drawable.bag4,
        R.drawable.bag5,
        R.drawable.bag6,
        R.drawable.bag7,
        R.drawable.bag8
    )

    val jewelry = listOf(
        R.drawable.jewelry,
        R.drawable.jewelry3,
        R.drawable.jewelry4,
        R.drawable.jewelry5,
        R.drawable.jewelry6,

        R.drawable.watch,
        R.drawable.watch2,
        R.drawable.watch3,
        R.drawable.watch4,
        R.drawable.watch5,
        R.drawable.watch6,

        R.drawable.glasses,
        R.drawable.glasses2,
        R.drawable.glasses4,
        R.drawable.glasses5,
        R.drawable.glasses6
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background (Color(0xFFFFFCF8))
            .padding(20.dp)
    ) {

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Outfit Builder",
            fontSize = 48.sp,
            fontFamily = greatVibes,
            color = Color(0xFF8B5E3C)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "create your signature wardrobe",
            fontSize = 17.sp,
            color = Color.Gray,
            letterSpacing = 0.5.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "current look",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF7A5C43),
            letterSpacing = 1.sp
        )

        Spacer(modifier = Modifier.height(18.dp))

        Card(
            shape = RoundedCornerShape(28.dp),

            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF7F1EA)
            ),

            elevation = CardDefaults.cardElevation(
                defaultElevation = 0.dp
            )
        ) {

            LazyRow(
                modifier = Modifier.padding(18.dp),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {

                selectedTop?.let {
                    item { OutfitPreviewCard(it) }
                }

                selectedBottom?.let {
                    item { OutfitPreviewCard(it) }
                }

                selectedDress?.let {
                    item { OutfitPreviewCard(it) }
                }

                selectedShoes?.let {
                    item { OutfitPreviewCard(it) }
                }

                items(selectedBags) {
                    OutfitPreviewCard(it)
                }

                items(selectedJewelry) {
                    OutfitPreviewCard(it)
                }
            }
        }

        Spacer(modifier = Modifier.height(22.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {

                    val outfit = mutableListOf<Int>()

                    selectedTop?.let {
                        outfit.add(it)
                    }

                    selectedBottom?.let {
                        outfit.add(it)
                    }

                    selectedDress?.let {
                        outfit.add(it)
                    }

                    selectedShoes?.let {
                        outfit.add(it)
                    }

                    outfit.addAll(selectedBags)
                    outfit.addAll(selectedJewelry)

                    if (outfit.isNotEmpty()) {

                        SavedOutfitManager.savedOutfits.add(outfit)
                    }
                },

            shape = RoundedCornerShape(26.dp),

            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFB08968)
            )
        ) {

            Text(
                text = "save look",

                modifier = Modifier.padding(22.dp),

                fontSize = 18.sp,

                fontWeight = FontWeight.Medium,

                color = Color.White,

                letterSpacing = 1.sp
            )
        }

        Spacer(modifier = Modifier.height(38.dp))

        Text(
            text = "wardrobe essentials",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(28.dp))

        BuilderSection(
            title = "tops",
            items = tops,
            onItemSelected = {
                selectedTop = it
            }
        )

        Spacer(modifier = Modifier.height(34.dp))

        BuilderSection(
            title = "bottoms",
            items = bottoms,
            onItemSelected = {
                selectedBottom = it
            }
        )

        Spacer(modifier = Modifier.height(34.dp))

        BuilderSection(
            title = "dresses",
            items = dresses,
            onItemSelected = {
                selectedDress = it
            }
        )

        Spacer(modifier = Modifier.height(34.dp))

        BuilderSection(
            title = "shoes",
            items = shoes,
            onItemSelected = {
                selectedShoes = it
            }
        )

        Spacer(modifier = Modifier.height(34.dp))

        MultiSelectSection(
            title = "bags",
            items = bags,
            selectedItems = selectedBags
        )

        Spacer(modifier = Modifier.height(34.dp))

        MultiSelectSection(
            title = "jewelry & accessories",
            items = jewelry,
            selectedItems = selectedJewelry
        )

        Spacer(modifier = Modifier.height(50.dp))
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
            color = Color(0xFF7A5C43),
            letterSpacing = 1.sp
        )

        Spacer(modifier = Modifier.height(14.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            contentPadding = PaddingValues(horizontal = 2.dp)
        ) {

            items(items) { image ->

                var isSelected by remember {
                    mutableStateOf(false)
                }

                Card(
                    modifier = Modifier
                        .size(
                            width = 150.dp,
                            height = 210.dp
                        )
                        .clickable {

                            isSelected = !isSelected
                            onItemSelected(image)
                        },

                    shape = RoundedCornerShape(28.dp),

                    colors = CardDefaults.cardColors(
                        containerColor = if (isSelected)
                            Color(0xFFF1E6DA)
                        else
                            Color.White
                    ),

                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 3.dp
                    )
                ) {

                    Image(
                        painter = painterResource(id = image),
                        contentDescription = null,

                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(28.dp)),

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
    selectedItems: MutableList<Int>
) {

    Column {

        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF7A5C43),
            letterSpacing = 1.sp
        )

        Spacer(modifier = Modifier.height(14.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            contentPadding = PaddingValues(horizontal = 2.dp)
        ) {

            items(items) { image ->

                var isSelected by remember {
                    mutableStateOf(false)
                }

                Card(
                    modifier = Modifier
                        .size(
                            width = 150.dp,
                            height = 210.dp
                        )
                        .clickable {

                            isSelected = !isSelected

                            if (isSelected) {

                                if (!selectedItems.contains(image)) {

                                    selectedItems.add(image)
                                }

                            } else {

                                selectedItems.remove(image)
                            }
                        },

                    shape = RoundedCornerShape(28.dp),

                    colors = CardDefaults.cardColors(
                        containerColor = if (isSelected)
                            Color(0xFFF1E6DA)
                        else
                            Color.White
                    ),

                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 3.dp
                    )
                ) {

                    Image(
                        painter = painterResource(id = image),
                        contentDescription = null,

                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(28.dp)),

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
            width = 130.dp,
            height = 180.dp
        ),

        shape = RoundedCornerShape(28.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
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