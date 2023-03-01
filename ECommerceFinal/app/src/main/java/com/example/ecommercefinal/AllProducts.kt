package com.example.ecommercefinal

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AllProducts : AppCompatActivity() {
    private lateinit var productsGV: GridView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_products)

        val productsArrayList = arrayListOf<products>()

        productsArrayList.add(products("shirt","apparel", 20,R.drawable.shirts))
        productsArrayList.add(products("shirt","apparel", 40,R.drawable.shirts1))
        productsArrayList.add(products("shirt","apparel", 30,R.drawable.shirts2))
        productsArrayList.add(products("shirt","apparel", 30,R.drawable.shirts3))
        productsArrayList.add(products("shirt","apparel", 30,R.drawable.shirts4))
        productsArrayList.add(products("shirt","apparel", 30,R.drawable.shirts5))

        var recyclerView = findViewById<RecyclerView>(R.id.recycler)
        recyclerView.adapter = ProductsAdapter()
        recyclerView.layoutManager = GridLayoutManager(this,2)
//            .findViewById<CardView>(R.id.card)

//        productsView.layoutManager = LinearLayoutManager(this)
//        productsView.adapter = ProductsAdapter(this,productsArrayList)
    }
}

class ProductsAdapter() : RecyclerView.Adapter<ProductsAdapter.ViewHolder>(){

    class restaurantviewholder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var restauranttextview = itemview.findViewById<TextView>(R.id.text1)
        var restaurantImageView = itemview.findViewById<ImageView>(R.id.image1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewtype: Int):  restaurantviewholder{
        val view =LayoutInflater.from(context).inflate(R.layout.activity_main3,parent,false)
        return restaurantviewholder(view)
    }

    override fun onBindViewHolder(holder: restaurantviewholder, position: Int) {
        var restaurantmodel = restaurantlist.get(position)
        holder.restauranttextview.text = restaurantmodel.name
        holder.restaurantImageView.setImageResource(restaurantmodel.img)
//        val eachCard = holder.itemView
//        eachCard.setOnClickListener {
//            val intent = Intent(context, ParticularRestaurant::class.java)
//            intent.putExtra("name", restaurantmodel.name)
//            intent.putExtra("img", restaurantmodel.img)
//            context.startActivity(intent)
//        }
    }
    override fun getItemCount(): Int {
        return restaurantlist.size
    }

//    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
//        var productName : TextView
//        init {
//            productName = view.findViewById(R.id.productName)
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        TODO("Not yet implemented")
//    }
//
//    override fun getItemCount(): Int {
//        TODO("Not yet implemented")
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        TODO("Not yet implemented")
//    }
}

data class products(var name:String, var category: String, var price: Int, var imgId:Int)