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

package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


/*
    Lớp database dùng để xác định các thực thể, DAOs và phiên bản của cơ sở dữ liệu.

    Lớp Database trong Room là lớp trừu tượng mà bạn tạo ra để đại diện cho cơ sở dữ liệu của bạn.
    Lớp này kết nối các Entity và DAO với cơ sở dữ liệu thực tế.
        Nó quản lý vòng đời của cơ sở dữ liệu và cung cấp phương thức để truy cập vào các DAO.

    Kết nối các DAO: Nó cung cấp phương thức để lấy các DAO mà bạn đã định nghĩa,
        giúp bạn truy cập và thao tác với dữ liệu thông qua các DAO.
    Singleton Pattern: Lớp Database thường được cài đặt theo mô hình singleton để đảm bảo rằng
        chỉ có một instance của cơ sở dữ liệu được tạo ra trong suốt vòng đời của ứng dụng.



 */



@Database(entities = [Item::class, Book::class, Author::class], version = 2, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao //Phuong thu truu tuong, chi dinh nghia ma khong co cai dat, cai dat tuy thuoc vao lop con chau
    abstract fun bookDao(): BooksDao
    abstract fun authorDao(): AuthorDao

    companion object {
        @Volatile
        private var Instance: InventoryDatabase? = null
            /*
            Các tính năng này giúp đảm bảo giá trị của Instance luôn cập nhật
                và giống nhau đối với tất cả các luồng thực thi.

            Điều đó có nghĩa là những thay đổi do một luồng thực hiện đối với Instance
                sẽ hiển thị ngay lập tức đối với tất cả các luồng khác.

            */

        fun getDatabase(context: Context): InventoryDatabase {
            // if Instance không rỗng, trả nó.
            // Ngược lại, tạo nó trong synchronized, đảm bảo chỉ có một luồng chi cập tại một thời điểm

            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, InventoryDatabase::class.java, "item_database")
                    //.addMigrations(MIGRATION_1_2) //Them bang moi vao bang da co\
                    //.fallbackToDestructiveMigration() //Xoa bang cu va tao bang moi khi co them du lieu
                    .build()
                    .also { Instance = it }

            }
        }
    }
}


/*
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Tạo bảng mới `authors`
        database.execSQL(
            "CREATE TABLE `authors` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL)")

        // Tạo bảng mới `books`
        database.execSQL("""
            CREATE TABLE `books` (
                `bookId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 
                `name` TEXT NOT NULL, 
                `type` TEXT NOT NULL, 
                `authorId` INTEGER, 
                `publicationInfo` TEXT NOT NULL, 
                `shelfNumber` TEXT NOT NULL, 
                `subject` TEXT NOT NULL, 
                `physicalDescription` TEXT NOT NULL, 
                `saveToLibrary` INTEGER NOT NULL,
                FOREIGN KEY(`authorId`) REFERENCES `authors`(`id`) ON UPDATE CASCADE ON DELETE SET NULL
            )
        """.trimIndent())
    }
}
*/

