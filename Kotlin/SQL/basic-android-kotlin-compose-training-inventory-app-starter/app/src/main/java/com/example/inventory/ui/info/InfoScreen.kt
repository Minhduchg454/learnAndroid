package com.example.inventory.ui.info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.inventory.R
import com.example.inventory.data.Item
import com.example.inventory.ui.navigation.NavigationDestination
import com.example.inventory.ui.theme.InventoryTheme

object InfoDestination : NavigationDestination {
    override val route = "Info"
    override val titleRes = R.string.info_button
    override val icon = Icons.Default.Info
}


@Composable
fun InfoScreen(
    modifier: Modifier = Modifier
){
    Column (
        modifier = Modifier
            .padding(WindowInsets.statusBars.asPaddingValues())
            .padding(dimensionResource(id = R.dimen.padding_small)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = stringResource(id = R.string.info_button),
            style = MaterialTheme.typography.titleLarge)
    }
}


@Preview(showBackground = true)
@Composable
fun InfoScreenPreview() {
    InventoryTheme {
        InfoScreen()
    }
}