package ru.zolax.lab10

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okhttp3.OkHttpClient
import okhttp3.Request
import org.junit.Test

import org.junit.Assert.*
import ru.zolax.lab10.network.Dog

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testRequest() {
        val client = OkHttpClient()
        val url = "https://dog.ceo/api/breeds/image/random"
        val request = Request.Builder().get().url(url).build()
        client.newCall(request).execute().use {
            var result = it.body?.string() ?: "invalid String"
            val parseResult = result.replace("\\","/")
            val moshi: Moshi = Moshi.Builder().build()
            val dogAdapter: JsonAdapter<Dog> = moshi.adapter(Dog::class.java)
            val dog: Dog? = dogAdapter.fromJson(parseResult)
            println(dog)
            }

    }
}