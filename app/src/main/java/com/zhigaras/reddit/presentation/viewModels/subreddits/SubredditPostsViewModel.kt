package com.zhigaras.reddit.presentation.viewModels.subreddits

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import com.zhigaras.reddit.data.MainRepository
import com.zhigaras.reddit.data.remote.response.posts.PostModel
import com.zhigaras.reddit.domain.model.ImagePostEntity
import com.zhigaras.reddit.domain.model.TextPostEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

@HiltViewModel
class SubredditPostsViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    
    fun getPagedPosts(subredditId: String) = mainRepository.getPagedPostsFlow(subredditId)
        .transform { pagingData -> emit(pagingData.map { (it.data as PostModel).map() }) }
        .transform { pagingData -> emit(pagingData.filter { it is ImagePostEntity || it is TextPostEntity }) }
        .cachedIn(viewModelScope)
    
}