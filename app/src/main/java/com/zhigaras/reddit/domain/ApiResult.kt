package com.zhigaras.reddit.domain

import com.zhigaras.reddit.presentation.UiText

sealed class ApiResult<out T>(
    val data: T?,
    val errorMessage: UiText?
) {
    
    class Success<out T>(_data: T?) : ApiResult<T>(
        data = _data,
        errorMessage = null
    )
    
    class Error<out T>(
        val exception: UiText
    ) : ApiResult<T>(
        data = null,
        errorMessage = exception
    )
    
    class Loading<out T>(_data: T?) : ApiResult<T>(
        data = _data,
        errorMessage = null
    )
}