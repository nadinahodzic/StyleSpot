package com.nadina.stylespot.ui.planner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nadina.stylespot.R

@Composable
fun PlannerScreen() {

    val greatVibes = FontFamily(
        Font(R.font.greatvibesregular)
    )

    val plannerDays = listOf(
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday",
        "Sunday"
    )

    val styles = listOf(

        "Clean Girl",
        "Old Money",
        "Streetwear",
        "Model Off Duty",
        "Pinterest Girl",
        "Y2K",
        "Soft Girl",
        "Dark Feminine",
        "Coquette",
        "Vanilla Girl",
        "Scandinavian",
        "Minimal",
        "Sporty Chic",
        "City Girl",
        "Classy Dinner",
        "Casual Coffee Run",
        "Girls Night",
        "Vacation Vibes",
        "Airport Fit",
        "Study Look",
        "Date Night",
        "Summer Vibes",
        "Winter Cozy",
        "Luxury Chic"
    )

    val selectedStyles = remember {
        mutableStateMapOf<String, String>()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAF9F6))
            .padding(16.dp)
    ) {

        Text(
            text = "Weekly Planner",
            fontSize = 44.sp,
            fontFamily = greatVibes,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "curate your weekly wardrobe",
            fontSize = 17.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(28.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {

            items(plannerDays) { day ->

                var expanded by remember {
                    mutableStateOf(false)
                }

                Card(
                    shape = RoundedCornerShape(26.dp),

                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFFCF8)
                    ),

                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 3.dp
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {

                        Text(
                            text = day,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF2D2D2D)
                        )

                        Spacer(modifier = Modifier.height(18.dp))

                        Card(
                            shape = RoundedCornerShape(18.dp),

                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFF7F1EA)
                            )
                        ) {

                            TextButton(
                                onClick = {
                                    expanded = true
                                },

                                modifier = Modifier.fillMaxWidth()
                            ) {

                                Text(
                                    text =
                                        selectedStyles[day]
                                            ?: "select your style",

                                    fontSize = 17.sp,

                                    fontWeight = FontWeight.Medium,

                                    letterSpacing = 1.sp,

                                    color = Color(0xFF8B5E3C)
                                )
                            }
                        }

                        DropdownMenu(
                            expanded = expanded,

                            onDismissRequest = {
                                expanded = false
                            }
                        ) {

                            styles.forEach { style ->

                                DropdownMenuItem(
                                    text = {
                                        Text(style)
                                    },

                                    onClick = {

                                        selectedStyles[day] = style
                                        expanded = false
                                    }
                                )
                            }
                        }

                        selectedStyles[day]?.let { selectedStyle ->

                            Spacer(modifier = Modifier.height(18.dp))

                            Card(
                                shape = RoundedCornerShape(18.dp),

                                colors = CardDefaults.cardColors(
                                    containerColor = Color(0xFFB08968)
                                )
                            ) {

                                Text(
                                    text = selectedStyle,

                                    modifier = Modifier.padding(
                                        horizontal = 18.dp,
                                        vertical = 12.dp
                                    ),

                                    color = Color.White,

                                    fontSize = 16.sp,

                                    fontWeight = FontWeight.Medium,

                                    letterSpacing = 1.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}