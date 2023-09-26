package com.mongodb.app.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import coil.compose.AsyncImage
import com.mongodb.app.R
import com.mongodb.app.ui.theme.Purple200
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.request.ImageRequest
import com.mongodb.app.domain.Song
import com.mongodb.app.presentation.home.HomeViewModel


@Composable
fun HomePage(
    homeViewModel: HomeViewModel
) {
    var popupControl by remember { mutableStateOf(false) }
//    val song = homeViewModel.currSongState
    val song = Song("temp", "tempo")

    Column (
        Modifier
            .background(Color.LightGray)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space=16.dp,
            alignment = Alignment.CenterVertically
        ),
    ) {
        Text(
            "Song of The Day",
            fontSize = 30.sp,
        )
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
//                .data("https://upload.wikimedia.org/wikipedia/en/8/8f/Blink-182_-_Blink-182_cover.jpg")
                .data(song.image_url)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_baseline_wifi_off_24_white),
            contentDescription = "Description",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(300.dp)
                .width(300.dp)
                .padding(16.dp)
                .clip(shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
        )
        Text(
            text = "Song Title: " + song.song_title,
            fontSize = 24.sp,
        )
        Text(
            text = "Artist: " + song.artist,
            fontSize = 24.sp,
        )
        Text(
            text = "Album: " + song.album,
            fontSize = 24.sp,
        )
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                colors = buttonColors(containerColor = Purple200),
                onClick = {
                }
            ) {
                Text(text = "Last Weeks Songs")
            }
            Button(
                colors = buttonColors(containerColor = Purple200),
                onClick = { popupControl = true }
            ) {
                Text(text = "Rate Today's Song")
            }
        }
        if (popupControl) {
            SmileyPopup(onDismissRequest = { popupControl = false})
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun HomePreview() {
//    MyApplicationTheme {
//        val repository = SyncRepository()
//        val tasks = (1..30).map { index ->
//            MockRepository.getMockTask(index)
//        }.toMutableStateList()
//
//        MyApplicationTheme {
//            TaskList(repository, TaskViewModel(repository, tasks))
//        }
//    }
//}