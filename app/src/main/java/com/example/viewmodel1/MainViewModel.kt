package com.example.viewmodel1

import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel(private val dogApiService: DogApiService) : ViewModel() {

    private var _dogImage = MutableLiveData<Response<Data>>()
    val dogImage: LiveData<Response<Data>>
        get() = _dogImage


    fun getDogImageNetworkCall(dog: ImageView) {
        _dogImage.postValue(Response.Loading) //postvalue è =
        viewModelScope.launch {
            try {
                val response = dogApiService.getRandomDogImage()
                if (response.isSuccessful) {
                    val dogImage = response.body()
                    _dogImage.postValue(Response.Success(response.code(), dogImage))
                    Log.e("MainViewModel", "ok!: ${response.code()}")

                } else {
                    _dogImage.postValue(Response.Error(response.code(), response.message()))
                    Log.e(
                        "MainViewModel",
                        "com.example.viewmodel1.ui.main.Response not successful: ${response.code()}"
                    )
                }
            } catch (e: Exception) {
                _dogImage.postValue(Response.Error(500, "ci sono problemi"))
                Log.e("MainViewModel", "Error: ${e.message}")
            }
        }
    }
}