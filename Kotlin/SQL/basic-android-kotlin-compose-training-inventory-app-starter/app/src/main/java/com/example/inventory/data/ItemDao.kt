package com.example.inventory.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


/*

    DATABASE ACCESS OBJECT (DAO) là một mẫu mà bạn có thể sử dụng để tách CSDL (Database))
        + khỏi phần còn lại của ứng dụng bằng cách cung cấp một giao diện trừu tượng.

    Các yêu cầu ứng dụng <-> DAO: câu lệnh SQL < -> DATA BASE

    Sử dụng Dao tự dộng sinh mã mỗi câu lênh truy vấn, chỉ cần ghi chú

 */

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE) //Giai phap khi xay ra xung dot, bo qua muc moi
    suspend fun insert(item: Item)

    @Update
    suspend fun update (item: Item)

    @Delete
    suspend fun delete (item: Item)

    @Query ("SELECT * FROM items WHERE id = :id")
    fun getItem (id: Int): Flow<Item>

    /*
    Kieu tra ve dang Flow
    + bất cứ khi nào dữ liệu trong bảng items thay đổi (cụ thể là với id tương ứng),
    + bạn sẽ nhận được bản cập nhật mới nhất mà không cần phải thực hiện truy vấn lại thủ công.

    + Chay khong chặn luồng chính

     */

    @Query ("SELECT * FROM items ORDER by name ASC")
    fun getAllItems(): Flow<List<Item>>

    @Query("SELECT * FROM items WHERE name LIKE '%' || :name || '%' ORDER BY name ASC")
    fun searchItemsByName(name: String): Flow<List<Item>>

}

@Dao
interface BooksDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(book: Book)

    @Update
    suspend fun update(book: Book)

    @Delete
    suspend fun delete(book: Book)

    @Query("SELECT * FROM books Where bookId = :bookId")
    fun getBookById(bookId: Int): Flow<Book>

    @Query ("SELECT * FROM books ORDER By name ASC") //asc: tang dan (A->Z) (0->9) desc: giam dan
    fun getAllBooks(): Flow<List<Book>>

    @Query ("SELECT * FROM books WHERE name LIKE '%' || :name || '%' ORDER BY name ASC")
    fun searchBooksByName(name: String): Flow<List<Book>>

    @Query ("SELECT * FROM books WHERE subject = :subject ORDER BY name ASC")
    fun searchBooksBySubject(subject: String): Flow<List<Book>>

    @Query("UPDATE books SET saveToLibrary = :saveToLibrary WHERE bookId = :bookId")
    suspend fun updateSaveToLibrary(bookId: Int, saveToLibrary: Boolean)

    @Query("SELECT saveToLibrary FROM books WHERE bookId = :bookId")
    fun getBookSavedState(bookId: Int): Flow<Boolean>
    //Để ui phản hồi theo sự thay đổi dữ liệu nên dùng flow

}

@Dao
interface AuthorDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(author: Author)

    @Update
    suspend fun update(author: Author)

    @Delete
    suspend fun delete(author: Author)

    @Query("Select * FROM authors WHERE Id = :authorId ORDER BY name ASC")
    fun searchAuthorById (authorId: Int): Flow<Author>

    @Query("SELECT * FROM authors WHERE name LIKE '%' || :name || '%' ORDER BY name ASC")
    fun searchAuthorByName(name: String): Flow<List<Author>>

    @Query ("SELECT * FROM authors ORDER BY name ASC")
    fun getAllAuthors(): Flow<List<Author>>
}
