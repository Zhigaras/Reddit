package com.zhigaras.reddit.data

import androidx.paging.*
import com.zhigaras.reddit.data.locale.DataStoreManager
import com.zhigaras.reddit.data.locale.db.CachedSubredditsDao
import com.zhigaras.reddit.data.remote.RedditApi
import com.zhigaras.reddit.data.remote.SafeRemoteRepo
import com.zhigaras.reddit.data.remote.response.CommonResponse
import com.zhigaras.reddit.data.remote.response.posts.PostModel
import com.zhigaras.reddit.data.remote.response.subreddits.SubredditModel
import com.zhigaras.reddit.domain.ApiResult
import com.zhigaras.reddit.domain.model.ImagePostEntity
import com.zhigaras.reddit.domain.model.PostEntity
import com.zhigaras.reddit.domain.model.SubredditEntity
import com.zhigaras.reddit.domain.model.TextPostEntity
import com.zhigaras.reddit.presentation.paging.GenericPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val dataStoreManager: DataStoreManager.Base,
    private val redditApi: RedditApi,
    private val cachedSubredditsDao: CachedSubredditsDao
) : SafeRemoteRepo.BaseRemoteRepo() {
    
    fun getPagedSubredditsFlow(query: String): Flow<PagingData<SubredditEntity>> = Pager(
        config = PagingConfig(pageSize = 25),
        pagingSourceFactory = {
            GenericPagingSource { afterKey ->
                redditApi.loadSubreddits(query, afterKey)
            }
        }
    ).flow.transform { pagingData -> emit(pagingData.map { (it.data as SubredditModel).map() }) }
    
    fun getPagedPostsFlow(subredditName: String): Flow<PagingData<PostEntity>> = Pager(
        config = PagingConfig(pageSize = 25),
        pagingSourceFactory = {
            GenericPagingSource { afterKey ->
                redditApi.loadSubredditPosts(subredditName, afterKey)
            }
        }
    ).flow.transform { pagingData -> emit(pagingData.map { (it.data as PostModel).map() }) }
        .transform { pagingData -> emit(pagingData.filter { it is ImagePostEntity || it is TextPostEntity }) }
    
    
    suspend fun saveCurrentSubreddit(subreddit: SubredditEntity) = cachedSubredditsDao.saveSubreddit(subreddit)
    
    fun getCurrentSubreddit(subredditName: String): Flow<SubredditEntity> {
        return cachedSubredditsDao.getSubreddit(subredditName)
    }
    
    suspend fun saveAccessToken(token: String) {
        dataStoreManager.saveToken(token)
    }
    
    suspend fun checkAccessToken(): Boolean {
        return dataStoreManager.checkToken()
    }
    
    suspend fun loadSearchResults(query: String): Response<CommonResponse> {
        return redditApi.searchSubreddits(query)
    }
    
//    suspend fun vote(id: String, direction: Int): ApiResult<Unit> {
//        return safeApiCall { redditApi.vote(id, direction) }
//    }
    
    suspend fun subscribeUnsubscribe(action: String, displayName: String): ApiResult<Unit> {
        return safeApiCall { redditApi.subscribeUnsubscribe(action, displayName) }
    }
}
