package com.tech.mpos

import FirstFragment
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tech.mpos.loginResponse.LoginResponse
import com.tech.mpos.transactionResponse.TransactionResponse
import com.tech.mpos.userResponse.UserResponse
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val homeFragment=FirstFragment()
    private val accountFragment=AccountFragment()
    private val businessDetailsFragment=BusinessDetailsFragment()
    private val paymentSetupFragment=PaymentSetupFragment()
    private val walletTransactionFragment=WalletTransactionFragment()
    private val transactionDetailsFragment=TransactionDetailsFragment()
    private val recentTransactionFragment=recentTransactionDetails()

    companion object {
        lateinit var responseBody: Response<LoginResponse>
        lateinit var transactionData: Response<TransactionResponse>
        lateinit var UserData: Response<UserResponse>
        lateinit var ACCESS_TOKEN: String
        lateinit var AMOUNT: String
        var POSITION = -1
        lateinit var CVV: String
//        var TRANSACTION_ID:String = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = getIntent();
        val position = intent.getStringExtra("position")
        val appBar = findViewById<AppBarLayout>(R.id.appBarLayout)
        val bottom = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        if (position?.toInt()!! == -2){
            finish()
//            replaceFragment(walletTransactionFragment)
        }
        else if(position.toInt() ==-3){
            bottom.visibility = View.GONE
            replaceFragment(recentTransactionFragment)
        }
        else if(position.toInt()!! >=0){
            appBar.visibility = View.GONE
            POSITION = position.toInt()
            replaceFragment(transactionDetailsFragment)
        }
        else
            replaceFragment(homeFragment)
//            replaceFragment(recentTransactionFragment)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = ""


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(homeFragment)
                R.id.person -> replaceFragment(accountFragment)
                R.id.paymb_button -> replaceFragment(paymentSetupFragment)
                R.id.business_details -> replaceFragment(businessDetailsFragment)
                R.id.wallet -> {
                    bottom.visibility = View.GONE
                    replaceFragment(walletTransactionFragment)
                }
//                R.id.wallet -> {
//                    val intent = Intent(this,TransactionsActivity::class.java)
//                    startActivity(intent)
//                }
            }
            true
        }
    }

    fun replaceFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment,fragment)
        transaction.commit()
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
