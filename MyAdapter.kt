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
import android.widget.Filter
import android.widget.Filterable

class MyAdapter(
    val context: Activity,
    private var productList: List<Product>
) : RecyclerView.Adapter<MyAdapter.MyViewHolder>(), Filterable {

    private var filteredList: List<Product> = productList
    private lateinit var myListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setItemClickListener(listener: onItemClickListener) {
        myListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.each_row, parent, false)
        return MyViewHolder(itemView, myListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = filteredList[position]

        holder.title.text = currentItem.title
//        holder.description = currentItem.description
        Picasso.get().load(currentItem.thumbnail).into(holder.images)

        holder.ratingBar.rating = currentItem.rating.toFloat()
        if (holder.ratingBar.rating >= 1f) {
            holder.rating_type.text = "Best seller"
            holder.rating_type.setTextColor(context.getColor(R.color.Green))
        } else {
            holder.rating_type.text = "Not trusted"
            holder.rating_type.setTextColor(context.getColor(R.color.Red))
        }
    }

    override fun getItemCount(): Int = filteredList.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint?.toString()?.lowercase() ?: ""
                val results = if (query.isEmpty()) {
                    productList
                } else {
                    productList.filter {
                        it.title.lowercase().contains(query) ||
                                it.description.lowercase().contains(query)
                    }
                }
                return FilterResults().apply { values = results }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as List<Product>
                notifyDataSetChanged()
            }
        }
    }

    class MyViewHolder(itemView: View, myListener: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.product_title)
//        val description: TextView = itemView.findViewById(R.id.warrantyInformation1)
        val images: ShapeableImageView = itemView.findViewById(R.id.product_Img)
        val ratingBar: RatingBar = itemView.findViewById(R.id.ratingBar1)
        val rating_type: TextView = itemView.findViewById(R.id.Rating_type)

        init {
            itemView.setOnClickListener {
                myListener.onItemClick(adapterPosition)
            }
        }
    }
}
