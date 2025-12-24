package com.example.api

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.api.MyAdapter

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(){

    lateinit var recyclerView: RecyclerView
    lateinit var myAdapter:MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView=findViewById(R.id.recylerView)
        val Retrofit_build= Retrofit.Builder().baseUrl("https://dummyjson.com/").addConverterFactory(
            GsonConverterFactory.create()).build().create(ApiInterface::class.java)
        val Retrofit_Type=Retrofit_build.getProductData()
        Retrofit_Type.enqueue(object : Callback<MyData?>{
            override fun onResponse(p0: Call<MyData?>, p1: Response<MyData?>) {
             val Reterofir=p1.body()
                val Retrofit_type1=Reterofir?.products!!
                myAdapter= MyAdapter(this@MainActivity,Retrofit_type1)
                recyclerView.adapter=myAdapter
                recyclerView.layoutManager= LinearLayoutManager(this@MainActivity)
                myAdapter.setItemClickListener(object : MyAdapter.onItemClickListener{
                    override fun onItemClick(position: Int)
                    { val clickedProduct = Retrofit_type1[position]
                        val intent = Intent(this@MainActivity, NewsDetail::class.java)
                        intent.putExtra("productTitle", clickedProduct.title)
                        intent.putExtra("productImage", clickedProduct.thumbnail)
                        intent.putExtra("description",clickedProduct.description)
                        intent.putExtra("productPrice",clickedProduct.price)
                        intent.putExtra("shing_informatiuon",clickedProduct.shippingInformation)
                        intent.putExtra("shing_informatiuon1",clickedProduct.category)
                        intent.putExtra("warrantyInformation",clickedProduct.warrantyInformation)
                        startActivity(intent)

                    }
                })
            }


            override fun onFailure(p0: Call<MyData?>, p1: Throwable) {
                TODO("Not yet implemented")
                Log.d("MAin Activity","onFailure:"+ p1.message)
            }
        })

    }
}
