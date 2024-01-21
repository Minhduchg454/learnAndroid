package eu.tutorials.myshoppinglistapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation.width
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


//B3
    //Dinh dang kieu du lieu can hien thi
data class ShoppingItem (val id:Int,
                         var name:String, //ten
                         var quantity:Int, //so luong
                         var isEditting :Boolean = false //kiem tra tinh trang chinh sua
)

@Composable
fun ShoppingListApp(){

    //B4:
        //Bien duy tri trang thai, tu dong cap nhat tren giao dien nguoi dung khi cap nhat gia tri moi
        //listOf để tạo một danh sách không thay đổi
    var sItems by remember { mutableStateOf(listOf<ShoppingItem>()) } //Tao bien voi du lieu la mot danh sach kieu du lieu ShoppingItem
    var showDialog by remember { mutableStateOf(false) }//Hien thi hop thoai
    var itemName by remember { mutableStateOf("") } //Ten do vat
    var itemQuanity by remember { mutableStateOf("")} //So luong do vat, do dang dung hop thoai nhap truong van ban nen la kieu string

    //Dinh dang kieu chu
    val customTextStyle = TextStyle (
        fontFamily = FontFamily.Default,
        fontSize = 32.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold
    )


    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ){
        //Title app
        Text(text = "SHOPPING LIST",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(10.dp),
            style = customTextStyle)


        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {

    //B2:
        // Lazy column: hien thi cac muc trong danh sach, chi hien thi cac muc trong khong gian hien thi
        // + Hien thi cac muc co gia tri, chi hien thi cac muc can xem
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp) //tao khoang cach giua nut Title va cot luoi
            ){
                items(listOf("Apple", "Banana", "Cherry")){
                    item ->
                    CustomCard (data = item)
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp) //tao khoang cach giua nut Title va cot luoi
            ) {

    //B5:
                items(sItems) { item ->
                    /*
                        mat hang mua sam nhan duoc it
                        co the su dung item thay the it voi dieu kien la it ->
                    */

                    if (item.isEditting) {  //Kiem tra trang thai chinh sua, neu dung hien thi giao dien chinh sua
                        ShoppingItemEditor(item = item,
                                            onEditComplete = { editedName, editedQuantity -> //dieu gi xay ra khi chinh sua xong

                                                //Than ham onEditComplete

                                                sItems = sItems.map { it.copy(isEditting = false) }
                                                    /*
                                                        Dam bam muc dang chinh sua, chuyen noi thanhh gia tri false
                                                        Ham map tao ban sao cua chuoi sItem voi cac phan tu co isEditting thanhg false
                                                        Sao do gan danh sach moi nay nay vao lai sItems
                                                     */


                                                val editedItem = sItems.find { it.id == item.id }
                                                    /*
                                                        Tao mot tham chieu
                                                        Duyet tat ca cac id trong danh sach
                                                         va tim mot muc ben trong danh sach co id dung voi ten cua item

                                                        Kết quả là một tham chiếu đến đối tượng trong danh sách,
                                                            không phải là một bản sao. Điều này có nghĩa là editedItem và
                                                            phần tử tương ứng trong sItems đều tham chiếu đến cùng một đối tượng trong bộ nhớ.

                                                        Khi bạn thay đổi nội dung của editedItem,
                                                            bạn đang thực sự thay đổi nội dung của đối tượng trong
                                                            danh sách sItems, vì cả hai tham chiếu đều trỏ đến cùng một đối tượng.
                                                     */

                                                editedItem?.let {  //Ham let thuc hien cac thoai tac tren chuoi neu co gia tri, neu doi tuong khong co gia tri thi khong thuc hien
                                                    it.name = editedName
                                                    it.quantity = editedQuantity }
                                        })

                    } else { //Kiem tra trang thai chinh sua, sai hien thi danh sach mua sam
                        ShoppingListItem(item = item,
                            onEditClick = {
                                sItems = sItems.map { it.copy(isEditting = it.id == item.id) }
                                          /*
                                                Tim ra muc can chinh sua va gan isEditting cua no bang true
                                                Finding out which item we are editing and
                                                    changing is "isEditting boole" to true

                                                Tao mot danh sach moi va thay doi gia tri isEditting, xet tung phan tung trong danh sach:
                                                    + neu phan tu nao trung voi item.id thi gan thanh isEditting = True
                                                    + nguoc lai neu id khac voi item.id thi gan thanh isEditting = False
                                                Gan danh sach moi nay thay the danh sach cu



                                           */

                            },
                            onDeleteClick = {
                                sItems = sItems - item //Khi chon nut xoa, loai bo muc dang xet khoi dang sach
                            })
                    }
                }
            }
        }

        //B1
            //Buton Add Item
        Button(
            onClick = { showDialog = true }, //Khi bam nut, thi cho gia tri showDialog = true, hien len bang them
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp) //canh chinh nut ngay giua hang ngay
        ) {
            Text(text = "Add Item",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold)
            )
        }

        Text(text = "Design by HuuDuc",
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            fontSize = 13.sp
        )

    }

    //B6:
        //Thiet lap hop thoai
        //Neu gia tri la dung thi hien thi hop thoai
    if (showDialog){
       AlertDialog(
           onDismissRequest = { showDialog = false }, //Neu bam ben ngoai hop thoai thi dong lai
           confirmButton = { //Hanh dong thuc hien trong hop thoai
                           Row (modifier = Modifier
                               .fillMaxWidth()
                               .padding(8.dp),
                               horizontalArrangement = Arrangement.SpaceBetween) //Tao khoang cach canh cach khoang trong cho cac phan tu
                           {
                               //Button "Add"
                               Button(onClick = {
                                   if (itemName.isNotBlank()){ //Neu itemName khac rong tuc la co nhap gia tri
                                       val newItem = ShoppingItem(
                                           id = sItems.size+1,
                                           name =itemName,
                                           quantity = itemQuanity.toInt()
                                       )
                                       sItems = sItems + newItem
                                       showDialog = false
                                       itemName=""
                                       itemQuanity=""
                                   }
                               }) {
                                   Text(text = "Add")
                               }

                               //Button "Cancel"
                               Button(onClick = {
                                    showDialog =false
                               }) {
                                   Text(text = "Cancel")
                               }
                           }
           },

           //Cac noi dung hien thi trong DiaLog
           title = { Text(text = "Add Item")},
           text = {
               Column {

                   //Text fiedld "Enter item name"
                   OutlinedTextField(
                       value = itemName,
                       onValueChange = {itemName = it}, // khi nhap gia tri moi vao, gia tri nay se gan vao bien itemNam
                       singleLine = true,
                       modifier = Modifier  //Lam rong toi da chieu ngang, va cach bien mot khoang 8dp
                           .fillMaxWidth()
                           .padding(8.dp),
                       label = {Text(text = "Enter item name") }
                   )

                   //Text fiedld "Enter quantity"
                   OutlinedTextField(
                       value = itemQuanity,
                       onValueChange = {itemQuanity = it},
                       singleLine = true,
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(8.dp),
                       label = {Text(text = "Enter quantity") }
                   )
               }
           }
       )

    }
}


