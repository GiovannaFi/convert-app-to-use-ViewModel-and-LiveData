package com.example.viewmodel1

import android.app.Application
import android.util.Log

class MyApplication : Application() {

    private val apiProvider = ApiProvider()
    val mainViewModelFactory = MainViewModelFactory(apiProvider.provider())


    override fun onCreate() {
        super.onCreate()
        Log.d("My application", "starting ok!")
    }
}