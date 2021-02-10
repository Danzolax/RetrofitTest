package ru.zolax.lab10.network

import retrofit2.Call
import retrofit2.http.GET


interface DogRetrofitInteface {
    @GET("api/breeds/image/random")
    fun getDog() : Call<Dog>
}