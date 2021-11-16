package com.example.estampamos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.Theme_Estampamos)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}