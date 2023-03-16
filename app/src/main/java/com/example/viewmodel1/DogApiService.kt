package com.example.viewmodel1

import retrofit2.Response
import retrofit2.http.GET

interface DogApiService {
    @GET("breeds/image/random")
    suspend fun getRandomDogImage(): Response<Data>

    //posso mettere più get e più suspend fun
}