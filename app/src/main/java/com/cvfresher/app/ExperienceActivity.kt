package com.cvfresher.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ExperienceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_experience)

        val btnAddExp = findViewById<Button>(R.id.btnAddExp)

        btnAddExp.setOnClickListener{
            val intent = Intent(this@ExperienceActivity, AddExperienceActivity::class.java)
            startActivity(intent)
        }

    }
}