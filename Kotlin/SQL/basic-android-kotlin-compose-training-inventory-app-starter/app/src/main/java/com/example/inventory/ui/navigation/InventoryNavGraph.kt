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

package com.example.inventory.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.inventory.ui.author.AddAuthorScreen
import com.example.inventory.ui.author.AuthorEntryDestination
import com.example.inventory.ui.book.BookEditDestination
import com.example.inventory.ui.book.BookEditScreen
import com.example.inventory.ui.book.BookEntryDestination
import com.example.inventory.ui.book.BookEntryScreen
import com.example.inventory.ui.home.HomeDestination
import com.example.inventory.ui.home.HomeScreen
import com.example.inventory.ui.info.InfoDestination
import com.example.inventory.ui.info.InfoScreen
import com.example.inventory.ui.item.BookDetailsDestination
import com.example.inventory.ui.item.BookDetailsScreen
import com.example.inventory.ui.item.ItemEditDestination
import com.example.inventory.ui.item.ItemEditScreen
import com.example.inventory.ui.item.ItemEntryDestination
import com.example.inventory.ui.item.ItemEntryScreen
import com.example.inventory.ui.search.SearchScreen
import com.example.inventory.ui.search.SearchScreenDestination

/**
 * Provides Navigation graph for the application.
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InventoryNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {

        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToItemUpdate = {
                    navController.navigate("${BookDetailsDestination.route}/${it}")
                }

                /*
                Ví dụ, nếu ItemDetailsDestination.route là "item_details" và it là 42,
                thì đường dẫn điều hướng sẽ trở thành "item_details/42".
                 */
            )
        }

        composable(route = ItemEntryDestination.route) {
            ItemEntryScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }

        /*
        composable(
            route = ItemDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemDetailsDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            ItemDetailsScreen(
                navigateToEditItem = { navController.navigate("${ItemEditDestination.route}/$it") },
                navigateBack = { navController.navigateUp() }
            )
        }
        */

        composable(
            route = BookDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(BookDetailsDestination.bookIdArg) {
                type = NavType.IntType
            })
        ) {
            BookDetailsScreen(
                navigateToEditBook = { navController.navigate("${BookEditDestination.route}/${it}") },
                navigateBack = { navController.navigateUp() }
            )
        }


        composable(
            route = BookEditDestination.routeWithArgs,
            arguments = listOf(navArgument(BookEditDestination.bookIdArg) {
                type = NavType.IntType
            })
        ) {
            InfoScreen()
            /*
            BookEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() },
                navController = navController
            )
             */
        }



        composable(
            route = ItemEditDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemEditDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            /*
            BookEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() },
                navController = navController
            )
            */

            ItemEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )

        }



        composable(route = SearchScreenDestination.route,
        ){
            SearchScreen(
                navigateToItemUpdate = {
                    navController.navigate("${BookDetailsDestination.route}/${it}")
                }

                /*
                Ví dụ, nếu ItemDetailsDestination.route là "item_details" và it là 42,
                thì đường dẫn điều hướng sẽ trở thành "item_details/42".
                 */
            )
        }


        composable(route = InfoDestination.route,
        ){
            //InfoScreen()
            BookEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() },
                navController = navController
            )
        }


        composable(route = BookEntryDestination.route,
        ){
            BookEntryScreen(
                navigateBack = { navController.navigate(BookEntryDestination.route) },
                onNavigateUp = {  navController.navigateUp()},
                navController = navController
            )
        }

        composable(route = AuthorEntryDestination.route,){
            AddAuthorScreen(
                navigateBack = { navController.navigate(BookEntryDestination.route) }
            )
        }

    }
}
