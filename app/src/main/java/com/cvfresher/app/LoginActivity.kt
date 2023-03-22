package com.cvfresher.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        val tvToReg = findViewById<TextView>(R.id.tvToReg)
        val btnLogIn = findViewById<Button>(R.id.btnLogIn)
        val imgBack = findViewById<ImageView>(R.id.imgBack)

        imgBack.setOnClickListener{
            finish()
        }

        tvToReg.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegActivity::class.java)
            startActivity(intent)
        }

        btnLogIn.setOnClickListener {
            val intent = Intent(this@LoginActivity, DashActivity::class.java)
            Toast.makeText(this, "Logging in...", Toast.LENGTH_SHORT).show()
            startActivity(intent)


        }
    }
}
