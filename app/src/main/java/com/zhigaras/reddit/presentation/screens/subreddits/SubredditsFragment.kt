package com.zhigaras.reddit.presentation.screens.subreddits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.zhigaras.reddit.R
import com.zhigaras.reddit.databinding.FragmentSubredditsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubredditsFragment : Fragment() {
    
    private var _binding: FragmentSubredditsBinding? = null
    private val binding get() = _binding!!
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSubredditsBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val pageAdapter = SubredditsViewPagerAdapter(this)
        
        val viewPager = binding.subredditsPager
        viewPager.adapter = pageAdapter
        TabLayoutMediator(binding.tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.new_title)
                1 -> tab.text = getString(R.string.popular_title)
            }
        }.attach()
    }
    
    
}