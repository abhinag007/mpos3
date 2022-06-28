package com.tech.mpos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tech.mpos.apiServices.ApiInterface
import com.tech.mpos.apiServices.RemoteDataSource
import com.tech.mpos.databinding.FragmentAccountBinding
import com.tech.mpos.databinding.FragmentWalletTransactionBinding

class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater,container,false)
        binding.outlinedTextField.editText?.setText(MainActivity.responseBody.body()?.data?.uid.toString())
        binding.emailCb.isEnabled = MainActivity.responseBody.body()?.data?.receipt?.email == true
        binding.businessNameTv.setText(MainActivity.responseBody.body()?.data?.businessName)
        binding.nameInitialsTv.setText(MainActivity.responseBody.body()?.data?.businessName?.get(0)?.toUpperCase().toString())
        binding.button2.setOnClickListener{
            val retIn = RemoteDataSource.getRetrofitInstance().create(ApiInterface::class.java)
//            val apiInterface = retIn.updateUser(token = "Bearer ${MainActivity.ACCESS_TOKEN}")

        }
        return binding.root
    }

}