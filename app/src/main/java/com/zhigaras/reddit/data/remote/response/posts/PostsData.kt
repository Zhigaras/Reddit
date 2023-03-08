package com.zhigaras.reddit.data.remote.response.posts

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostsData(
    @Json(name = "data")
    val data: PostModel,
    @Json(name = "kind")
    val kind: String
)