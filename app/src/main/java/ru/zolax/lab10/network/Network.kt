package ru.zolax.lab10.network


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object Network {
    private val baseUrl = "https://dog.ceo/"
    private val logInterceptor = HttpLoggingInterceptor().apply(){
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(logInterceptor)
        .addNetworkInterceptor(logInterceptor)
        .connectTimeout(10,TimeUnit.SECONDS)
        .writeTimeout(10,TimeUnit.SECONDS)
        .readTimeout(10,TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val service: DogRetrofitInteface = retrofit.create(DogRetrofitInteface::class.java)


}