package com.zhigaras.reddit.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.zhigaras.reddit.data.locale.DataStoreManager
import com.zhigaras.reddit.data.remote.RedditApi
import com.zhigaras.reddit.data.remote.SafeRemoteRepo
import com.zhigaras.reddit.data.remote.response.CommonResponse
import com.zhigaras.reddit.data.remote.response.Thing
import com.zhigaras.reddit.domain.ApiResult
import com.zhigaras.reddit.presentation.paging.GenericPagingSource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val dataStoreManager: DataStoreManager.Base,
    private val redditApi: RedditApi
) : SafeRemoteRepo.BaseRemoteRepo() {
    
    fun getPagedSubredditsFlow(query: String): Flow<PagingData<Thing>> = Pager(
        config = PagingConfig(pageSize = 25),
        pagingSourceFactory = {
            GenericPagingSource { afterKey ->
                redditApi.loadSubreddits(query, afterKey)
            }
        }
    ).flow
    
    fun getPagedPostsFlow(subredditId: String): Flow<PagingData<Thing>> = Pager(
        config = PagingConfig(pageSize = 25),
        pagingSourceFactory = {
            GenericPagingSource { afterKey ->
                redditApi.loadSubredditPosts(subredditId, afterKey)
            }
        }
    ).flow
    
    suspend fun saveAccessToken(token: String) {
        dataStoreManager.saveToken(token)
    }
    
    suspend fun checkAccessToken(): Boolean {
        return dataStoreManager.checkToken()
    }
    
    suspend fun loadSearchResults(query: String): Response<CommonResponse> {
        return redditApi.searchSubreddits(query)
    }
    
    suspend fun vote(id: String, direction: Int): ApiResult<Nothing> {
        return safeApiCall { redditApi.vote(id, direction) }
    }
    
}
