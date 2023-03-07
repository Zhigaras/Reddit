package com.zhigaras.reddit.presentation.screens.subreddits

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.zhigaras.reddit.presentation.viewModels.subreddits.PopularSubredditsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class PopularSubredditsFragment : AbstractSubredditsFragment() {
    
    override val viewModel: PopularSubredditsViewModel by viewModels()
    
    override fun observePagerFlow() {
        viewModel.pagedPopularSubreddits.onEach {
            subredditsPageAdapter.submitData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }
}