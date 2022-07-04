import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.tech.mpos.MainActivity
import com.tech.mpos.MainActivity.Companion.responseBody
import com.tech.mpos.MainActivity.Companion.transactionData
import com.tech.mpos.PaymentSetupFragment
import com.tech.mpos.R
import com.tech.mpos.WalletTransactionFragment
import com.tech.mpos.apiServices.ApiInterface
import com.tech.mpos.apiServices.RemoteDataSource
import com.tech.mpos.databinding.FragmentDashboardBinding
import com.tech.mpos.transactionResponse.TransactionResponse
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_wallet_transaction.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FirstFragment:Fragment(R.layout.fragment_dashboard) {
     private var _binding: FragmentDashboardBinding?=null
     private val binding get() = _binding!!
    private val paymentSetupFragment= PaymentSetupFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        WalletTransactionFragment().showData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater,container,false)
        val volume = resources.getStringArray(R.array.volume)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, volume)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
        binding.welcomeNameTv.text = "Welcome ${responseBody.body()?.data?.businessName}"
//        Log.d("data","hi ${transactionData.body()?.walletBalance.toString()}")

        binding.textView4.text = "CAD ${transactionData.body()?.walletBalance}"
        binding.tapBoxLl.setOnClickListener {
            (activity as MainActivity?)?.replaceFragment(paymentSetupFragment)
        }

        return binding.root
    }


/*
    fun getTransactionData() {
        val retIn = RemoteDataSource.getRetrofitInstance().create(ApiInterface::class.java)
        val apiInterface = retIn.transactionsData(token = "Bearer ${MainActivity.ACCESS_TOKEN}")

        apiInterface.enqueue( object : Callback<TransactionResponse> {
            override fun onResponse(
                call: Call<TransactionResponse>,
                response: Response<TransactionResponse>,
            ) {
                transactionData = response
//                textView4.setText(transactionData.body()?.walletBalance.toString())
//                Toast.makeText(this@FirstFragment, transactionData.body()?.walletBalance.toString(),
//                    Toast.LENGTH_SHORT).show()

            }

            override fun onFailure(call: Call<TransactionResponse>, t: Throwable) {
                t.message?.let { Log.d("data: ", it) }
//                Toast.makeText(this@FirstFragment, "Data: ${ t.message }", Toast.LENGTH_SHORT).show()
            }

        })

    }*/

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}