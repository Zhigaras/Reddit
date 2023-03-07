package com.zhigaras.reddit.presentation.screens.subreddits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.zhigaras.reddit.R
import com.zhigaras.reddit.databinding.FragmentSubredditsGenericBinding
import com.zhigaras.reddit.presentation.UiText
import com.zhigaras.reddit.presentation.paging.MarginItemDecoration
import com.zhigaras.reddit.presentation.paging.PageLoadStateAdapter
import com.zhigaras.reddit.presentation.paging.SubredditsPageAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

abstract class AbstractSubredditsFragment : Fragment(){
    
    protected abstract val viewModel: ViewModel
    private var _binding: FragmentSubredditsGenericBinding? = null
    private val binding get() = _binding!!
    protected val subredditsPageAdapter = SubredditsPageAdapter()
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSubredditsGenericBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setUpPageAdapter()
        setupLoadStates()
        observePagerFlow()
    }
    
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
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
    }
    
    fun setupLoadStates() {
        binding.swipeRefresh.setOnRefreshListener {
            subredditsPageAdapter.refresh()
        }
        subredditsPageAdapter.loadStateFlow.onEach {
            binding.swipeRefresh.isRefreshing = it.refresh == LoadState.Loading
            listOf(it.append, it.prepend, it.refresh).forEach { loadState ->
                if (loadState is LoadState.Error) {
                    showToast(
                        loadState.error.localizedMessage
                            ?: UiText.ResourceString(R.string.loading_error)
                                .asString(requireContext())
                    )
                }
            }
            
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }
    
    abstract fun observePagerFlow()
    
    fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
}