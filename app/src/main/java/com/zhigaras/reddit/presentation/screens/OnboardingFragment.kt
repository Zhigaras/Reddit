package com.zhigaras.reddit.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.zhigaras.reddit.R
import com.zhigaras.reddit.presentation.viewModels.OnboardingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingFragment : Fragment() {
    
    private val viewModel: OnboardingViewModel by viewModels()
    
    private val authLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val intentData = it.data ?:return@registerForActivityResult
            viewModel.handleAuthResponseIntent(intentData)
        }
    
    fun openAuthPage() = viewModel.prepareAuthPageIntent { authLauncher.launch(it) }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_onboarding, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val button = view.findViewById<Button>(R.id.button)
        button.setOnClickListener {
            openAuthPage()
        }
    }
    
    
}