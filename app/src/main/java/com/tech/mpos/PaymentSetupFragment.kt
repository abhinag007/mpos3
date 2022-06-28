package com.tech.mpos

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tech.mpos.databinding.FragmentPaymentSetupBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PaymentSetupFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentPaymentSetupBinding?=null
    private val binding get() = _binding!!
    private var amount: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

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
            val intent = Intent (getActivity(), PayingActivity::class.java)
            intent.putExtra("totalAmount",totalAmount.toString())
            startActivity(intent)
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