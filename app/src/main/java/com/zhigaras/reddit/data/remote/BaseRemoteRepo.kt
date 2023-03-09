package com.zhigaras.reddit.data.remote

import com.zhigaras.reddit.R
import com.zhigaras.reddit.domain.ApiResult
import com.zhigaras.reddit.presentation.UiText
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

interface SafeRemoteRepo {
    
    abstract class BaseRemoteRepo {
        
        suspend fun <Data> safeApiCall(apiToBeCalled: suspend () -> Response<Data>): ApiResult<Data> {
            return try {
                val response = apiToBeCalled()
                
                if (response.isSuccessful) {
                    ApiResult.Success(_data = response.body())
                } else {
                    ApiResult.Error(exception = UiText.ResourceString(R.string.check_connection))
                }
            } catch (e: HttpException) {
                ApiResult.Error(
                    exception = if (e.message != null) UiText.DynamicString(e.message())
                    else UiText.ResourceString(R.string.something_went_wrong)
                )
            } catch (e: IOException) {
                ApiResult.Error(exception = UiText.ResourceString(R.string.check_connection))
            } catch (e: Exception) {
                ApiResult.Error(exception = UiText.ResourceString(R.string.something_went_wrong))
            }
        }
    }
}

