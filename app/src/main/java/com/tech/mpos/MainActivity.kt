package com.tech.mpos

import FirstFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tech.mpos.loginResponse.LoginResponse
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val homeFragment=FirstFragment()
    private val accountFragment=AccountFragment()
    private val businessDetailsFragment=BusinessDetailsFragment()
    private val paymentSetupFragment=PaymentSetupFragment()
    private val walletTransactionFragment=WalletTransactionFragment()

    companion object {
        lateinit var responseBody: Response<LoginResponse>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
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
//        val navController = findNavController(R.id.flFragment)
//
//        bottomNavigationView.setupWithNavController(navController)
//        val appBarConfiguration = AppBarConfiguration(setOf(R.id.mainActivity, R.id.accountFragment, R.id.paymentSetupFragment, R.id.businessDetailsFragment,R.id.walletTransactionFragment2))
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
   /* private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_team_icon -> Toast.makeText(this, "Search Clicked", Toast.LENGTH_SHORT).show()
            R.id.bell_notification_icon -> Toast.makeText(this, "Refresh Clicked", Toast.LENGTH_SHORT).show()
            R.id.person -> {
                Toast.makeText(this, "Search Clicked", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }*/
}
