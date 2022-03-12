package com.example.techastral_loc

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.techastral_loc.databinding.ActivityFundBinding
import com.google.android.material.navigation.NavigationView

class fund : AppCompatActivity() {
    lateinit var binding: ActivityFundBinding
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawer: DrawerLayout
    private lateinit var toolbar: Toolbar
    lateinit var nav: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFundBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fund)


        var pref = applicationContext.getSharedPreferences("MyPref", MODE_PRIVATE)
        var editor: SharedPreferences.Editor = pref.edit()

        nav = binding.funNav

        val header = nav.getHeaderView(0)

        //variables for assigning image,name and emailId
        val image = header.findViewById<ImageView>(R.id.headerImg)
        val name = header.findViewById<TextView>(R.id.headerName)
        val username = header.findViewById<TextView>(R.id.headerUserName)


        name.text = pref.getString("name", null).toString()
        username.text = pref.getString("username", null).toString()
        name.setTextColor(Color.parseColor("#ffffff"))
        username.setTextColor(Color.parseColor("#ffffff"))

        toolbar = binding.fundToolbar
        setSupportActionBar(toolbar)
        drawer = binding.fundDrawer
        toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.isDrawerIndicatorEnabled = true
        toggle.drawerArrowDrawable.color = resources.getColor(R.color.white)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val rv = binding.rvFundRaiser
        rv.apply {
            layoutManager = LinearLayoutManager(this@fund)
        }

        nav.setNavigationItemSelectedListener {
            drawer.closeDrawer(GravityCompat.START)
            when (it.itemId) {
                R.id.volunter -> {
                    val intent = Intent(this, Voluntering::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.funding -> {
                    drawer.closeDrawer(GravityCompat.START)
                }
                R.id.logout -> {
                    editor.clear()
                    editor.commit()

                    val intent = Intent(this, Login::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.profile -> {
                    val intent = Intent(this, Profile::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            true
        }

    }
}