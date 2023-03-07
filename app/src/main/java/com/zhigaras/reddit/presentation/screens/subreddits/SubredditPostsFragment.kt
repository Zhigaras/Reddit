package com.zhigaras.reddit.presentation.screens.subreddits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zhigaras.reddit.R
import com.zhigaras.reddit.databinding.FragmentSubredditPostsBinding

class SubredditPostsFragment : Fragment() {
    
    private var _binding: FragmentSubredditPostsBinding? = null
    private val binding get() = _binding!!
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSubredditPostsBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.button.setOnClickListener {
            findNavController().navigate(R.id.from_subreddit_posts_to_single_post)
        }
    }
    
    
}