package com.zhigaras.reddit.data.remote.response.posts.temp


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SecureMedia(
    @Json(name = "reddit_video")
    val redditVideo: RedditVideo
)