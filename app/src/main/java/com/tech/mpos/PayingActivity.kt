package com.tech.mpos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.tech.mpos.nfcService.NfcActivity

class PayingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paying)
        val amount=intent.getStringExtra("totalAmount")

        val textView = findViewById<TextView>(R.id.textView2)
        textView.text = "CAD $amount"
        Log.d("check","above")
        Intent(this, NfcActivity::class.java)
        Log.d("check","below")

        val close = findViewById<ImageView>(R.id.closeScreen_iv)
        close.setOnClickListener {
            this.finish()
        }
    }
}