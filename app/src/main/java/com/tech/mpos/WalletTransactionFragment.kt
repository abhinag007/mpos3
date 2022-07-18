package com.tech.mpos

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.tech.mpos.MainActivity.Companion.responseBody
import com.tech.mpos.MainActivity.Companion.transactionData
import com.tech.mpos.adapter.ListAdapter
import com.tech.mpos.apiServices.ApiInterface
import com.tech.mpos.apiServices.RemoteDataSource
import com.tech.mpos.databinding.FragmentWalletTransactionBinding
import com.tech.mpos.transactionResponse.TransactionResponse
import kotlinx.android.synthetic.main.fragment_wallet_transaction.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WalletTransactionFragment : Fragment(R.layout.fragment_wallet_transaction) {
    private var _binding: FragmentWalletTransactionBinding?=null
    private val binding get() = _binding!!
    lateinit var adapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWalletTransactionBinding.inflate(inflater,container,false)
//        binding.uniqueIDTv.text = responseBody.body()?.data?.uid.toString()
//        binding.textView13.text = "${transactionData.body()?.walletBalance} CAD"
        getTransaction()
        var check = false
        binding.settingIcon.setOnClickListener {
            check = !check
            if(check){
                binding.horzontalScroll.visibility = View.VISIBLE
            }
            else{
                binding.horzontalScroll.visibility = View.GONE
            }
        }
        return binding.root
    }

    private fun getTransaction() {
        val retIn = RemoteDataSource.getRetrofitInstance().create(ApiInterface::class.java)
        Log.d("Access Token: ", MainActivity.ACCESS_TOKEN)
        val apiInterface = retIn.transactionsData(token = "Bearer ${MainActivity.ACCESS_TOKEN}")

        apiInterface.enqueue( object : Callback<TransactionResponse> {

            override fun onResponse(
                call: Call<TransactionResponse>,
                response: Response<TransactionResponse>,
            ) {
                transactionData = response
                val transaction = response.body()

                if(transaction?.totalTransaction!=0){
                    textView16.visibility = View.GONE
                    progressBar.visibility = View.GONE
                    transactionBackground_ctl.visibility = View.VISIBLE
                    adapter = ListAdapter(context,transaction!!.transactions)
                    listOfTransactions_rv.adapter = adapter
                    listOfTransactions_rv.layoutManager = LinearLayoutManager(context)
                }
                else{
                    textView16.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<TransactionResponse>, t: Throwable) {
                t.message?.let { Log.d("data: ", it) }
//                Toast.makeText(this@FirstFragment, "Data: ${ t.message }", Toast.LENGTH_SHORT).show()
            }

        })
    }

}
