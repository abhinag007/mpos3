package com.tech.mpos

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.tech.mpos.MainActivity.Companion.AMOUNT
import com.tech.mpos.databinding.FragmentPaymentSetupBinding


class PaymentSetupFragment : Fragment() {

    private var _binding: FragmentPaymentSetupBinding?=null
    private val binding get() = _binding!!
    private var amount: Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var tax:Double = 0.0
        var totalAmount: Double = 0.0
        _binding = FragmentPaymentSetupBinding.inflate(inflater, container, false)
        binding.nameInitialsTv.setText(MainActivity.responseBody.body()?.data?.businessName?.get(0)?.toUpperCase().toString())
        binding.businessNameTv.setText(MainActivity.responseBody.body()?.data?.businessName)
        binding.proceedButtonBtn.setOnClickListener {
            if(binding.totalAmountUpdateTv.text != "CAD 0.0"){
                AMOUNT = totalAmount.toString()
                val intent = Intent (getActivity(), PayingActivity::class.java)
                intent.putExtra("totalAmount",totalAmount.toString())
                startActivity(intent)
            }
            else {
                Toast.makeText(context, "Please Enter Amount",Toast.LENGTH_SHORT).show()
            }
        }


        binding.inputAmountTil.editText?.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (binding.inputTaxTil.editText?.text.toString().equals(""))
                    tax =  0.0

                else
                    tax = binding.inputTaxTil.editText?.text.toString().toDouble()

                if(s.toString().equals(""))
                    amount = 0.0
                else
                    amount = s.toString().toDouble();
                totalAmount = amount + (amount * tax * 0.01)

                binding.totalAmountUpdateTv.text = "CAD $totalAmount"
            }

        })


        binding.inputTaxTil.editText?.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
           }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

                if (s.toString().equals(""))
                    tax =  0.0

                else
                    tax = s.toString().toDouble()

                totalAmount = amount + (amount * tax * 0.01)
                binding.totalAmountUpdateTv.setText("CAD $totalAmount")

            }

        })
        return binding.root
    }

}