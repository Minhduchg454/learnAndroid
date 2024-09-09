/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.cupcake

import android.content.Context
import android.content.Intent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cupcake.data.DataSource
import com.example.cupcake.ui.OrderSummaryScreen
import com.example.cupcake.ui.OrderViewModel
import com.example.cupcake.ui.SelectOptionScreen
import com.example.cupcake.ui.StartOrderScreen

/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */



/*
Enum class (lớp liệt kê) trong Kotlin là một cách để định nghĩa một tập hợp các hằng số liên quan đến nhau.
Lớp này thường được sử dụng khi bạn có một tập hợp các giá trị cố định và hữu hạn mà bạn muốn làm việc với.

Khi bạn định nghĩa một enum class với các tham số như thế này,
+ mỗi giá trị của enum class có thể được sử dụng như một đối tượng với các thuộc tính và phương thức của riêng nó
+Trong trường hợp này, mỗi giá trị của CupcakeScreen có một thuộc tính title đại diện cho tiêu đề của màn hình.
 */

enum class CupcakeScreen(@StringRes val title: Int){
    Start(title = R.string.app_name),
    Flavor(title = R.string.choose_flavor),
    Pickup(title = R.string.choose_pickup_date),
    Summary(title = R.string.order_summary)
}

@Composable
fun CupcakeAppBar(
    currentScreen: CupcakeScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}



