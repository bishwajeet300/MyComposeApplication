package com.bishwajeet.mycomposeapplication

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import dev.chrisbanes.accompanist.coil.CoilImage


@Preview
@Composable
fun Screen() {
    Column(
        Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        MoviePoster()
    }
}


@Composable
fun MoviePoster(modifier: Modifier = Modifier) {
    Column(modifier
        .clip(RoundedCornerShape(20.dp))
        .background(Color.White)
        .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CoilImage(
            data = "https://www.joblo.com/assets/images/joblo/posters/2019/08/joker-poster-main3.jpg",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(180.dp)
                .aspectRatio(.674f)
                .clip(RoundedCornerShape(10.dp))
        )

        Text("Joker",
            fontSize = 24.sp,
            color = Color.Black
        )
        Row {
            Chip("Action")
            Chip("Drama")
            Chip("History")
        }
    }
}


@Composable
fun Chip(label: String, modifier: Modifier = Modifier) {
    Text(text = label,
        fontSize = 12.sp,
        color = Color.Gray,
        modifier = modifier
            .border(1.dp, Color.Gray, RoundedCornerShape(50))
            .padding(horizontal = 8.dp, vertical = 2.dp)
    )
}