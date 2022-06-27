package com.tech.mpos

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    ): View? {
        _binding = FragmentPaymentSetupBinding.inflate(inflater, container, false)
        binding.proceedButtonBtn.setOnClickListener {
            val intent = Intent (getActivity(), PayingActivity::class.java)
            getActivity()?.startActivity(intent)
        }
//        val amount = binding.inputAmountTil.editText?.text.toString()
//        val tax = binding.inputTaxTil.editText?.text.toString()
//        binding.totalAmountUpdateTv.text = "amount+tax"

        binding.inputAmountTil.editText?.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if(count ==0){
                    binding.totalAmountUpdateTv.text = "CAD 0.0"
                }
                binding.totalAmountUpdateTv.text = s.toString()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(count ==0){
                    binding.totalAmountUpdateTv.text = "CAD 0.0"
                }
                binding.totalAmountUpdateTv.text = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {

                binding.totalAmountUpdateTv.text = s.toString()
            }

        })

        var tax:String
        binding.inputTaxTil.editText?.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if(count ==0){
                    binding.totalAmountUpdateTv.text = "CAD 0.0"
                }
                tax=  s.toString()
                binding.totalAmountUpdateTv.text = tax

//                binding.totalAmountUpdateTv.text = (amount + (amount*s.toString().toDouble()*0.01)).toString()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                tax=  s.toString()

                if((tax.toDouble()*10).toString().equals("")){
                    binding.totalAmountUpdateTv.text = "CAD 0.0"
                }
                binding.totalAmountUpdateTv.text = (tax.toDouble()*10).toString()
            }

            override fun afterTextChanged(s: Editable?) {
                tax=  s.toString()
                binding.totalAmountUpdateTv.text = tax
            }

        })
        return binding.root
    }


/*    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PaymentSetupFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }*/

}