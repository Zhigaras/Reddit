package com.zhigaras.reddit.data.remote

import com.zhigaras.reddit.data.remote.response.posts.PostsResponse
import com.zhigaras.reddit.data.remote.response.subreddits.SubredditsResponse
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
    ): Response<SubredditsResponse>
    
    @GET("/subreddits/search")
    suspend fun searchSubreddits(
        @Query("q") query: String
    ): Response<SubredditsResponse>
    
    @GET("/r/{name}")
    suspend fun getSubredditPosts(
        @Path("name") subredditName: String,
        @Query("count") count: Int,
        @Query("after") afterKey: String
    ): Response<PostsResponse>
}