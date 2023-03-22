package com.cvfresher.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView

class DashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash)

        val lytExp = findViewById<ViewGroup>(R.id.lytExp)
        val imgBack = findViewById<ImageView>(R.id.imgBack)

        imgBack.setOnClickListener{
            finish()
        }

        lytExp.setOnClickListener{
            val intent = Intent(this@DashActivity, ExperienceActivity::class.java)
            startActivity(intent)
        }
    }
}