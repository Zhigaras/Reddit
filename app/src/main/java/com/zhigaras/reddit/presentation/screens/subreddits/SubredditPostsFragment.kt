package com.zhigaras.reddit.presentation.screens.subreddits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.zhigaras.reddit.databinding.FragmentSubredditPostsBinding
import com.zhigaras.reddit.presentation.viewModels.subreddits.SubredditPostsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubredditPostsFragment : Fragment() {
    
    private var _binding: FragmentSubredditPostsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SubredditPostsViewModel by viewModels()
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSubredditPostsBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
    }
}