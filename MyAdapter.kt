package com.example.api

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class MyAdapter(
    val context: Activity,
    val productArrayList: List<Product>
) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.each_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = productArrayList[position]

        // Bind text
        holder.title.text = currentItem.title

        // Bind image
        Picasso.get().load(currentItem.thumbnail).into(holder.images)

        // Bind rating
        holder.ratingBar.rating = currentItem.rating.toFloat()
        if (holder.ratingBar.rating>=4f){
          holder.rating_type.text="Best seller"

            holder.rating_type.setTextColor(context.getColor(R.color.Green))

        }
        else{
            holder.rating_type.text="not trused"
            holder.rating_type.setTextColor(context.getColor(R.color.Red))
        }
    }

    override fun getItemCount(): Int = productArrayList.size

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.product_title)
        val images: ShapeableImageView = itemView.findViewById(R.id.product_Img)
        val ratingBar: RatingBar = itemView.findViewById(R.id.ratingBar1)
        val rating_type: TextView=itemView.findViewById(R.id.Rating_type)
        init {

        }
    }
}
