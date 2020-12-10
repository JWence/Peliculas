package com.example.peliculas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.login_activity_main.*

class Login_MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity_main)

        supportActionBar?.hide()


        button_r.setOnClickListener{

            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        textView.setOnClickListener {
            var intent = Intent(this, Main2ActivityRegistrarse::class.java)

            startActivity(intent)
        }

    }

}
