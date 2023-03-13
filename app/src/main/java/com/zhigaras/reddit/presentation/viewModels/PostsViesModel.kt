package com.zhigaras.reddit.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.zhigaras.reddit.R
import com.zhigaras.reddit.data.MainRepository
import com.zhigaras.reddit.domain.ApiResult
import com.zhigaras.reddit.domain.model.SubredditEntity
import com.zhigaras.reddit.presentation.Communication
import com.zhigaras.reddit.presentation.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViesModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val selectedSubCommunication: Communication.Base<SubredditEntity>,
    private val onClickResultCommunication: Communication.Base<ApiResult<Int>>
): ViewModel() {
    
    fun getPagedPosts(subredditName: String) =
        mainRepository.getPagedPostsFlow(subredditName).cachedIn(viewModelScope)
    
    fun subscribeUnsubscribe(displayName: String, isUserSubscriber: Boolean, position: Int) {
        viewModelScope.launch {
            onClickResultCommunication.map(ApiResult.Loading(position))
            kotlin.runCatching {
                if (isUserSubscriber) {
                    mainRepository.subscribeUnsubscribe("unsub", displayName)
                } else {
                    mainRepository.subscribeUnsubscribe("sub", displayName)
                }
            }.fold(
                onSuccess = { onClickResultCommunication.map(ApiResult.Success(position)) },
                onFailure = {
                    onClickResultCommunication.map(
                        ApiResult.Error(UiText.ResourceString(R.string.something_went_wrong))
                    )
                }
            )
        }
    }
}