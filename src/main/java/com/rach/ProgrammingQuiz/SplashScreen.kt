package com.rach.ProgrammingQuiz

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.rach.ProgrammingQuiz.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        window.statusBarColor =  Color.parseColor("#00baff")

        Handler().postDelayed({

            val intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
            finish()

        },3000)

    }
}