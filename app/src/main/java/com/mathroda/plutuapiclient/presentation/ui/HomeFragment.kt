package com.mathroda.plutuapiclient.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.mathroda.plutuapiclient.R
import com.mathroda.plutuapiclient.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.sadad.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_sadadVerifyFragment)
        }

        binding.adfali.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_adfaliVerifyFragment)
        }

        binding.localBanks.setOnClickListener {

        }


        return binding.root
    }


}