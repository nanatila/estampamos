package com.example.applogin.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.applogin.R
import com.example.applogin.view.ui.fragments.AdminFragment
import com.example.applogin.view.ui.fragments.ComentsFragment
import com.example.applogin.view.ui.fragments.HomeFragment
import com.example.applogin.view.ui.fragments.OrderFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val orderFragment = OrderFragment()
    private val commentsFragment = ComentsFragment()
    private val adminFragment = AdminFragment()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        replaceFragment(homeFragment)
        var bottonNavigation = findViewById<BottomNavigationView>(R.id.bnvMenu)
        bottonNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navHomeFragment -> replaceFragment(homeFragment)
                R.id.navOrderFragment -> replaceFragment(orderFragment)
                R.id.navCommentsFragment -> replaceFragment(commentsFragment)
                R.id.navAdminFragment -> replaceFragment(adminFragment)

            }
            true
        }
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(applicationContext, "Usuario Registrado Bienvenido", Toast.LENGTH_LONG).show()
    }

    private fun replaceFragment(fragment: Fragment){
        if(fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()

        }
    }
}
