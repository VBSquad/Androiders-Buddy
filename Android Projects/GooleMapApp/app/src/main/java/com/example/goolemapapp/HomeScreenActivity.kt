package com.example.goolemapapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomeScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        val button = findViewById<Button>(R.id.button)
        button?.setOnClickListener(){
            val intent = Intent(this,MapsActivity::class.java)
            startActivity(intent);
        }
    }
}