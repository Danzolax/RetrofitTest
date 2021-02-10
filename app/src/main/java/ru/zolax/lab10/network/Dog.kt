package ru.zolax.lab10.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Dog(
    @Json(name = "message")
    val imageUrl: String,
    @Json(name = "status")
    val status: String,

)