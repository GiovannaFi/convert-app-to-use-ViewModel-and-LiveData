package com.example.viewmodel1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory(private val dogApiService: DogApiService) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dogApiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}