package com.example.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        var activity_two_textView = findViewById<TextView>(R.id.displayFrom1)
        var name = intent.getStringExtra("USER")
        activity_two_textView.text= name
    }
}