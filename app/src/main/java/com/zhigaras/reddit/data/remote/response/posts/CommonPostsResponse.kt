package com.zhigaras.reddit.data.remote.response.posts

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommonPostsResponse(
    @Json(name = "data")
    val commonData: CommonPostsData,
    @Json(name = "kind")
    val kind: String
)