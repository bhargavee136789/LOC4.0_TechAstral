package com.example.techastral_loc

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.techastral_loc.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onStart() {
        super.onStart()

        var pref = applicationContext.getSharedPreferences("MyPref", MODE_PRIVATE)

        val token = pref.getString("token",null)
        if(!token.isNullOrEmpty()){
           val intent = Intent(this, Voluntering::class.java)
           startActivity(intent)
           finish()
        }
        else{
            Log.d("token in data",pref.getString("token",null).toString())
            Log.d("email in data",pref.getString("email",null).toString())
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportActionBar?.hide()

        var pref = applicationContext.getSharedPreferences("MyPref", MODE_PRIVATE)
        var editor: SharedPreferences.Editor = pref.edit()

        val baseUrl = "http://shrutiprasad.pythonanywhere.com/"

        val email = binding.loginEmail
        val password = binding.loginPassword
//        val username = findViewById<EditText>(R.id.loginUser)
        val login = binding.btnlogin
        val signUp = binding.signUp
        signUp.setOnClickListener {
            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)
        }

        login.setOnClickListener {


            if(email.text.toString().isEmpty() || password.text.toString().isEmpty() ){
                Toast.makeText(this,"Please enter data in all fields",Toast.LENGTH_SHORT).show()
            }
            else{
                val rf = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RetrofitApi::class.java)

                val dataLogin = logData(
                    email.text.toString(),
                    "",
                    "",
                    password.text.toString(),
//                    ""
                )

                val lRequest = rf.login(dataLogin)

                lRequest.enqueue(object : Callback<logData> {
                    override fun onResponse(call: Call<logData>, response: Response<logData>) {
                        if(response.code() == 200){
                            Toast.makeText(this@Login,"Welcome",Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@Login,Voluntering::class.java)
                            Log.d("log in data",response.body()!!.email.toString())
                            editor.putString("token",response.body()!!.tokens.toString())
                            editor.putString("email",response.body()!!.email.toString())
                            editor.putString("username",response.body()!!.username.toString())
//                            editor.putString("name",response.body()!!.name.toString())
                            editor.commit()
                            startActivity(intent)
                            finish()
                        }
                        else{
                            email.setText("")
                            password.setText("")
                            Log.d("Login error",response.message().toString())
                            Toast.makeText(this@Login,"Please try again",Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<logData>, t: Throwable) {
                        Log.d("Login failure",t.message.toString())
                    }

                })

            }
        }

        if(savedInstanceState!=null){
            binding.loginEmail.setText(savedInstanceState.getString("loginEmail"))
            binding.loginPassword.setText(savedInstanceState.getString("loginPassword"))
        }

    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putString("loginEmail",binding.loginEmail.text.toString())
        outState.putString("loginPassword",binding.loginPassword.text.toString())
    }
}