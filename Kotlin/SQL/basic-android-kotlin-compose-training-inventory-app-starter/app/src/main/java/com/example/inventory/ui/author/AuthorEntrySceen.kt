package com.example.inventory.ui.author

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.inventory.R
import com.example.inventory.ui.AppViewModelProvider
import com.example.inventory.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch

object AuthorEntryDestination : NavigationDestination {
    override val route = "author entry"
    override val titleRes = R.string.add_new_author
    override val icon = Icons.Default.Edit
}


@Composable
fun AddAuthorScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AuthorEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    var newAuthor by remember { mutableStateOf(AuthorDetails()) }


    Column (
        modifier = modifier
            .padding(WindowInsets.statusBars.asPaddingValues())
            .padding(dimensionResource(id = R.dimen.padding_small)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        OutlinedTextField(
            value = newAuthor.name,
            onValueChange = { newName ->
               newAuthor = newAuthor.copy(name = newName) },
            label = { Text(stringResource(R.string.author_name_rq)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))

        Button(
            onClick = {
                coroutineScope.launch {
                    viewModel.updateUiState(newAuthor)
                    viewModel.saveAuthor()
                    navigateBack()
                }
            },
            modifier = Modifier,
            shape = MaterialTheme.shapes.small,
            enabled = true
        ) {
            Text(stringResource(R.string.save_action))
        }
    }
}