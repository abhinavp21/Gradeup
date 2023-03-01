package com.example.singlepage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var listOfImages= arrayListOf<images>()
        listOfImages.add(images(R.drawable.shirts))
        listOfImages.add(images(R.drawable.shirts))

        var images_view=findViewById<RecyclerView>(R.id.image_list)
        images_view.layoutManager = LinearLayoutManager(this)
        images_view.adapter = Restaurantadapter(this,listOfImages)
    }
}
class ImagesAdapter(var context: Context, var lisOfImages: ArrayList<images>):RecyclerView.Adapter<ImagesAdapter.images>{
    class restaurantviewholder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var restaurantImageView = itemview.findViewById<ImageView>(R.id.image1)
    }
}
data class images(val img: Int)
