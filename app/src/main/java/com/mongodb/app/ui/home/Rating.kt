package com.mongodb.app.ui.home

import android.view.MotionEvent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


//@Composable
//fun RatingBar(
//    modifier: Modifier = Modifier,
//    rating: Double = 0.0,
//    stars: Int = 5,
//    starsColor: Color = Color.Yellow,
//) {
//    val filledStars = floor(rating).toInt()
//    val unfilledStars = (stars - ceil(rating)).toInt()
////    val halfStar = !(rating.rem(1).equals(0.0))
//    Row(modifier = modifier) {
//        repeat(filledStars) {
//            Icon(imageVector = Icons.Outlined.Star, contentDescription = null, tint = starsColor)
//        }
//        if (halfStar) {
//            Icon(
//                imageVector = Icons.Outlined.StarHalf,
//                contentDescription = null,
//                tint = starsColor
//            )
//        }
//        repeat(unfilledStars) {
//            Icon(
//                imageVector = Icons.Outlined.Star,
//                contentDescription = null,
//                tint = starsColor
//            )
//        }
//    }
//}

@ExperimentalComposeUiApi
@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Int
) {
    var ratingState by remember {
        mutableStateOf(rating)
    }

    var selected by remember {
        mutableStateOf(false)
    }
    val size by animateDpAsState(
        targetValue = if (selected) 72.dp else 64.dp,
        spring(Spring.DampingRatioMediumBouncy)
    )

    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        for (i in 1..5) {
            Icon(
                painter = painterResource(id = com.google.android.material.R.drawable.abc_star_black_48dp),
                contentDescription = "star",
                modifier = modifier
                    .width(size)
                    .height(size)
                    .pointerInteropFilter {
                        when (it.action) {
                            MotionEvent.ACTION_DOWN -> {
                                selected = true
                                ratingState = i
                            }
                            MotionEvent.ACTION_UP -> {
                                selected = false
                            }
                        }
                        true
                    },
                tint = if (i <= ratingState) Color(0xFFFFD700) else Color(0xFFA2ADB1)
            )
        }
    }
}