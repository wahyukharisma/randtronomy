package com.example.randtronomy.presentation.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.randtronomy.R
import com.example.randtronomy.presentation.ui.main.MainActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val r = Runnable {
            startActivity(Intent(this, MainActivity::class.java))
        }

        Handler(Looper.getMainLooper()).postDelayed(r,2000)
    }
}