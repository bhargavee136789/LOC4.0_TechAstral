package com.example.techastral_loc

import android.app.Dialog
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.techastral_loc.databinding.ActivityDetailFundBinding
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class detail_fund : AppCompatActivity(),PaymentResultListener {
    lateinit var binding: ActivityDetailFundBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailFundBinding.inflate(layoutInflater)
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
        val amt = intent.getStringExtra("amt")
        val date = intent.getStringExtra("date")

        Log.d("type",type.toString())

        binding.fEventName.setText(name)
        binding.fEventType.setText(type)
        binding.fEventDesc.setText(desc!!.toString())
        binding.fEventDate.setText(date!!.substringBefore("00:00:00"))
        binding.fEventDeadline.setText(end.toString().substringBefore("00:00:00"))
        binding.fEventLoc.setText(loc)
        binding.fRequiredAmount.setText(amt.toString())

        Checkout.preload(applicationContext)

//        val amount = binding
        val button = binding.donate

        binding.donate.setOnClickListener {
            val dialog = Dialog(this)
            dialog.setCancelable(false)
            dialog.setTitle("Make a donation")
            dialog.setTitle("Donations")
            dialog.setContentView(R.layout.pay_layout)
            val amount = dialog.findViewById<EditText>(R.id.payText)
            val pay = dialog.findViewById<Button>(R.id.pay)
            val cancel = dialog.findViewById<Button>(R.id.cancel)

            dialog.create()
            dialog.show()

            pay.setOnClickListener {
                val value = amount.text.toString().toDouble() * 100
                startPayment(value.toString())
                dialog.dismiss()
            }

            cancel.setOnClickListener {
                dialog.dismiss()
            }



        }
    }

    fun startPayment(amount: String) {
        /*
        *  You need to pass current activity in order to let Razorpay create CheckoutActivity
        * */
        val co = Checkout()

        co.setKeyID("rzp_test_C33WGVfe5ULgc9")

        try {
            val options = JSONObject()
            options.put("name", "Razorpay Corp")
            options.put("description", "Demoing Charges")
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png")
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
//            options.put("order_id", "order_DBJOWzybf0sJbb");
            options.put("amount", amount)//pass amount in currency subunits

//            val retryObj = JSONObject();
//            retryObj.put("enabled", true);
//            retryObj.put("max_count", 4);
//            options.put("retry", retryObj);

            val prefill = JSONObject()
            prefill.put("email", "meetmehta2811@gmail.com")
            prefill.put("contact", "9867023748")

            options.put("prefill", prefill)
            co.open(this, options)
        } catch (e: Exception) {
            Toast.makeText(this, "Error in payment: " + e.message, Toast.LENGTH_LONG).show()
            Log.d("payment error", e.toString())
            e.printStackTrace()
        }
    }

    override fun onPaymentSuccess(p0: String?) {
        val rf = Retrofit.Builder()
            .baseUrl("http://shrutiprasad.pythonanywhere.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitApi::class.java)

//        val data = dataFund()
//
//        val request = rf.fund()

        Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show()

    }

    override fun onPaymentError(p0: Int, p1: String?) {
        Toast.makeText(this, "Payment Not Successful", Toast.LENGTH_SHORT).show()

    }
}
