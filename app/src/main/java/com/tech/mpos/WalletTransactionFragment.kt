package com.tech.mpos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.tech.mpos.MainActivity.Companion.responseBody
import com.tech.mpos.MainActivity.Companion.transactionData
import com.tech.mpos.databinding.FragmentDashboardBinding
import com.tech.mpos.databinding.FragmentWalletTransactionBinding

class WalletTransactionFragment : Fragment(R.layout.fragment_wallet_transaction) {
    private var _binding: FragmentWalletTransactionBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWalletTransactionBinding.inflate(inflater,container,false)
        binding.uniqueIDTv.text = responseBody.body()?.data?.uid.toString()
        binding.textView13.text = "${transactionData.body()?.walletBalance} CAD"
        return binding.root
    }

}
