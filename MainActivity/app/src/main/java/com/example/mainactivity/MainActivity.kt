package com.example.mainactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var textView = findViewById<TextView>(R.id.tvHello)
        var editTextView = findViewById<EditText>(R.id.tvEdit)

        var btnView = findViewById<Button>(R.id.btnSubmit)
        var offerBtnView = findViewById<Button>(R.id.btnOffers)
        var name =""

        btnView.setOnClickListener(){
            name = editTextView.text.toString()
            if(name == ""){
                offerBtnView.visibility=INVISIBLE
                Toast.makeText(this@MainActivity, "please name", Toast.LENGTH_SHORT).show()
            }else{
                var message = "hello from $name"
                textView.text=message
                editTextView.text.clear()
                offerBtnView.visibility=VISIBLE
            }
        }
        offerBtnView.setOnClickListener(){
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("USER", name)
            startActivity(intent)
        }
    }
}