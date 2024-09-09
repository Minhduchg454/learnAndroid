package com.example.marsphotos.data

import com.example.marsphotos.model.MarsPhoto
import com.example.marsphotos.network.MarsApiService


/*
Hàm trong interface chỉ là một khai báo (không có thân hàm),
+ và yêu cầu bất kỳ lớp nào triển khai interface đó phải cung cấp cách triển khai cụ thể cho các phương thức này.
Interface giúp định nghĩa một hợp đồng mà các lớp phải tuân thủ
+ nhưng không quy định chi tiết cách thực hiện các phương thức đó.
 */


// một interface để định nghĩa cách lấy dữ liệu ảnh sao Hỏa. mà không quan tâm cách thực hiện cụ thể
// Xd phuong thuc can thiet de lay du lieu
interface MarsPhotoRepository {
    suspend fun getMarsPhoto() : List <MarsPhoto>
}

//triển khai MarsPhotoRepository và sử dụng Retrofit để gọi API.
//Cung cap cach lay du lieu tu api, nhung khong can biet chi tiet ve cach thuc goi API

class NetworkMarsPhotoRepository (
    private val marsApiService: MarsApiService //Doi tuong cua API server
) : MarsPhotoRepository{
    override suspend fun getMarsPhoto(): List<MarsPhoto> = marsApiService.getPhotos()
    //Goi phuong thuc getPhotos của marsApiService để lấy dữ liệu từ sach
}