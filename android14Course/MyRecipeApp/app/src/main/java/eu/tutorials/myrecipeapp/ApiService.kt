package eu.tutorials.myrecipeapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val retrofit= Retrofit.Builder().baseUrl("www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    val recipeService= retrofit.create(ApiService::class.java)
interface ApiService {
    //Ham tam dung, xu ly o che do nen, giu cho giao dien phan hoi nhanh cho khi du lieu duoc tai xuong
    @GET("categories.php")
    suspend fun getCategories():CategoriesResponse
}