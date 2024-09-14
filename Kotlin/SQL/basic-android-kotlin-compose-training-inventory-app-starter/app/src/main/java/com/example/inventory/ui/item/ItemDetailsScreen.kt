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

package com.example.inventory.ui.item

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.inventory.InventoryTopAppBar
import com.example.inventory.R
import com.example.inventory.data.Item
import com.example.inventory.ui.navigation.NavigationDestination
import com.example.inventory.ui.theme.InventoryTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.inventory.ui.AppViewModelProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.inventory.data.Author
import com.example.inventory.data.Book
import com.example.inventory.ui.book.BookDetails
import com.example.inventory.ui.book.toBook

/*
    object: Khai báo một đối tượng singleton,
    có nghĩa là ItemDetailsDestination chỉ có một thể hiện duy nhất trong toàn bộ ứng dụng
 */

/*
object ItemDetailsDestination : NavigationDestination {
    override val route = "item_details"
    override val titleRes = R.string.item_detail_title
    override val icon = Icons.Default.Edit
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}
 */

object BookDetailsDestination : NavigationDestination {
    override val route = "book_details"
    override val titleRes = R.string.book_detail_title
    override val icon = Icons.Default.Home
    const val bookIdArg = "bookId"
    val routeWithArgs = "$route/{$bookIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailsScreen(
    navigateToEditBook: (Int) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ItemDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val bookDetailsuiState = viewModel.bookDetailsUiState.collectAsState() //Bien chua du lieu tu viewmodel
    val authorUiState by viewModel.AuthorsUiState.collectAsState()


    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = {
            InventoryTopAppBar(
                title = stringResource(BookDetailsDestination.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack,
                scrollBehavior = scrollBehavior
            )

        }, floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateToEditBook(viewModel.bookId) },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))

            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(R.string.edit_item_title),
                )
            }
        }, modifier = modifier.padding(top = 4.dp)
    ) { innerPadding ->
        BookDetailsBody(
            bookDetailsUiState = bookDetailsuiState.value,
            authorList = authorUiState.authorList,
            viewModel = viewModel,
            onSaveToLibrary = { viewModel.saveToLibrary() },
            onDelete = {
                viewModel.DeleteBook()
                navigateBack()
            },
            modifier = Modifier
                .padding(
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
                    top = innerPadding.calculateTopPadding()
                )
                .verticalScroll(rememberScrollState())
        )
    }
}

@Composable
private fun BookDetailsBody(
    bookDetailsUiState: BookDetailsUiState,
    authorList: List<Author>,
    viewModel: ItemDetailsViewModel,
    onSaveToLibrary: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }

        /*
        C1
        val isBookSave by viewModel.isBooKSave.collectAsState() //chuyen doi ket qua tu flow sang state de ui cap nhat
        var enabledSaveBook2 by rememberSaveable { mutableStateOf(!isBookSave) }

        LaunchedEffect(isBookSave) {
            enabledSaveBook2 = !isBookSave
        }

        C2
        val isBookSave by viewModel.isBooKSave.collectAsState()
        var enabledSaveBook2 = !isBookSave
        */



        //C3
        var enabledSaveBook by rememberSaveable { mutableStateOf(!bookDetailsUiState.bookDetails.saveToLibrary) }

            // Dam bao cap nhat du lieu moi nhat khi co thay doi ve du lieu do
            // Sử dụng LaunchedEffect để theo dõi sự thay đổi của bookDetailsUiState
        LaunchedEffect(bookDetailsUiState.bookDetails.saveToLibrary) {
            enabledSaveBook = !bookDetailsUiState.bookDetails.saveToLibrary
        }


        BookDetailsCard(
            book = bookDetailsUiState.bookDetails.toBook(),
            authorList = authorList,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                onSaveToLibrary ()
                enabledSaveBook = false
            },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.small,
            enabled = enabledSaveBook
        ) {
            Text(stringResource(R.string.save_to_library))
        }


        OutlinedButton(
            onClick = { deleteConfirmationRequired = true },
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.delete))
        }

        if (deleteConfirmationRequired) {
            DeleteConfirmationDialog(
                onDeleteConfirm = {
                    deleteConfirmationRequired = false
                    onDelete()
                },
                onDeleteCancel = { deleteConfirmationRequired = false },
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))
            )
        }

    }
}




@Composable
fun BookDetailsCard(
    book: Book,
    authorList: List<Author>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer, //nen
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer //mau chu
        )
    ) {
        val paddingRow = dimensionResource(id = R.dimen.padding_medium)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_small)),

            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            val authorName = authorList.find { it.id == book.authorId }?.name ?: "Unknown Author"

            BookDetailsRow(
                labelResID = R.string.vn_book_name_req,
                bookDetail = book.name,
                modifier = Modifier.padding(
                    horizontal = paddingRow
                )
            )

            BookDetailsRow(
                labelResID = R.string.vn_author_name_req,
                bookDetail = authorName,
                modifier = Modifier.padding(
                    horizontal = paddingRow
                )
            )

            BookDetailsRow(
                labelResID = R.string.vn_book_type_req,
                bookDetail = book.type,
                modifier = Modifier.padding(
                    horizontal = paddingRow
                )
            )

            BookDetailsRow(
                labelResID = R.string.vn_publication_info_req,
                bookDetail = book.publicationInfo,
                modifier = Modifier.padding(
                    horizontal = paddingRow
                )
            )

            BookDetailsRow(
                labelResID = R.string.vn_shelf_number_req,
                bookDetail = book.shelfNumber,
                modifier = Modifier.padding(
                    horizontal = paddingRow
                )
            )

            BookDetailsRow(
                labelResID = R.string.vn_subject_req,
                bookDetail = book.subject,
                modifier = Modifier.padding(
                    horizontal = paddingRow
                )
            )

            BookDetailsRow(
                labelResID = R.string.vn_physical_description_req,
                bookDetail = book.physicalDescription,
                modifier = Modifier.padding(
                    horizontal = paddingRow
                )
            )
        }
    }
}



/*
@Composable
fun ItemDetailsRow(
    @StringRes labelResID: Int, itemDetail: String, modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        Text(stringResource(labelResID))

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = itemDetail,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .wrapContentWidth(Alignment.End)
        )
    }
}

*/





@Composable
fun BookDetailsRow(
    @StringRes labelResID: Int,
    bookDetail: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_small)),
        //horizontalArrangement = Arrangement.SpaceBetween // Căn chỉnh các thành phần
    ) {
        Text(
            stringResource(labelResID),
            modifier = Modifier
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = bookDetail,
            modifier = Modifier
                .wrapContentWidth(),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.End
        )
    }
}








@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(onDismissRequest = { /* Do nothing */ },
        title = { Text(stringResource(R.string.attention)) },
        text = { Text(stringResource(R.string.delete_question)) },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(stringResource(R.string.no))
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(stringResource(R.string.yes))
            }
        })
}






/*


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ItemDetailsScreenBodyPreview() {
    InventoryTheme {
        ItemDetailsBody(
            ItemDetailsUiState(
                outOfStock = true,
                itemDetails = ItemDetails(1, "Pen", "$100", "10")
            ),
            onSellItem = {},
            onDelete = {}
        )
    }
}
*/