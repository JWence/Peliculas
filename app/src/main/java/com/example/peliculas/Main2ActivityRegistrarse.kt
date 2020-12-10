package com.example.peliculas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.login_activity_main.*

class Main2ActivityRegistrarse : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2_registrarse)
        supportActionBar?.hide()

        button_r.setOnClickListener{
            println("ndocenojc")

            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
