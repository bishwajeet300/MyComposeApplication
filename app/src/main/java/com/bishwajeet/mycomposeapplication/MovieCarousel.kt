package com.bishwajeet.mycomposeapplication

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.rememberScrollableController
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ConfigurationAmbient
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import dev.chrisbanes.accompanist.coil.CoilImage
import kotlin.math.roundToInt


@Preview
@Composable
fun Screen() {
    var offset = remember { mutableStateOf(0f) }
    val controller = rememberScrollableController {
        offset.value = it.plus(offset.value)
        it
    }
    Row(
        Modifier
            .background(Color.Black)
            .fillMaxSize()
            .scrollable(
                Orientation.Horizontal,
                controller,
            )
    ) {
        Row(Modifier
            .offset(getX = { offset.value }, getY = { 0f } )
        ) {
            MoviePoster()
            MoviePoster()
            MoviePoster()
        }
    }
}


fun Modifier.offset(
    getX: () -> Float,
    getY: () -> Float,
    rtlAware: Boolean = true
) = this then object : LayoutModifier {
    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureScope.MeasureResult {
        val placeable = measurable.measure(constraints)
        return layout(placeable.width, placeable.height) {
            if (rtlAware) {
                placeable.placeRelative(getX().roundToInt(), getY().roundToInt())
            } else {
                placeable.place(getX().roundToInt(), getY().roundToInt())
            }
        }
    }
}

@Composable
fun MoviePoster(modifier: Modifier = Modifier) {
    
    val screenSize = ConfigurationAmbient.current.screenWidthDp.dp * .60f
    
    Column(
        modifier
            .clip(RoundedCornerShape(20.dp))
            .width(screenSize)
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

        Text(
            "Joker",
            fontSize = 24.sp,
            color = Color.Black
        )
        Row {
            Chip("Action")
            Chip("Drama")
            Chip("History")
        }
        StarRating(9.0f)
        Spacer(modifier = Modifier.height(30.dp))
        BuyTicketButton(onClick = {})
    }
}


@Composable
fun BuyTicketButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        backgroundColor = Color.DarkGray,
        elevation = 0.dp,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text("Buy Ticket", color = Color.White)
    }
}


@Composable
fun StarRating(rating: Float) {

}


@Composable
fun Chip(label: String, modifier: Modifier = Modifier) {
    Text(
        text = label,
        fontSize = 9.sp,
        color = Color.Gray,
        modifier = modifier
            .border(1.dp, Color.Gray, RoundedCornerShape(50))
            .padding(horizontal = 10.dp, vertical = 2.dp)
    )
}