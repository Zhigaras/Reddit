package com.zhigaras.reddit.presentation.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.zhigaras.reddit.data.MainRepository
import com.zhigaras.reddit.domain.ApiResult
import com.zhigaras.reddit.domain.model.SubredditEntity
import com.zhigaras.reddit.presentation.Communication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubredditsViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val selectedSubCommunication: Communication.Base<SubredditEntity>,
    private val onClickResultCommunication: Communication.Base<ApiResult<Int>>
) : ViewModel() {
    
    fun getPagedSubreddits(query: String) =
        mainRepository.getPagedSubredditsFlow(query).cachedIn(viewModelScope)
    
    fun getPagedPosts(subredditName: String) =
        mainRepository.getPagedPostsFlow(subredditName).cachedIn(viewModelScope)
    
    fun saveSelectedSubreddit(subreddit: SubredditEntity) {
        selectedSubCommunication.map(subreddit)
    }
    
    suspend fun observeSelectedSubreddit(collector: FlowCollector<SubredditEntity?>) {
        selectedSubCommunication.observe(collector)
    }
    
    fun subscribeUnsubscribe(displayName: String, isUserSubscriber: Boolean, position: Int) {
        viewModelScope.launch {
            val result = if (isUserSubscriber) {
                mainRepository.subscribeUnsubscribe("unsub", displayName)
            } else {
                mainRepository.subscribeUnsubscribe("sub", displayName)
            }
            if (result is ApiResult.Success)
            onClickResultCommunication.map(ApiResult.Success(position))
            Log.d("AAA", "viewModel $result")
        }
    }
    
    suspend fun observeClickResult(collector: FlowCollector<ApiResult<Int>?>) {
        onClickResultCommunication.observe(collector)
    }
}