package com.zhigaras.reddit.presentation.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.zhigaras.reddit.R
import com.zhigaras.reddit.presentation.viewModels.AuthViewModel

class AuthFragment : Fragment() {
    
    private val viewModel: AuthViewModel by viewModels()
    private val authLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val intentData = it.data ?: return@registerForActivityResult
            viewModel.handleAuthResponseIntent(intentData)
        }
    
    fun openAuthPage() = viewModel.prepareAuthPageIntent { authLauncher.launch(it) }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }
    
}