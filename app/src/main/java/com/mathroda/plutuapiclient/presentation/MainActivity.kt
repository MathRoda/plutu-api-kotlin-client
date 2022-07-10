package com.mathroda.plutuapiclient.presentation

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.mathroda.plutuapiclient.R
import com.mathroda.plutuapiclient.core.Resource
import com.mathroda.plutuapiclient.presentation.viewmodel.PlutuViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: PlutuViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        viewModel.sendOtp.observe(this) { result ->
          when(result) {
              is Resource.Success -> Log.d("result", result.data.toString())
              is Resource.Error -> Log.d("result", result.message.toString())
              is Resource.Loading -> Log.d("result", result.data.toString())
          }
        }

    }
}