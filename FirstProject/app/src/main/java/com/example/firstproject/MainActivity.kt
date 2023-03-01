package com.example.firstproject

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var productList = arrayListOf<Product>()
        productList.add(Product("Product A"))
        productList.add(Product("Product B"))
        productList.add(Product("Product C"))
        productList.add(Product("Product D"))
        productList.add(Product("Product E"))
        productList.add(Product("Product F"))

        var productListView = findViewById<RecyclerView>(R.id.recyclerView)
        productListView.layoutManager = LinearLayoutManager(this)
        productListView.adapter = ProductAdapter(this, productList)
    }
}

data class Product(var productName : String)

class ProductAdapter(var context: Context, var productList: ArrayList<Product>) : RecyclerView.Adapter<ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_todo, parent,false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        var product = productList.get(position)
        holder.productNameTextView.text = product.productName
    }

}

class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var productNameTextView = itemView.findViewById<TextView>(R.id.productName)
}