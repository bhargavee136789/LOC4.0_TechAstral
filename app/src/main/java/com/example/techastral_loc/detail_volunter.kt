package com.example.techastral_loc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.techastral_loc.databinding.ActivityDetailVolunterBinding

class detail_volunter : AppCompatActivity() {
    lateinit var binding:ActivityDetailVolunterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailVolunterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}