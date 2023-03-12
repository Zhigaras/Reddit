package com.zhigaras.reddit.presentation.screens.subreddits

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularSubredditsFragment : AbstractSubredditsFragment() {
    
    override val apiQuery = "popular"
    
}