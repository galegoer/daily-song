package com.mongodb.app.ui.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import coil.request.ImageRequest

data class SmileyData(
    val url: String,
    val label: String,
)

@Composable
fun Smiley(
    smileyData: SmileyData,
    isSelected: Boolean,
    index: Int,
    count: Int,
    modifier: Modifier = Modifier,
    animationDurationInMillis: Int = 200,
    onClick: () -> Unit,
) {
    val padding: Dp by animateDpAsState(
        targetValue = if (isSelected) {
            0.dp
        } else {
            4.dp
        },
        animationSpec = tween(
            durationMillis = animationDurationInMillis,
            easing = LinearEasing,
        ),
    )
    val size: Dp by animateDpAsState(
        targetValue = if (isSelected) {
            52.dp
        } else {
            44.dp
        },
        animationSpec = tween(
            durationMillis = animationDurationInMillis,
            easing = LinearEasing,
        ),
    )
    val saturation: Float by animateFloatAsState(
        targetValue = if (isSelected) {
            1F
        } else {
            0F
        },
        animationSpec = tween(
            durationMillis = animationDurationInMillis,
            easing = LinearEasing,
        ),
    )
    val matrix = ColorMatrix().apply {
        setToSaturation(saturation)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth(),
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(smileyData.url)
                .crossfade(true)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(
                    top = padding,
                )
                .size(size)
                .clip(CircleShape)
                .clickable {
                    onClick()
                },
            colorFilter = ColorFilter.colorMatrix(matrix)
        )
    }
}

@Composable
fun RatingBar(
    data: List<SmileyData>,
    rating: Int,
    setRating: (rating: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
    ) {
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 24.dp,
                    start = 44.dp,
                    end = 44.dp,
                ),
            thickness = 4.dp,
        )
        Row {
            data.mapIndexed { index, smileyData ->
                Smiley(
                    smileyData = smileyData,
                    isSelected = index == rating,
                    index = index,
                    count = data.size,
                    modifier = Modifier.weight(1F),
                    onClick = {
                        setRating(index)
                    },
                )
            }
        }
    }
}

@Composable
@Preview
fun SmileyPopup(onDismissRequest: () -> Unit = {}) {

    val data: List<SmileyData> = listOf(
        SmileyData("https://cdn-icons-png.flaticon.com/512/742/742905.png", "Terrible"),
        SmileyData("https://cdn-icons-png.flaticon.com/512/742/742761.png", "Bad"),
        SmileyData("https://cdn-icons-png.flaticon.com/512/742/742774.png", "Okay"),
        SmileyData("https://cdn-icons-png.flaticon.com/512/742/742940.png", "Good"),
        SmileyData("https://cdn-icons-png.flaticon.com/512/742/742869.png", "Awesome"),
    )

    val (rating, setRating) = remember {
        mutableStateOf(data.size / 2)
    }

    // TODO: test on actual device seems to not gray out bg but may be an emulator issue
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card (
            modifier = Modifier,
//                .fillMaxWidth()
//                .padding(35.dp),
            shape = RoundedCornerShape(25.dp),
        ) {
            RatingBar(
                data = data,
                rating = rating,
                setRating = setRating,
            )
        }
    }
}