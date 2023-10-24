package com.example.proj6_cyoapi_recycleredition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var coffeeList: MutableList<String>
    private lateinit var rvCoffee: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rvCoffee = findViewById(R.id.coffee_list)
        coffeeList = mutableListOf()

        getCoffeeImageURL()
    }

    private fun getCoffeeImageURL() {
        val client = AsyncHttpClient()

        client["https://api.sampleapis.com/coffee/hot/", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.d("Coffee", "response successful")
                val coffeeImageArray = json.jsonArray.getJSONObject()
                for (i in 0 until coffeeImageArray.length()) {
                    coffeeList.add(coffeeImageArray.getString(i))
                }
                val adapter = CoffeeAdapter(coffeeList)
                rvCoffee.adapter = adapter
                rvCoffee.layoutManager = LinearLayoutManager(this@MainActivity)
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Coffee Error", errorResponse)
            }
        }]
    }
}