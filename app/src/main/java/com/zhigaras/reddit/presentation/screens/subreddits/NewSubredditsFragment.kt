package com.zhigaras.reddit.presentation.screens.subreddits

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewSubredditsFragment : AbstractSubredditsFragment() {

    override val apiQuery = "new"
    
}