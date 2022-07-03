package com.tech.mpos

import FirstFragment
import android.graphics.Movie
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.FileObserver.ACCESS
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tech.mpos.apiServices.ApiInterface
import com.tech.mpos.apiServices.RemoteDataSource
import com.tech.mpos.loginResponse.LoginResponse
import com.tech.mpos.transactionResponse.TransactionResponse
import com.tech.mpos.userResponse.UserResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {
    private val homeFragment=FirstFragment()
    private val accountFragment=AccountFragment()
    private val businessDetailsFragment=BusinessDetailsFragment()
    private val paymentSetupFragment=PaymentSetupFragment()
    private val walletTransactionFragment=WalletTransactionFragment()

    companion object {
        lateinit var responseBody: Response<LoginResponse>
        lateinit var transactionData: Response<TransactionResponse>
        lateinit var UserData: Response<UserResponse>
        lateinit var ACCESS_TOKEN: String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
//        getTransactionData()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(homeFragment)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = ""

//        val firstFragment=FirstFragment()
//        setCurrentFragment(firstFragment)
//        val firstFragment=RecentTransactionFragment()
//        setCurrentFragment(firstFragment)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(homeFragment)
                R.id.person -> replaceFragment(accountFragment)
                R.id.paymb_button -> replaceFragment(paymentSetupFragment)
                R.id.business_details -> replaceFragment(businessDetailsFragment)
                R.id.wallet -> replaceFragment(walletTransactionFragment)
            }
            true
        }
    }

    fun replaceFragment(fragment: Fragment){
        if(fragment!=null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.flFragment,fragment)
            transaction.commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }

}


/*
1) Update Account Details (Check Without animation)
2) Update Business Details (Check Without Animation)
3) Make dialog box enter cvv (check)
4) Remove 0 balance proceed to payment (Check)
5) Call payment api
6) Do recyclerview show recent transaction
7) Show transaction Details
 */
