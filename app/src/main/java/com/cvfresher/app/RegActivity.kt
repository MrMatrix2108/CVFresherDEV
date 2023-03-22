package com.cvfresher.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)

        val btnReg = findViewById<Button>(R.id.btnReg)
        val btnBack = findViewById<Button>(R.id.btnBackToLogin)

        btnReg.setOnClickListener{
            val intent = Intent(this@RegActivity, LoginActivity::class.java)
            Toast.makeText(this, "Registration Successful!", Toast.LENGTH_LONG).show()
            startActivity(intent)
        }

        btnBack.setOnClickListener{
            val intent = Intent(this@RegActivity, LoginActivity::class.java)
            startActivity(intent)
        }
}
}
