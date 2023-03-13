package com.zhigaras.reddit.presentation.screens.subreddits

import androidx.fragment.app.viewModels
import com.zhigaras.reddit.presentation.viewModels.subreddits.PopularSubredditsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularSubredditsFragment : AbstractSubredditsFragment() {
    
    override val viewModel: PopularSubredditsViewModel by viewModels()
    
    
}