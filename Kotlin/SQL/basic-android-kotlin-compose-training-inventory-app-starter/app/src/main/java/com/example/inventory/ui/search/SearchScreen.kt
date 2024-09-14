package com.example.inventory.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.inventory.R
import com.example.inventory.ui.home.HomeViewModel
import com.example.inventory.ui.navigation.NavigationDestination
import com.example.inventory.ui.theme.InventoryTheme
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.inventory.InventoryTopAppBar
import com.example.inventory.data.Item
import com.example.inventory.ui.AppViewModelProvider
import com.example.inventory.ui.item.formatedPrice
import com.example.inventory.ui.theme.InventoryTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.sp
import com.example.inventory.data.Author
import com.example.inventory.data.Book
import com.example.inventory.ui.book.customTextFieldColors
import com.example.inventory.ui.home.BookList
import com.example.inventory.ui.home.HomeBookBody
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import java.time.format.TextStyle

object SearchScreenDestination : NavigationDestination {
    override val route = "Search"
    override val titleRes = R.string.search
    override val icon = Icons.Default.Search
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navigateToItemUpdate: (Int) -> Unit, //Di chuyen toi item da click, id cua item
    modifier: Modifier = Modifier,
    viewModel:  HomeViewModel = viewModel (factory = AppViewModelProvider.Factory)
) {

    val searchUiState by viewModel.searchUiState.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val authorUiState by viewModel.AuthorsUiState.collectAsState()

    //Thiet lap cuon cho top bar
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { //Thanh bar tren cung ~ tieu de
            SearchBarHome (
                searchQuery = searchQuery,
                viewModel = viewModel,
                scrollBehavior = scrollBehavior
            )
        },
    ) { innerPadding ->
        HomeBookBody(
            bookList = searchUiState.bookList,
            authorList = authorUiState.authorList,
            onItemClick = navigateToItemUpdate,
            modifier = modifier
                .fillMaxSize(), // Áp dụng padding từ Scaffold để tránh bàn phím che,
            contentPadding = innerPadding,
        )
    }
}




@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchBarHome(
    searchQuery: String,
    viewModel: HomeViewModel,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    modifier: Modifier = Modifier,
){
    // Lấy màu nền hiện tại của hệ thống
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()
    val statusBarColor = MaterialTheme.colorScheme.background

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = statusBarColor,
            darkIcons = useDarkIcons
        )
    }

    CenterAlignedTopAppBar(
        title = {

            Box(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { viewModel.updateSearchQuery(it) }, // Truyền chuỗi tìm kiếm vào ViewModel
                    label = { Text(text = stringResource(R.string.search)) },
                    modifier = modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    shape = MaterialTheme.shapes.medium,
                    leadingIcon = { // Thêm biểu tượng tìm kiếm bên trái
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon"
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done // Thiết lập hành động Done cho bàn phím
                    ),
                    singleLine = true,
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 15.sp),
                    colors = customTextFieldColors()
                )
            }
        },
        scrollBehavior = scrollBehavior,
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_small))
    )
}



/*
@Preview(showBackground = true)
@Composable
fun SearchHomeBodyPreview() {
    InventoryTheme {
        SearchBody(listOf(
            Item(1, "Game", 100.0, 20), Item(2, "Pen", 200.0, 30), Item(3, "TV", 300.0, 50)
        ), onItemClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun SearchHomeBodyEmptyListPreview() {
    InventoryTheme {
        SearchBody(listOf(), onItemClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun SearchInventoryItemPreview() {
    InventoryTheme {
        InventoryItem(
            Item(1, "Game", 100.0, 20),
        )
    }
}



@Composable
fun SearchScreenDratf(
    modifier: Modifier = Modifier
){
    Column (
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.search),
            style = MaterialTheme.typography.titleLarge)
    }
}


*/