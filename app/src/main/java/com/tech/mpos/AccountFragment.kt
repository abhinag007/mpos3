package com.tech.mpos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tech.mpos.MainActivity.Companion.UserData
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
        binding.emailCb.isChecked = UserData.body()?.receipt?.email == true
        binding.businessNameTv.setText(responseBody.body()?.data?.businessName)
        binding.nameInitialsTv.setText(responseBody.body()?.data?.businessName?.get(0)?.toUpperCase().toString())
        binding.button2.setOnClickListener{
            FetchTransactionData().updateUserReceiptData(context,ReceiptEmail(email_cb.isChecked==true))
        }
        return binding.root
    }

}

class Task(private var emailStatus: Boolean, private var email: String)