//B8:
    //Giao dien trinh chinh sua
@Composable
fun ShoppingItemEditor (item :ShoppingItem,
                        onEditComplete: (String, Int) -> Unit){
    //Ghi de len bien cu
    var editedName by remember { mutableStateOf(item.name)} //Tao mot doi tuong co gia tri ban dau la item.name
    var editedQuantity by remember { mutableStateOf(item.quantity.toString()) }
    var isEditing by remember { mutableStateOf(item.isEditting) }

    //Hien thi
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .background(Color.White),
        horizontalArrangement = Arrangement.SpaceEvenly,//chia ngang thanh phan khong gian deu nhau
        verticalAlignment = Alignment.CenterVertically) //chia doc, ngay giua
    {

        // TextField: Name and Quantity
        Column  {
            //TextField: Name
                BasicTextField(
                    value = editedName,
                    onValueChange = {editedName = it},
                    singleLine = true,
                    modifier = Modifier
                        .padding(8.dp)
                        .border(
                            border = BorderStroke(1.dp, Color.Black),
                            shape = RoundedCornerShape(20)
                        )
                        .padding(3.dp)
                )


            //TextField: Quantity
                BasicTextField(
                    value = editedQuantity,
                    onValueChange = {editedQuantity= it},
                    singleLine = true,
                    modifier = Modifier
                        .padding(8.dp)
                        .border(
                            border = BorderStroke(1.dp, Color.Black),
                            shape = RoundedCornerShape(20)
                        )
                        .padding(3.dp)
                )
        }

            //Button "Save"
            Button(onClick = { //Chuyen gi xay ra khi click nut nay
                isEditing = false
                onEditComplete (editedName, editedQuantity.toIntOrNull() ?: 1) //goi ham onEditComplete, truyen gia tri vua nhap vao ham
            }) {
                Text (text = "Save")

            }

    }
}


