package com.zhigaras.reddit.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.zhigaras.reddit.data.locale.DataStoreManager
import com.zhigaras.reddit.data.remote.RedditApi
import com.zhigaras.reddit.data.remote.response.subreddits.SubredditsData
import com.zhigaras.reddit.data.remote.response.subreddits.CommonSubredditsResponse
import com.zhigaras.reddit.presentation.paging.GenericPagingSource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val dataStoreManager: DataStoreManager.Base,
    private val redditApi: RedditApi
) {
    
    fun getPagedSubredditsFlow(query: String): Flow<PagingData<SubredditsData>> = Pager(
        config = PagingConfig(pageSize = 25),
        pagingSourceFactory = { GenericPagingSource(redditApi, query) }
    ).flow
    
    suspend fun saveAccessToken(token: String) {
        dataStoreManager.saveToken(token)
    }
    
    suspend fun checkAccessToken(): Boolean {
        return dataStoreManager.checkToken()
    }
    
    suspend fun loadSearchResults(query: String): Response<CommonSubredditsResponse> {
        return redditApi.searchSubreddits(query)
    }
    

}
