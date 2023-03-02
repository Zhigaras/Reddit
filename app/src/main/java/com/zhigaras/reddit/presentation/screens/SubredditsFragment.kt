package com.zhigaras.reddit.presentation.screens

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhigaras.reddit.R
import com.zhigaras.reddit.presentation.viewModels.SubredditsViewModel

class SubredditsFragment : Fragment() {
    
    companion object {
        fun newInstance() = SubredditsFragment()
    }
    
    private lateinit var viewModel: SubredditsViewModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SubredditsViewModel::class.java)
        // TODO: Use the ViewModel
    }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_subreddits, container, false)
    }
    
}