package com.zhigaras.reddit.presentation.viewModels.subreddits

import com.zhigaras.reddit.data.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularSubredditsViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : AbstractSubredditsViewModel(mainRepository) {
    
    override val apiQuery = "popular"
    
}