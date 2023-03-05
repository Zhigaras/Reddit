package com.zhigaras.reddit.presentation.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.zhigaras.reddit.R
import com.zhigaras.reddit.databinding.FragmentSubredditsBinding
import com.zhigaras.reddit.presentation.paging.MarginItemDecoration
import com.zhigaras.reddit.presentation.paging.PageLoadStateAdapter
import com.zhigaras.reddit.presentation.paging.SubredditsPageAdapter
import com.zhigaras.reddit.presentation.viewModels.NewSubredditsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SubredditsFragment : Fragment() {
    
    private val viewModel: NewSubredditsViewModel by viewModels()
    private var _binding: FragmentSubredditsBinding? = null
    private val binding get() = _binding!!
    private val subredditsPageAdapter = SubredditsPageAdapter()
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSubredditsBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setUpPageAdapter()
    }
    
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    
    fun setupLoadStates() {
        subredditsPageAdapter.loadStateFlow.onEach {
            val loadStateList = listOf(it.append, it.refresh, it.prepend)
            loadStateList.forEach {loadState ->
                if (loadState is LoadState.Loading) {
                
                }
            }
        }
    }
    
    fun setUpPageAdapter() {
        
        binding.recyclerView.apply {
            adapter = subredditsPageAdapter.withLoadStateFooter(PageLoadStateAdapter())
            addItemDecoration(
                MarginItemDecoration(
                    resources.getDimensionPixelSize(
                        R.dimen.recyclerview_dimen
                    )
                )
            )
        }
        
        viewModel.pagedNewSubreddits.onEach {
            subredditsPageAdapter.submitData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }
}