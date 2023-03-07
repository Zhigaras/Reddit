package com.zhigaras.reddit.data

import com.zhigaras.reddit.data.locale.DataStoreManager
import com.zhigaras.reddit.data.remote.RedditApi
import com.zhigaras.reddit.data.remote.response.subreddits.SubredditsResponse
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val dataStoreManager: DataStoreManager.Base,
    private val redditApi: RedditApi
) {
    suspend fun saveAccessToken(token: String) {
        dataStoreManager.saveToken(token)
    }
    
    suspend fun checkAccessToken(): Boolean {
        return dataStoreManager.checkToken()
    }
    
    suspend fun loadSubreddits(type: String, count: Int, afterKey: String): Response<SubredditsResponse> {
        return redditApi.loadSubreddits(type, count, afterKey)
    }
    
    suspend fun loadSearchResults(query: String): Response<SubredditsResponse> {
        return redditApi.searchSubreddits(query)
    }
}
