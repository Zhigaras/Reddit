package com.zhigaras.reddit.data.remote.response.posts

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommonPostsData(
    @Json(name = "after")
    val after: String?,
    @Json(name = "before")
    val before: String?,
    @Json(name = "children")
    val data: List<PostsData>,
    @Json(name = "dist")
    val dist: Int
)