package com.zhigaras.reddit.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.zhigaras.reddit.data.MainRepository
import com.zhigaras.reddit.domain.model.SubredditEntity
import com.zhigaras.reddit.presentation.Communication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

@HiltViewModel
class SubredditsViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val communication: Communication.Base<SubredditEntity>
) : ViewModel() {
    
    fun getPagedSubreddits(query: String) =
        mainRepository.getPagedSubredditsFlow(query).cachedIn(viewModelScope)
    
    fun getPagedPosts(subredditName: String) =
        mainRepository.getPagedPostsFlow(subredditName).cachedIn(viewModelScope)
    
    fun saveSelectedSubreddit(subreddit: SubredditEntity) {
        communication.map(subreddit)
    }
    
    suspend fun observe(collector: FlowCollector<SubredditEntity?>) {
        communication.observe(collector)
    }
}