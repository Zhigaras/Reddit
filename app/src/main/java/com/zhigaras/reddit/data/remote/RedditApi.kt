package com.zhigaras.reddit.data.remote

import com.zhigaras.reddit.data.remote.response.CommonResponse
import com.zhigaras.reddit.data.remote.response.posts.PostsData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RedditApi {
    companion object {
        const val BASE_URL = "https://oauth.reddit.com/"
    }
    
    @GET("/subreddits/{type}")
    suspend fun loadSubreddits(
        @Path("type") type: String,
        @Query("after") afterKey: String
    ): Response<CommonResponse>
    
    @GET("/subreddits/search")
    suspend fun searchSubreddits(
        @Query("q") query: String
    ): Response<CommonResponse>
    
    @GET("/r/{subredditName}")
    suspend fun loadSubredditPosts(
        @Path("subredditName") subredditName: String,
        @Query("after") afterKey: String
    ): Response<CommonResponse>
    
    @GET("/r/{subredditName}/comments/{postId}")
    suspend fun getPostDetails(
        @Path("subredditName") subredditName: String,
        @Path("postId") postId: String
    ): Response<List<PostsData>> //TODO
    
    @POST("/api/vote")
    suspend fun vote(
        @Query("id") id: String,
        @Query("dir") direction: Int
    )
    
    @POST("/api/subscribe")
    suspend fun subscribeUnsubscribe(
        @Query("action") action: String,
        @Query("sr_name") displayName: String
    ): Response<Unit>
}