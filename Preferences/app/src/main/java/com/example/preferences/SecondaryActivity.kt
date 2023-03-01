package com.example.preferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary)

        var textView = findViewById<TextView>(R.id.signedText)

        val sh = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val s1 = sh.getString("username", "")
//        val s2 = sh.getString("age", 0)

        // Setting the fetched data in the EditTexts
        textView.text = "hello " + s1
//        age.setText(a.toString())
    }
}