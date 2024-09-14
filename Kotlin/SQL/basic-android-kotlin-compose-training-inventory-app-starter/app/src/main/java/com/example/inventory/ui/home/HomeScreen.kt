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

package com.example.inventory.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.inventory.InventoryTopAppBar
import com.example.inventory.R
import com.example.inventory.data.Item
import com.example.inventory.ui.AppViewModelProvider
import com.example.inventory.ui.item.formatedPrice
import com.example.inventory.ui.navigation.NavigationDestination
import com.example.inventory.ui.theme.InventoryTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.sp
import com.example.inventory.data.Author
import com.example.inventory.data.Book
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import java.time.format.TextStyle


object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
    override val icon = Icons.Default.Home
}

/**
 * Entry route for Home screen
 */
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemUpdate: (Int) -> Unit, //Di chuyen toi item da click, id cua item
    modifier: Modifier = Modifier,
    viewModel:  HomeViewModel = viewModel (factory = AppViewModelProvider.Factory)
) {
    //Nguon du lieu cap nhat lien tuc
    val bookUiState by viewModel.BooksUiState.collectAsState()
    val authorUiState by viewModel.AuthorsUiState.collectAsState()


    //Thiet lap cuon cho top bar
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { //Thanh bar tren cung ~ tieu de
            InventoryTopAppBar(
                title = stringResource(id = R.string.app_name),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
    ) { innerPadding ->
            HomeBookBody(
                bookList = bookUiState.bookList,
                authorList = authorUiState.authorList,
                onItemClick = navigateToItemUpdate,
                modifier = modifier
                    .fillMaxSize(), // Áp dụng padding từ Scaffold để tránh bàn phím che,
                contentPadding = innerPadding,
            )
    }
}

@Composable
fun HomeBookBody(
    bookList: List<Book>,
    authorList: List<Author>,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier.fillMaxSize()
    ) {
        if (bookList.isEmpty() && authorList.isEmpty()) {
            Text(
                text = stringResource(R.string.no_item_description),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier,
            )
        } else {
            BookList(
                bookList = bookList,
                authorList = authorList,
                onItemClick = { onItemClick(it.bookId) },
                contentPadding = contentPadding,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}


@Composable
fun BookList(
    bookList: List<Book>,
    authorList: List<Author>,//Danh sach cac item hien co
    onItemClick: (Book) -> Unit, //Duoc goi khi muc trong danh sach duoc nhan
    contentPadding: PaddingValues, //Khoang cach xung quanh danh sach
    modifier: Modifier = Modifier //Kieu dang, cach trinh bay cua lazy colum
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = contentPadding,
    ) {
        items(items =  bookList, key = { it.bookId }) { book ->

            val author = authorList.find { it.id == book.authorId }

            //Chi thuc hien neu author khong phai la null, neu la null thi bo qua chay den bien khac
            author?.let {
                BookCard(
                    book = book,
                    author = it,
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_small))
                        .clickable { onItemClick(book) }
                )
            }
        }
    }
}


@Composable
fun BookCard(
    book: Book,
    author: Author,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp), //thiet lap do cao co cad
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface, //nen
            contentColor = MaterialTheme.colorScheme.onSurface //mau chu
        )
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {
            Text(
                    text = book.name,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .wrapContentWidth() //tu dong xuong dong
            )

            Text(
                text = "${stringResource(id = R.string.vn_author_name_req)} ${author.name}",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "${stringResource(id = R.string.vn_shelf_number_req)} ${book.shelfNumber}",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}




/*
@Preview(showBackground = true)
@Composable
fun HomeBodyPreview() {
    InventoryTheme {
        HomeBody(listOf(
            Item(1, "Game", 100.0, 20), Item(2, "Pen", 200.0, 30), Item(3, "TV", 300.0, 50)
        ), onItemClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun HomeBodyEmptyListPreview() {
    InventoryTheme {
        HomeBody(listOf(), onItemClick = {})
    }
}


@Preview(showBackground = true)
@Composable
fun InventoryItemPreview() {
    InventoryTheme {
        InventoryItem(
            Item(1, "Game", 100.0, 20),
        )
    }
}
*/