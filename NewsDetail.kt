package com.example.api;

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class NewsDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        supportActionBar?.hide()

        val heading = intent.getStringExtra("productTitle")
        val imageUrl = intent.getStringExtra("productImage")
        val description_last=intent.getStringExtra("description")
        val price_product = intent.getDoubleExtra("productPrice", 0.0)
        val shiping_information=intent.getStringExtra("shing_informatiuon")
        val shing_informatiuon1=intent.getStringExtra("shing_informatiuon1")
val wrannty_info=intent.getStringExtra("warrantyInformation")


        val headingTv = findViewById<TextView>(R.id.deatil_Information)
        val headingIv = findViewById<ImageView>(R.id.ImageIntent)
val description=findViewById<TextView>(R.id.last_one)
        val shing_informatiuon01=findViewById<TextView>(R.id.shing_informatiuon1)
        val price_details=findViewById<TextView>(R.id.price)
        val shing_informatiuon=findViewById<TextView>(R.id.shing_informatiuon)
        val final_end=findViewById<TextView>(R.id.warrantyInformation1)

        headingTv.text = heading
        description.text=description_last
        price_details.text = "₹ $price_product"
        shing_informatiuon.text=shiping_information
        shing_informatiuon01.text=shing_informatiuon1
        final_end.text=wrannty_info



        val button=findViewById<Button>(R.id.button)
        button.setOnClickListener {
            intent= Intent(this, order_complete::class.java)
            startActivity(intent)
        }





        // Use Picasso to load the image from URL

        Picasso.get().load(imageUrl).into(headingIv)

    }
}
