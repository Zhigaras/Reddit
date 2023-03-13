package com.zhigaras.reddit.presentation.viewModels.subreddits

import com.zhigaras.reddit.data.MainRepository
import com.zhigaras.reddit.domain.ApiResult
import com.zhigaras.reddit.presentation.Communication
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularSubredditsViewModel @Inject constructor(
    private val mainRepository: MainRepository,
//    private val selectedSubCommunication: Communication.Base<SubredditEntity>,
    private val onClickResultCommunication: Communication.Base<ApiResult<Int>>
) : AbstractSubredditsViewModel(mainRepository, onClickResultCommunication) {
    
    override val apiQuery = "popular"
    
}