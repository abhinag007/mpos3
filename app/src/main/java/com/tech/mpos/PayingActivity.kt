package com.tech.mpos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.tech.mpos.nfcService.NfcActivity

class PayingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paying)

        Log.d("check","above")
        Intent(this, NfcActivity::class.java)
        Log.d("check","below")


    }
}