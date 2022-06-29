package com.tech.mpos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tech.mpos.MainActivity.Companion.responseBody
import com.tech.mpos.databinding.FragmentAccountBinding
import com.tech.mpos.models.ReceiptEmail
import kotlinx.android.synthetic.main.fragment_account.*

class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater,container,false)
        binding.outlinedTextField.editText?.setText(responseBody.body()?.data?.uid.toString())
        binding.emailCb.isEnabled = responseBody.body()?.data?.receipt?.email == true
        binding.businessNameTv.setText(responseBody.body()?.data?.businessName)
        binding.nameInitialsTv.setText(responseBody.body()?.data?.businessName?.get(0)?.toUpperCase().toString())
        binding.button2.setOnClickListener{
         /*   val retIn = RemoteDataSource.getRetrofitInstance().create(ApiInterface::class.java)
            val receiptEmail = ReceiptEmail(email_cb.isEnabled==true)
            val apiInterface = retIn.updateReceiptEmail(token = "Bearer ${MainActivity.ACCESS_TOKEN}",receiptEmail)
            mProgress.show()
            apiInterface.enqueue(object: retrofit2.Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.code() == 200){
                        UserData = response
                        mProgress.dismiss()
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Toast.makeText(context,"Update Failed due to some error", Toast.LENGTH_SHORT).show()
                }
            })*/
            FetchTransactionData().updateUserData(context,ReceiptEmail(email_cb.isEnabled==true))
        }
        return binding.root
    }

}

class Task(private var emailStatus: Boolean, private var email: String)