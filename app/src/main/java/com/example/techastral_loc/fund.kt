package com.example.techastral_loc

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.techastral_loc.databinding.ActivityFundBinding
import com.example.techastral_loc.databinding.ActivityVolunteringBinding
import com.google.android.material.navigation.NavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class fund : AppCompatActivity() {
    lateinit var binding: ActivityFundBinding
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawer: DrawerLayout
    private lateinit var toolbar: Toolbar
    lateinit var nav: NavigationView
    lateinit var data:ArrayList<dataItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFundBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var pref = applicationContext.getSharedPreferences("MyPref", MODE_PRIVATE)
        var editor: SharedPreferences.Editor = pref.edit()

        val rv = binding.rvFundRaiser
        rv.apply {
            layoutManager = LinearLayoutManager(this@fund)
        }

        nav = binding.funNav

        val header = nav.getHeaderView(0)

        //variables for assigning image,name and emailId
        val image = header.findViewById<ImageView>(R.id.headerImg)
        val name = header.findViewById<TextView>(R.id.headerName)
        val username = header.findViewById<TextView>(R.id.headerUserName)


        name.text = pref.getString("email",null).toString()
        username.text = pref.getString("username",null).toString()
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


        val rf = Retrofit.Builder()
            .baseUrl("http://shrutiprasad.pythonanywhere.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitApi::class.java)

        val request = rf.funding()

        request.enqueue(object:Callback<ArrayList<dataItem>>{
            override fun onResponse(
                call: Call<ArrayList<dataItem>>,
                response: Response<ArrayList<dataItem>>
            ) {
                if(response.code() == 200 || response.code() == 201){
                    val adapter = response.body()?.let { AdapterFundRaiser(it,this@fund) }
                    data = response.body() as ArrayList<dataItem>
                    rv.adapter = adapter
                }
                else{
                    Log.d("response error",response.message())
                }
            }

            override fun onFailure(call: Call<ArrayList<dataItem>>, t: Throwable){
                Log.d("response failure",t.message.toString())
            }

        })


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
                R.id.ngo -> {
                    val intent = Intent(this, ngo::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.logout -> {
                    editor.clear()
                    editor.commit()

                    val intent = Intent(this, Login::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            true
        }

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val search_btn = menu.findItem(R.id.search)
        val search = search_btn?.actionView as SearchView
        search.queryHint = "Search Here"

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != "") {
                    val new_data = data.filter { all_users ->
                        val s = (all_users.type1)!!.lowercase()
                        s.startsWith(newText!!.lowercase())
                    }
                    binding.rvFundRaiser.adapter = AdapterFundRaiser(new_data as ArrayList<dataItem>, this@fund)
                    binding.rvFundRaiser.adapter?.notifyDataSetChanged()
                }
                if (newText == "") {
                    binding.rvFundRaiser.adapter = AdapterFundRaiser(data, this@fund)
                    binding.rvFundRaiser.adapter?.notifyDataSetChanged()
                }
                return true
            }

        })
        return true
    }

}