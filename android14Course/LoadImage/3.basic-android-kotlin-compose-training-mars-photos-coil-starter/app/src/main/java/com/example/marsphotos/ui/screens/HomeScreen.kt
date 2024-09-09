/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.marsphotos.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.marsphotos.R
import com.example.marsphotos.model.MarsPhoto
import com.example.marsphotos.ui.theme.MarsPhotosTheme
import coil.request.ImageRequest
import androidx.compose.ui.platform.LocalContext

@Composable
fun HomeScreen(
    marsUiState: MarsUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    when (marsUiState) {
        is MarsUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is MarsUiState.Success -> PhotoGirdScreen(photos = marsUiState.photos, modifier = modifier)
        is MarsUiState.Error -> ErrorScreen(retryAction= retryAction ,modifier = modifier.fillMaxSize())
    }
}



@Composable
fun MarsPhotoCard(
    photo: MarsPhoto,
    modifier: Modifier = Modifier
){

    Card ( //Bao boc hinh anh,tao khung chua
       modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp ) //Xac dinh do cao cua Card so voi be mat, tao hieu ung do bong
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(photo.imgSrc) //Nguon du lieu
                .build(),
            contentDescription = stringResource(R.string.mars_photo),
            contentScale = ContentScale.Crop, //Hinh anh lap day man hinh, o chua
            error = painterResource(R.drawable.ic_broken_image), //Hien thi khi anh bi loi
            placeholder = painterResource(R.drawable.loading_img), //Hien thi trong khi cho load anh
            modifier = Modifier.fillMaxWidth()
        )
    }
}

/**
 * The home screen displaying the loading message.
 */
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

/**
 * The home screen displaying error message with re-attempt button.
 */
@Composable
fun ErrorScreen(
    retryAction: () -> Unit, //Lambda khong co gia tri tra ve
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction ) {
            Text(stringResource(R.string.retry))
        }
    }
}

/**
 * ResultScreen displaying number of photos retrieved.
 */
@Composable
fun ResultScreen(photos: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = photos)
    }
}

//Luoi trinh bay hinh anh
@Composable
fun PhotoGirdScreen(
    photos: List<MarsPhoto>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),//Chieu rong cua moi item
        modifier = modifier.padding(horizontal = 4.dp),
        contentPadding= contentPadding
    ) {
        items(items = photos, key = { photo -> photo.id}){ photo ->
            MarsPhotoCard(
                photo =photo,
                modifier = Modifier
                    .padding(4.dp)
                    .aspectRatio(1.5f)) //Thiet lap ti le khung hinh (Chieu rong so voi chieu cao) co dinh du hinh anh khac ty le, hinh anh co the bi phong to hoac cat mat mot phan
        }
    }
}



@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    MarsPhotosTheme {
        LoadingScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    MarsPhotosTheme {
        ErrorScreen(retryAction = {})
    }
}

@Preview(showBackground = true)
@Composable
fun PhotosGridScreenPreview() {
    MarsPhotosTheme {
        val mockData = List(10) { MarsPhoto("$it", "") }
        ResultScreen(stringResource(R.string.placeholder_success))
    }
}
