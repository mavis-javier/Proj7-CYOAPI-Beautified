package com.example.proj6_cyoapi_recycleredition

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CoffeeAdapter(private val coffeeList: List<String>): RecyclerView.Adapter<CoffeeAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val petImage: ImageView

        init {
            // Find our RecyclerView item's ImageView for future use
            petImage = view.findViewById(R.id.coffee_image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeAdapter.ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.coffee_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoffeeAdapter.ViewHolder, position: Int) {
        // "Get element from your dataset at this position and replace the contents of the view with that element"
        Glide.with(holder.itemView)
            .load(coffeeList[position])
            .centerCrop()
            .into(holder.petImage)
    }

    override fun getItemCount() = coffeeList.size
}