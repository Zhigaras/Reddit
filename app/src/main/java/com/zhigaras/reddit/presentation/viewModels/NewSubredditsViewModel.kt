package com.zhigaras.reddit.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.zhigaras.reddit.data.MainRepository
import com.zhigaras.reddit.presentation.paging.SubredditsPagingSource
import com.zhigaras.reddit.domain.model.SubredditEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class NewSubredditsViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    
    private val newSubredditsQuery = "popular"
    
    val pagedNewSubreddits: Flow<PagingData<SubredditEntity>> = Pager(
        config = PagingConfig(pageSize = 25), /// TODO:
        pagingSourceFactory = { SubredditsPagingSource(mainRepository, newSubredditsQuery) }
    ).flow.cachedIn(viewModelScope)
}