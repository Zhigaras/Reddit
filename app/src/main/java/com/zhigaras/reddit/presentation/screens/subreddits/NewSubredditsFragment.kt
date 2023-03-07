package com.zhigaras.reddit.presentation.screens.subreddits

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.zhigaras.reddit.presentation.viewModels.subreddits.NewSubredditsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class NewSubredditsFragment : AbstractSubredditsFragment() {
    
    override val viewModel: NewSubredditsViewModel by viewModels()
    
    override fun observePagerFlow() {
        viewModel.pagedNewSubreddits.onEach {
            subredditsPageAdapter.submitData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }
    
}