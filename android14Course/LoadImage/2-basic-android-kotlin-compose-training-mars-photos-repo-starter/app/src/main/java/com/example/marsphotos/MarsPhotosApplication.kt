package com.example.marsphotos

import android.app.Application
import com.example.marsphotos.data.AppContainer
import com.example.marsphotos.data.DefaultAppContainer

//Lop nay ket thua tu application

class MarsPhotosApplication: Application () {
    lateinit var container: AppContainer //lateinit dung de khoi tao sau
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}