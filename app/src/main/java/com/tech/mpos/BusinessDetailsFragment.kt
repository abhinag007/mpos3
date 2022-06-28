package com.tech.mpos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tech.mpos.databinding.FragmentAccountBinding
import com.tech.mpos.databinding.FragmentBusinessDetailsBinding

class BusinessDetailsFragment : Fragment() {
    private var _binding: FragmentBusinessDetailsBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBusinessDetailsBinding.inflate(inflater,container,false)
        binding.businessNameTil.editText?.setText(MainActivity.responseBody.body()?.data?.businessName)
        binding.phoneNumberTil.editText?.setText(MainActivity.responseBody.body()?.data?.mobileNumber)
        binding.emailAddressTil.editText?.setText(MainActivity.responseBody.body()?.data?.email)

        return binding.root
    }

}