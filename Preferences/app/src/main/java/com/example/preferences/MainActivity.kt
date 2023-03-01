package com.example.preferences

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sh = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val s1 = sh.getString("username", "")
        val i = Intent(applicationContext, SecondaryActivity::class.java)
        if(s1!="")
            startActivity(i)

        var nameView = findViewById<EditText>(R.id.name)
        var passView = findViewById<EditText>(R.id.password)
        var btnView=findViewById<Button>(R.id.signinBtn)

        btnView.setOnClickListener(){
            var name = nameView.text.toString()
            var password = passView.text.toString()

            if(name !="" && password!=""){
                val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
                val myEdit = sharedPreferences.edit()

                // write all the data entered by the user in SharedPreference and apply
                myEdit.putString("username", name)
                myEdit.putString("passcode", password)
                myEdit.apply()

                startActivity(i)
            }else{
                Toast.makeText(applicationContext,"enter both fields",Toast.LENGTH_SHORT).show();
            }
        }

    }
}