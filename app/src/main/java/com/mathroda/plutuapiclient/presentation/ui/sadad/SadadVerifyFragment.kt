package com.mathroda.plutuapiclient.presentation.ui.sadad

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mathroda.plutuapiclient.R
import com.mathroda.plutuapiclient.core.Resource
import com.mathroda.plutuapiclient.databinding.FragmentSadadVerifyBinding
import com.mathroda.plutuapiclient.presentation.viewmodels.SadadViewModel

class SadadVerifyFragment : Fragment() {
    private lateinit var binding: FragmentSadadVerifyBinding
    private val viewModel: SadadViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentSadadVerifyBinding.inflate(inflater, container, false)

        binding.sendOtp.setOnClickListener {

            val phoneNumber = binding.phoneNumber.text.toString()
            val birthYear = binding.yearOfBirth.text.toString()
            val amount = binding.amount.text.toString()

            viewModel.sendOtpFunction(
                mobileNumber = phoneNumber,
                birthYear = birthYear,
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
                    val amount = data?.result?.amount!!
                    val directions = SadadVerifyFragmentDirections.actionSadadVerifyFragmentToSadadConfirmFragment2(processId, amount )
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