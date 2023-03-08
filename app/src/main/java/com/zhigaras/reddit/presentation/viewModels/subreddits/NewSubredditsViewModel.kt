package com.zhigaras.reddit.presentation.viewModels.subreddits

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.zhigaras.reddit.data.MainRepository
import com.zhigaras.reddit.data.remote.response.subreddits.SubredditModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

@HiltViewModel
class NewSubredditsViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    
    private val newSubredditsQuery = "new"
    
    val pagedNewSubreddits =
        mainRepository.getPagedSubredditsFlow(newSubredditsQuery)
            .transform { pagingData -> emit(pagingData.map { (it.data as SubredditModel).map() }) }
            .cachedIn(viewModelScope)
}