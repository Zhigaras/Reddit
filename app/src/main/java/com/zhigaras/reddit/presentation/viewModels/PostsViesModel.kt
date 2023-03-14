package com.zhigaras.reddit.presentation.viewModels

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.zhigaras.reddit.data.MainRepository
import com.zhigaras.reddit.domain.model.SubredditEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PostsViesModel @Inject constructor(
    private val mainRepository: MainRepository
): AbstractViewModel(mainRepository) {
    
    fun getPagedPosts(subredditName: String) =
        mainRepository.getPagedPostsFlow(subredditName).cachedIn(viewModelScope)
    
    fun getCurrentSubreddit(subredditName: String): Flow<SubredditEntity> {
        return mainRepository.getCurrentSubreddit(subredditName)
    }
}