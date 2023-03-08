package com.zhigaras.reddit.data.remote

import com.zhigaras.reddit.data.remote.response.posts.CommonPostsResponse
import com.zhigaras.reddit.data.remote.response.subreddits.CommonSubredditsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RedditApi {
    companion object {
        const val BASE_URL = "https://oauth.reddit.com/"
    }
    @GET("/subreddits/{type}")
    suspend fun loadSubreddits(
        @Path("type") type: String,
        @Query("count") count: Int,
        @Query("after") afterKey: String
    ): Response<CommonSubredditsResponse>
    
    @GET("/subreddits/search")
    suspend fun searchSubreddits(
        @Query("q") query: String
    ): Response<CommonSubredditsResponse>
    
    @GET("/r/{subredditName}")
    suspend fun getSubredditPosts(
        @Path("subredditName") subredditName: String,
        @Query("count") count: Int,
        @Query("after") afterKey: String
    ): Response<CommonPostsResponse>
    
    @GET("/r/{subredditName}/comments/{postId}")
    suspend fun getPostDetails(
        @Path("subredditName") subredditName: String,
        @Path("postId") postId: String
    ) : Response<List<CommonPostsResponse>> //TODO
}