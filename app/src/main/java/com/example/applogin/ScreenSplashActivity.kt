package com.example.applogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

class ScreenSplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.Theme_Applogin)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

    }
}