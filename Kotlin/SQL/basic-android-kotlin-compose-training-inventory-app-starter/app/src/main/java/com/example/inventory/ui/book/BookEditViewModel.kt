package com.example.inventory.ui.book

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventory.data.AuthorsRepository
import com.example.inventory.data.BooksRepository
import com.example.inventory.ui.item.BookDetailsUiState
import com.example.inventory.ui.item.ItemDetails
import com.example.inventory.ui.item.ItemUiState
import com.example.inventory.ui.item.toItem
import com.example.inventory.ui.item.toItemUiState
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/*
    Lấy dữ liệu và cập nhật từ [BookRepository]


 */

class BookEditViewModel (
    savedStateHandle: SavedStateHandle,
    private val bookRepository: BooksRepository
): ViewModel() {

    /*
    Biến có thể thay đổi trạng thái được quản lý bởi viewmodl

    Biến này lưu trữ trạng thái UI hiện tại của book,
        + bao gồm các chi tiết của item (bookDetails)
        + trạng thái hợp lệ của dữ liệu nhập (isEntryValid).
     */
    var bookUiState by mutableStateOf(BookUiState())


    /*
    Id của book dang quản lý lấy từ savedStateHandle
     */
    val bookId: Int = checkNotNull(savedStateHandle[BookEditDestination.bookIdArg])

    /*
    Nhận một đối tượng BookDetail (Các thuộc tính của book)
        + đc gán một giá trị mặc định là bookUiState.bookDetails

    Kiểm tra tính hợp lệ của dữ liệu nhập, xem các thuộc tính có trống không.
        +Nếu không trống nhận true người lại nhân false
     */
    private fun validateInput(uiState: BookDetails = bookUiState.bookDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && type.isNotBlank() && publicationInfo.isNotBlank()
                    && shelfNumber.isNotBlank() && subject.isNotBlank() && physicalDescription.isNotBlank()
        }
    }



    /*
    Khởi tạo trạng thái của item khi ViewModel được khởi tạo.

    Khi lớp ItemEditViewModel được khởi tạo, phương thức init sẽ được gọi.
        +   Trong phương thức này, một coroutine được khởi chạy trong viewModelScope.

    Coroutine này lấy luồng dữ liệu của item từ ItemsRepository
        +   bằng cách sử dụng phương thức getItemStream(itemId).
        +   Luồng này sẽ phát ra giá trị null khi item không tồn tại và giá trị của item khi nó tồn tại.

    Hàm filterNotNull() được sử dụng để loại bỏ các giá trị null từ luồng.
    Sau đó, first() lấy giá trị đầu tiên không null từ luồng và
        + chuyển đổi nó thành ItemUiState bằng cách gọi hàm mở rộng toItemUiState(true) với đối số true để bật nút "Save" (nút lưu).

     */
    init {
        viewModelScope.launch {
            bookUiState = bookRepository.getBookStream(bookId)
                .filterNotNull()
                .first()
                .toBookUiState(true)
        }
    }


    /*
    Phương thức này cập nhật trạng thái UI (itemUiState)
        + khi người dùng thay đổi thông tin chi tiết của item (itemDetails).

    Nó gọi lại validateInput để cập nhật trạng thái hợp lệ (isEntryValid) dựa trên dữ liệu mới.
     */
    fun updateUiState (bookDetails: BookDetails){
        bookUiState = BookUiState(bookDetails = bookDetails, isEntryValid = validateInput(bookDetails))
    }

    suspend fun updateBook(){
        if (validateInput(bookUiState.bookDetails)) { //Kiem tra nguoi dung nhap hop le
            bookRepository.updateBook(bookUiState.bookDetails.toBook()) //cap nhat lai gia tri
        }
    }

}