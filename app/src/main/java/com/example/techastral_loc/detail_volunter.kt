package com.example.techastral_loc

import android.content.DialogInterface
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.example.techastral_loc.databinding.ActivityDetailVolunterBinding

class detail_volunter : AppCompatActivity() {
    lateinit var binding:ActivityDetailVolunterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailVolunterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var pref = applicationContext.getSharedPreferences("MyPref", MODE_PRIVATE)
        var editor: SharedPreferences.Editor = pref.edit()

        val intent = intent
        val name = intent.getStringExtra("name")
        val type = intent.getStringExtra("type")
        val loc = intent.getStringExtra("loc")
        val desc = intent.getStringExtra("desc")
        val end = intent.getStringExtra("dead")
        val date = intent.getStringExtra("date")
        val people = intent.getStringExtra("people")


        Log.d("type",type.toString())

        binding.vEventName.setText(name)
        binding.vEventType.setText(type)
        binding.vEventDesc.setText(desc!!.substringBefore("link"))
        binding.vEventDate.setText(date!!.substringBefore("00:00:00"))
        binding.vEventDeadline.setText(end.toString().substringBefore("00:00:00"))
        binding.vEventLoc.setText(loc)
        binding.vEventPeople.setText(people.toString())

        val s = desc.substringAfter("link:")

        binding.volunteerBtn.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Save the link for meeting\n $s")
            builder.setTitle("Meeting Link")
            builder.setCancelable(false)
            builder.setPositiveButton("Ok"){dialog,which ->
                dialog.dismiss()
            }
            val dialog:AlertDialog = builder.create()
            dialog.show()
        }

    }
}