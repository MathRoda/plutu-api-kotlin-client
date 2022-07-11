package com.mathroda.plutuapiclient.presentation.ui.sadad

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mathroda.plutuapiclient.R
import com.mathroda.plutuapiclient.core.Resource
import com.mathroda.plutuapiclient.databinding.FragmentAdfaliConfirmBinding
import com.mathroda.plutuapiclient.databinding.FragmentHomeBinding
import com.mathroda.plutuapiclient.databinding.FragmentSadadConfirmBinding
import com.mathroda.plutuapiclient.databinding.FragmentSadadVerifyBinding
import com.mathroda.plutuapiclient.presentation.viewmodels.SadadViewModel

class SadadConfirmFragment : Fragment() {
    private lateinit var binding: FragmentSadadConfirmBinding
    private val viewModel: SadadViewModel by viewModels()
    private val args: SadadConfirmFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSadadConfirmBinding.inflate(inflater, container, false)

        binding.payment.setOnClickListener {
            val processId = args.processId
            val amount = args.amount
            val code = binding.otpCode.text.toString()

            viewModel.confirmFunction(
                processId = processId,
                otpCode = code,
                amount = amount,
                invoiceNo = processId
            )
        }

        viewModel.confirm.observe(viewLifecycleOwner) { result ->
            when(result) {
                is Resource.Loading -> {binding.progressBar.visibility = View.VISIBLE}

                is Resource.Success -> {
                    val data = result.data
                    val successMsg  = data?.message
                    Toast.makeText(requireContext(), successMsg , Toast.LENGTH_LONG).show()

                    val directions = SadadConfirmFragmentDirections.actionSadadConfirmFragmentToHomeFragment2()
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