// B7:
    //Giao dien hien thi danh sach
@Composable
fun ShoppingListItem(
    item: ShoppingItem,
    onEditClick: () -> Unit, //Thuc thi mot ham thi click chuot ~Lambda; khong co dau vao va khong co dau ra nhung co ma thu thi
    onDeleteClick: () -> Unit,
){
    Row (
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .border( //Tao khung quanh noi dung can hien thi
                border = BorderStroke(2.dp, Color.Black),
                shape = RoundedCornerShape(20) // 20% bo tron
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text (text = "Name: ${item.name}", modifier = Modifier.padding(8.dp))
        Text (text = "Qty: ${item.quantity}", modifier = Modifier.padding(8.dp))

        // Tao cac nut de kich hoat lenh
        Row (modifier = Modifier.padding(8.dp)) {
            IconButton(onClick = onEditClick) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = null)
            }
            IconButton(onClick = onDeleteClick) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }
    }
}






/*Bieu thuc Lambda
 //Dua vao gia tri va thay doi gia tri, tuong tu cach hoat dong cua ham

    val doubleNumber: (Int) -> Int = {it * 2} //it la bat cu gia tri gi nhap vao
    Text(doubleNumber(5).toString())

 //ket qua tra ve la 10

*/


/*Map
 //Duyet qua tung phan tu trong mang va thay doi gia tri theo mong muon

    fun main (){
        val numbers = listOf(1,2,3)
        val doubled = numbers.map{it *2}
        println (doubled)
    }


 //ket qua tra ve se la 2,3,4

 */

/*Vd2:  map
    fun main (){
    var number = listOf (1,2,3,4)
    println(number)
    number = number.map { it *2 }
    println(number)

}
 */





/* Method copy

    fun main (){
    val blueRoseVase = Vase(color = "blue", design= "Rose")
    val redRoseVase = blueRoseVase.copy(color ="red") //nhap gia tri muon thay doi
    println(blueRoseVase)
    println(redRoseVase)

    //Ket qua tra ve
    /*
        Vase(color=blue, design=Rose)
        Vase(color=red, design=Rose)
     */
    }

    data class Vase (val color: String, val design: String)

 */





/* Vd: let
    fun main (){
    val name :String? = "Ella"

    /* Bien nay co y nghia la chuoi hay co the Null
        Khai bao bien nam co kieu du lieu la String va gan tra tri ban dau cho no la Ella
        ? sau String cho biet rang bien name co the chua gia ri null, neu khong co dau ? bien khong the chua gia tri null
    */


    /*Let:
        Chi thuc hien tuong tuong nhu try catch
        Thuc hien hanh dong chi khi doi tuong khong phai la null, neu la null thi khong thuc hien gì ca
    */

    name?.let{
        println(it.toUpperCase())
    }

    println(name)


    /*
        Ket qua tra ve
        ELLLA
        Ella
    */

}

*/

/* vd let

    fun main() {
    var length: String? = null
    length?.let {
        length = it.toUpperCase()
    }
    println("Nội dung của chuỗi là: $length")
}


 */
/* vidu let
   //Chi thuc hien thi bien khac null

        fun main() {
            var name: String? = "Lan"

            name?.let {
                println("Name is $it")
            }

            name = null
             name?.let {
                println("Name is $it")
            }
        }
 */





 */


/*Vi du: lazy column
    LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp) //tao khoang cach giua nut Title va cot luoi
            ){
                items(listOf("Apple", "Banana", "Cherry")){
                    item ->
                    Text (text = item)
                }
            }
 */