@Composable
fun CupcakeApp(
    viewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()

        /*
        Biến backStackEntry là một trạng thái Compose (Compose state) chứa thông tin về mục hiện tại trong ngăn xếp điều hướng của navController.
        Bạn có thể sử dụng backStackEntry để truy cập các thông tin như màn hình đang hiển thị hiện tại, thông tin về đối tượng đích của màn hình, v.v.

        Khi bạn khai báo backStackEntry với by navController.currentBackStackEntryAsState(),
        Compose sẽ tự động theo dõi và cập nhật biến này khi trạng thái của navController thay đổi. Điều này giúp bạn cập nhật giao diện người dùng dựa trên thay đổi trong ngăn xếp điều hướng một cách hiệu quả

        By dùng để tự dộng cập nhật khi thay đổi

         */

    val currentScreen = CupcakeScreen.valueOf(
        backStackEntry?.destination?.route ?: CupcakeScreen.Start.name)

        /*
        CupcakeScreen.valueOf(...) là một phương thức trong enum class CupcakeScreen của bạn.
        +Nó được sử dụng để chuyển đổi một chuỗi (String) thành một giá trị enum tương ứng.

        backStackEntry?.destination?.route : xac dinh gia tri cua man hinh hien tai dang hien khi trong ung dung , trong ngan xep lui dieu huong

        Dat gia tri man hinh hien tai la start mac dinh khi backStackEntry?.destination?.route ? co gia tri null

        Hoat dong
        + Nếu backStackEntry?.destination?.route không phải là null, nó sẽ cố gắng chuyển đổi chuỗi route thành một giá trị enum từ enum class CupcakeScreen.
        + Nếu backStackEntry?.destination?.route là null, nó sẽ chuyển đổi chuỗi "Start" (từ CupcakeScreen.Start.name) thành một giá trị enum từ enum class CupcakeScreen.
         */

    Scaffold(
        topBar = {
            CupcakeAppBar(
                currentScreen =currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null , //Kiem tra co ton tai mot man hinh phia truoc trong ngan xep khong, tuc co gia tri khac null
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        /*
        NavHost là một Thành phần kết hợp hiển thị các đích đến có thể kết hợp khác,
        dựa trên một tuyến đường nhất định

        navController: Một phiên bản của lớp NavHostController.
        + Bạn có thể sử dụng đối tượng này để điều hướng giữa các màn hình, ví dụ: bằng cách gọi phương thức điều hướng() để điều hướng đến đích khác.
        + Bạn có thể lấy NavHostController bằng cách gọi RememberNavController() từ một hàm có khả năng kết hợp.

        startDestination: Một tuyến chuỗi xác định đích được hiển thị theo mặc định khi ứng dụng hiển thị NavHost lần đầu tiên.
        + Trong trường hợp ứng dụng Cupcake, đây sẽ là lộ trình Bắt đầu.
         */

        NavHost(
            navController = navController,
            startDestination = CupcakeScreen.Start.name, //name là một thuộc tính của mỗi giá trị enum trong Kotlin. Nó trả về tên của giá trị enum dưới dạng một chuỗi (String). Trong trường hợp này, CupcakeScreen.Start.name sẽ trả về chuỗi "Start".
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(route = CupcakeScreen.Start.name){
                StartOrderScreen(
                    quantityOptions = DataSource.quantityOptions,
                    onNextButtonClicked = {
                        viewModel.setQuantity(it)
                        navController.navigate(CupcakeScreen.Flavor.name)
                                          },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                )
            }

            composable(route = CupcakeScreen.Flavor.name){
                val context = LocalContext.current
                /*
                Nó cho phép truy cập vào các tài nguyên và lớp dành riêng cho ứng dụng
                , cũng như các lệnh gọi lên cho các hoạt động ở cấp ứng dụng như khởi chạy các hoạt động, v.v.
                Bạn có thể sử dụng biến này để lấy các chuỗi từ danh sách ID tài nguyên trong mô hình khung nhìn để hiển thị danh sách các hương vị.
                 */

                SelectOptionScreen(
                    subtotal = uiState.price,
                    onNextButtonClicked = {navController.navigate(CupcakeScreen.Pickup.name)},
                    onCancelButtonClicked = { cancelOrderAndNavigateToStart(viewModel,navController) },
                    options = DataSource.flavors.map { id -> context.resources.getString(id) },
                    onSelectionChanged = {viewModel.setFlavor(it)},
                    modifier = Modifier.fillMaxHeight()
                )
            }

            composable(route = CupcakeScreen.Pickup.name){
                SelectOptionScreen(
                    subtotal = uiState.price,
                    onNextButtonClicked = {navController.navigate(CupcakeScreen.Summary.name)},
                    onCancelButtonClicked = { cancelOrderAndNavigateToStart(viewModel,navController) },
                    options = uiState.pickupOptions,
                    onSelectionChanged = {viewModel.setDate(it)},
                    modifier = Modifier.fillMaxHeight()
                )
            }

            composable(route = CupcakeScreen.Summary.name){
                val context = LocalContext.current
                OrderSummaryScreen(
                    orderUiState = uiState,
                    onCancelButtonClicked = { cancelOrderAndNavigateToStart(viewModel,navController) },
                    onSendButtonClicked = {subject: String, summary: String ->
                                          shareOrder(context,subject,summary)

                    },
                    modifier = Modifier.fillMaxHeight()
                    )
            }
        }

    }
}




//Truyen du lieu sang ung dung khac
//+ Create an intent object and specify the intent, such as ACTION_SEND.
//+ Specify the type of additional data being sent with the intent. For a simple piece of text, you can use "text/plain", though other types, such as "image/*" or "video/*", are available.
//+ Pass any additional data to the intent, such as the text or image to share, by calling the putExtra() method. This intent will take two extras: EXTRA_SUBJECT and EXTRA_TEXT.
//+ Call the startActivity() method of context, passing in an activity created from the intent

private fun shareOrder (
    context: Context, //Cung cap thong tin trang thai cua ung dung va giup tuong tac voi he thong android
    subject: String, //Tieu de
    summary: String //Noi dung
    ){
    val intent = Intent(Intent.ACTION_SEND).apply {
        //Goi apply de tao doi tuong intent va truyen vao ham lambda
        type = "text/plain" //Kieu du lieu truyen
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, summary)
    }
    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.new_cupcake_order)
        )
    )


}






private fun cancelOrderAndNavigateToStart(
    viewModel : OrderViewModel,
    navControler: NavHostController
){
    viewModel.resetOrder()
    navControler.popBackStack(CupcakeScreen.Start.name, inclusive = false)

    /*
    Phương thức popBackStack() có hai tham số bắt buộc.
    route: Chuỗi biểu thị tuyến đường của đích bạn muốn quay lại.
    inclusive: Giá trị Boolean, nếu đúng, cũng bật (xóa) tuyến đường đã chỉ định.
    Nếu sai, popBackStack() sẽ xóa tất cả các đích ở trên cùng—nhưng không bao gồm—đích bắt đầu,
        +để nó ở màn hình trên cùng hiển thị cho người dùng.
    */
}
