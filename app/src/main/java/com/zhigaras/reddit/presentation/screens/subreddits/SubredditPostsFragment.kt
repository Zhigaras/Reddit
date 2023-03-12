package com.zhigaras.reddit.presentation.screens.subreddits

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.ConcatAdapter
import com.zhigaras.reddit.R
import com.zhigaras.reddit.databinding.FragmentSubredditPostsBinding
import com.zhigaras.reddit.presentation.UiText
import com.zhigaras.reddit.presentation.paging.HeaderAdapter
import com.zhigaras.reddit.presentation.paging.PageLoadStateAdapter
import com.zhigaras.reddit.presentation.paging.PostsPageAdapter
import com.zhigaras.reddit.presentation.viewModels.SubredditsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SubredditPostsFragment : Fragment() {
    
    private var _binding: FragmentSubredditPostsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SubredditsViewModel by activityViewModels()
    private val headerAdapter = HeaderAdapter()
    private val postsPageAdapter = PostsPageAdapter(
        onPostClick = { onPostClick() },
        onCommentsClick = { onCommentsClick() },
        onVoteButtonsClick = { onVoteClick() }
    )
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSubredditPostsBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        observeSelectedSubreddit()
        setUpPageAdapter()
        setupLoadStates()
        
    }
    
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    
    fun setUpPageAdapter() {
        val concatAdapter = ConcatAdapter(
            headerAdapter,
            postsPageAdapter.withLoadStateFooter(PageLoadStateAdapter())
        )
        binding.recyclerView.adapter = concatAdapter
    }
    
    fun setupLoadStates() {
        binding.swipeRefresh.setOnRefreshListener {
            postsPageAdapter.refresh()
        }
        postsPageAdapter.loadStateFlow.onEach {
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
    
    fun observeSelectedSubreddit() {
        lifecycleScope.launchWhenStarted {
            viewModel.observe { subreddit ->
                subreddit?.let {
                    Log.d("AAA", it.toString())
                    observePagerFlow(it.displayName)
                    headerAdapter.setData(it)
                    //TODO(повторная загрузка по возвращению назад)
                }
            }
        }
    }
    
    fun observePagerFlow(subredditName: String) {
        viewModel.getPagedPosts(subredditName).onEach {
            postsPageAdapter.submitData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }
    
    fun onPostClick() {
    
    }
    
    fun onCommentsClick() {
    
    }
    
    fun onVoteClick() {
    
    }
    
    fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
    
}