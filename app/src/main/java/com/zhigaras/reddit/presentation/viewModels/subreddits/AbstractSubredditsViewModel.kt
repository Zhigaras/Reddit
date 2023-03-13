package com.zhigaras.reddit.presentation.viewModels.subreddits

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.zhigaras.reddit.R
import com.zhigaras.reddit.data.MainRepository
import com.zhigaras.reddit.domain.ApiResult
import com.zhigaras.reddit.presentation.Communication
import com.zhigaras.reddit.presentation.UiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class AbstractSubredditsViewModel(
    private val mainRepository: MainRepository,
//    private val selectedSubCommunication: Communication.Base<SubredditEntity>,
    private val onClickResultCommunication: Communication.Base<ApiResult<Int>>
) : ViewModel() {
    
    abstract val apiQuery: String
    
    private val _subscribeChannel = Channel<ApiResult<Int>>()
    val subscribeChannel get() = _subscribeChannel.receiveAsFlow()
    
    fun getPagedSubreddits() =
        mainRepository.getPagedSubredditsFlow(apiQuery).cachedIn(viewModelScope)
    
    fun subscribeUnsubscribe(displayName: String, isUserSubscriber: Boolean, position: Int) {
        viewModelScope.launch {
            _subscribeChannel.send(ApiResult.Loading(position))
            kotlin.runCatching {
                if (isUserSubscriber) {
                    mainRepository.subscribeUnsubscribe("unsub", displayName)
                } else {
                    mainRepository.subscribeUnsubscribe("sub", displayName)
                }
            }.fold(
                onSuccess = { _subscribeChannel.send(ApiResult.Success(position)) },
                onFailure = {
                    _subscribeChannel.send(
                        ApiResult.Error(UiText.ResourceString(R.string.something_went_wrong))
                    )
                }
            )
        }
    }
}