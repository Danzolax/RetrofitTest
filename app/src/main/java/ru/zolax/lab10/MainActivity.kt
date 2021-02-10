package ru.zolax.lab10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.zolax.lab10.extensions.toast
import ru.zolax.lab10.network.Dog
import ru.zolax.lab10.network.DogsAdapter
import ru.zolax.lab10.network.Network

class MainActivity : AppCompatActivity() {
    private val repeateCount = 20
    private val dogsApiService = Network.service
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var dogsRV: RecyclerView
    private lateinit var adapter: DogsAdapter

    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        swipeRefresh = findViewById(R.id.swiped_refresh)
        dogsRV = findViewById(R.id.dogs_rv)
        progressBar = findViewById(R.id.loadingBar)

        adapter = DogsAdapter()
        dogsRV.adapter = adapter
    }

    override fun onResume() {
        progressBar.visibility = View.VISIBLE
        super.onResume()
        repeat(repeateCount) {
            val dogCall: Call<Dog> = dogsApiService.getDog()
            dogCall.enqueue(object : Callback<Dog> {
                override fun onResponse(call: Call<Dog>, response: Response<Dog>) {
                    val dog: Dog? = response.body()
                    if (dog != null){
                        adapter.addDog(dog)
                    } else{
                        Log.d("M_MainActivity","Null Dogs")
                    }
                    progressBar.visibility = View.GONE
                }

                override fun onFailure(call: Call<Dog>, t: Throwable) {
                    progressBar.visibility = View.VISIBLE
                    toast("Failed to Upload Dogs")
                }
            })

        }


        swipeRefresh.setOnRefreshListener {
            adapter.clearDogs()
            repeat(repeateCount) {
                val dogCall: Call<Dog> = dogsApiService.getDog()
                dogCall.enqueue(object : Callback<Dog> {
                    override fun onResponse(call: Call<Dog>, response: Response<Dog>) {

                        val dog: Dog? = response.body()
                        if (dog != null)
                            adapter.addDog(dog)
                        progressBar.visibility = View.GONE
                    }
                    override fun onFailure(call: Call<Dog>, t: Throwable) {
                        progressBar.visibility = View.VISIBLE
                        toast("Failed to Upload Dogs")
                    }
                })
            }
            swipeRefresh.isRefreshing = false
        }
    }
}