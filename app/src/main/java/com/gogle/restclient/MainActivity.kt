package com.gogle.restclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//      Creating a communication using the retrofit
        var rf = Retrofit.Builder()
            .baseUrl(RetrofitInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
//        definig the api
//        initialization of the interface
        var API = rf.create(RetrofitInterface::class.java)
        var call = API.posts

//        enque the functions
        call?.enqueue(object:Callback<List<PostModel?>?>{
            override fun onResponse(
                call: Call<List<PostModel?>?>,
                response: Response<List<PostModel?>?>
            ) {
//                binding data to listview
                var postlist : List<PostModel>? = response.body() as List<PostModel>
//                define the post
                var post = arrayOfNulls<String>(postlist!!.size)
                for (i in postlist!!.indices){
                    post[i] = postlist!![i]!!.title
//                    create a varibale to bind this data into a list view
                    var adapter = ArrayAdapter<String>(applicationContext, android.R.layout.simple_list_item_1,post)
//                    Log.d("TAG", "message")
                    val listView: ListView = findViewById(R.id.listview)
                    listView.adapter = adapter
                }
            }

            override fun onFailure(call: Call<List<PostModel?>?>, t: Throwable) {
                TODO("Not yet implemented")
            }
//            impliment method of callback for on failure and on responsce

        })

    }
}