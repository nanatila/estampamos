package com.example.applogin.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.applogin.R
import com.example.applogin.view.ui.fragments.AdminFragment
import com.example.applogin.view.ui.fragments.CommentsFragment
import com.example.applogin.view.ui.fragments.HomeFragment
import com.example.applogin.view.ui.fragments.OrderFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.journeyapps.barcodescanner.ScanOptions

import com.journeyapps.barcodescanner.ScanContract

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import com.example.applogin.fragment_configuracion
import com.example.applogin.fragment_contactanos
import com.example.applogin.model.Producto
import com.example.applogin.view.adapter.pedidosadapter
import com.example.applogin.viewmodel.ProductosListViewModel
import com.journeyapps.barcodescanner.ScanIntentResult


class HomeActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val orderFragment = OrderFragment()
    private val commentsFragment = CommentsFragment()
    private val adminFragment = AdminFragment()
    private val fragmentConfig= fragment_configuracion()
    private val fragmentComment = CommentsFragment()
    private val fragmentContact = fragment_contactanos()
    private  val viewModel: ProductosListViewModel by viewModels()

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
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

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
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.scan ->{
                Toast.makeText(this, "Scanner", Toast.LENGTH_LONG).show()
                barcodeLauncher.launch(ScanOptions())
            }
            R.id.configuracion -> {
                Toast.makeText(this, "configuraciones", Toast.LENGTH_LONG).show()
                replaceFragment(fragmentConfig)
            }
            R.id.comentarios -> {
                Toast.makeText(this, "comentarios", Toast.LENGTH_LONG).show()
                replaceFragment(fragmentComment)
            }
            R.id.contactenos -> {
                Toast.makeText(this, "comentarios", Toast.LENGTH_LONG).show()
                replaceFragment(fragmentContact)
            }
        }
        return true
    }

    // Register the launcher and result handler
    private val barcodeLauncher = registerForActivityResult(
        ScanContract()
    ) { result: ScanIntentResult ->
        if (result.contents == null) {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(
                this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
            var producto:Producto

            //viewModel.getProductoByBarCode(result.contents)

        }
    }
}
