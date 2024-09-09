package com.example.marsphotos.data

import com.example.marsphotos.network.MarsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit



//một interface định nghĩa cách truy cập vào marsPhotoRepository.
//Cung cap cac doi tuong can thiet cho he thong
interface AppContainer {
    val marsPhotoRepository : MarsPhotoRepository
}


//triển khai AppContainer, cấu hình Retrofit, và cung cấp một đối tượng

// Cung cấp cấu hình cho toàn bộ hệ thống
// + bao gồm cả việc tạo đối tượng NetworkMarsPhotoRepository và cấu hình Retrofit.
class DefaultAppContainer : AppContainer {

    //Dia chi co so cua API, nguon du lieu
    private val BASE_URL =
        "https://android-kotlin-fun-mars-server.appspot.com"

    //Đối tượng Retrofit được cấu hình với địa chỉ cơ sở và bộ chuyển đổi JSON (Gson trong trường hợp này).
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    //Đối tượng của ApiService được tạo ra bằng Retrofit
    private val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }

    // Cung cấp một đối tượng NetworkMarsPhotoRepository với retrofitService là tham số.
    override val marsPhotoRepository: MarsPhotoRepository by lazy {
        NetworkMarsPhotoRepository(retrofitService)
    }

}