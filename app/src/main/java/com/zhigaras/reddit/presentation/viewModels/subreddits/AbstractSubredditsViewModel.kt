package com.zhigaras.reddit.presentation.viewModels.subreddits

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.zhigaras.reddit.data.MainRepository
import com.zhigaras.reddit.domain.model.SubredditEntity
import com.zhigaras.reddit.presentation.viewModels.AbstractViewModel
import kotlinx.coroutines.launch

abstract class AbstractSubredditsViewModel(
    private val mainRepository: MainRepository
) : AbstractViewModel(mainRepository) {
    
    abstract val apiQuery: String
    
    fun getPagedSubreddits() =
        mainRepository.getPagedSubredditsFlow(apiQuery).cachedIn(viewModelScope)
    
    fun saveCurrentSubreddit(subreddit: SubredditEntity) {
        viewModelScope.launch {
            mainRepository.saveCurrentSubreddit(subreddit)
        }
    }
}