package com.example.techastral_loc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.techastral_loc.databinding.ActivitySignUpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignUp : AppCompatActivity() {

    lateinit var signUp:Button
    lateinit var fName:EditText
    lateinit var lName:EditText
    lateinit var userName:EditText
    lateinit var password:EditText
    lateinit var email:EditText
    lateinit var phone:EditText
    lateinit var pincode:EditText
    lateinit var area:EditText

    lateinit var binding: ActivitySignUpBinding
    val baseUrl = "http://shrutiprasad.pythonanywhere.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signUp = binding.signBtn
        userName = binding.signFullname
        password = binding.signPassword
        email = binding.signEmail
        phone = binding.pNumber
        pincode = binding.pincode
        area = binding.area

        signUp.setOnClickListener {
            regsiter()
        }

        if(savedInstanceState!=null) {
            binding.signEmail.setText(savedInstanceState.getString("signEmail"))
            binding.signPassword.setText(savedInstanceState.getString("signPassword"))
            binding.signFullname.setText(savedInstanceState.getString("signUsername"))
            binding.pNumber.setText(savedInstanceState.getString("pNumber"))
            binding.pincode.setText(savedInstanceState.getString("pincode"))
            binding.area.setText(savedInstanceState.getString("area"))
        }
    }

    fun regsiter(){
//        if(fName.text.toString().isEmpty() || lName.text.toString().isEmpty() || userName.text.toString().isEmpty()
//            || email.text.toString().isEmpty() || password.text.toString().isEmpty()){
        if(userName.text.toString().isEmpty() || email.text.toString().isEmpty() || password.text.toString().isEmpty()){
            Toast.makeText(this,"Please enter data in all fields",Toast.LENGTH_SHORT).show()
        }
        else{
            val rf = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitApi::class.java)

            val rData = registerData(
                userName.text.toString(),
                email.text.toString(),
                phone.text.toString(),
                password.text.toString(),
                pincode.text.toString(),
                area.text.toString()
            )

            val rRequest = rf.register(rData)

            rRequest.enqueue(object :Callback<registerData>{
                override fun onResponse(call: Call<registerData>, response: Response<registerData>) {
                    if(response.code() == 201){
                        Toast.makeText(this@SignUp,"You have been successfully registered",Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@SignUp,Login::class.java)
                        Toast.makeText(this@SignUp,"Please Login",Toast.LENGTH_SHORT).show()
                        startActivity(intent)
                        finish()
                    }
                    else if(response.code() == 400){
                        fName.setText("")
                        lName.setText("")
                        userName.setText("")
                        email.setText("")
                        phone.setText("")
                        password.setText("")
                        Toast.makeText(this@SignUp,"Username or email is already registered",Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Log.d("regsiter error",response.message().toString())
                        fName.setText("")
                        lName.setText("")
                        userName.setText("")
                        email.setText("")
                        phone.setText("")
                        password.setText("")
                        Toast.makeText(this@SignUp,"Some error occurred please try again",Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<registerData>, t: Throwable) {
                    Log.d("SignUp failure",t.message.toString())
                }

            })
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putString("signEmail",binding.signEmail.text.toString())
        outState.putString("signPassword",binding.signPassword.text.toString())
        outState.putString("signUsername",binding.signFullname.text.toString())
        outState.putString("pNumber",binding.pNumber.text.toString())
        outState.putString("pincode",binding.pincode.text.toString())
        outState.putString("area",binding.area.text.toString())
    }
}