package com.zhigaras.reddit.presentation.viewModels.subreddits

import androidx.lifecycle.ViewModel
import com.zhigaras.reddit.data.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SubredditPostsViewModel @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel() {
    
//    fun getPagedPosts(subredditId: String) = mainRepository.getPagedPostsFlow(subredditId)
    
}