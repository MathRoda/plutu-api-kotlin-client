package com.mathroda.plutuapiclient.presentation.ui.adfali

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mathroda.plutuapiclient.R
import com.mathroda.plutuapiclient.core.Resource
import com.mathroda.plutuapiclient.databinding.FragmentAdfaliVerifyBinding
import com.mathroda.plutuapiclient.presentation.ui.sadad.SadadVerifyFragmentDirections
import com.mathroda.plutuapiclient.presentation.viewmodels.AdfaliViewModel

class AdfaliVerifyFragment : Fragment() {
    private lateinit var binding: FragmentAdfaliVerifyBinding
    private val viewModel: AdfaliViewModel by viewModels()
    private var amountTop = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAdfaliVerifyBinding.inflate(inflater, container, false)

        binding.sendOtp.setOnClickListener {

            val phoneNumber = binding.phoneNumber.text.toString()
            val amount = binding.amount.text.toString()
            amountTop = amount


            viewModel.sendOtpFunction(
                mobileNumber = phoneNumber,
                amount = amount
            )
        }

        viewModel.sendOtp.observe(viewLifecycleOwner) { result ->
            when(result) {
                is Resource.Loading -> { binding.progressBar.visibility = View.VISIBLE}
                is Resource.Success -> {
                    val data = result.data
                    val successMsg  = data?.message
                    Toast.makeText(requireContext(), successMsg , Toast.LENGTH_SHORT).show()

                    val processId = data?.result?.process_id.toString()
                    val directions = AdfaliVerifyFragmentDirections.actionAdfaliVerifyFragmentToAdfaliConfirmFragment(processId, amountTop)
                    findNavController().navigate(directions)
                }
                is Resource.Error -> {}
                is Resource.ErrorResult -> {
                    val errorMsg = result.errorResponse?.error?.message
                    Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.INVISIBLE
                }
            }
        }
        return binding.root
    }

}