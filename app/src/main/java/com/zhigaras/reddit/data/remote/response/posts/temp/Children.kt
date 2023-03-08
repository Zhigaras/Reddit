package com.zhigaras.reddit.data.remote.response.posts.temp


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.zhigaras.reddit.data.remote.response.posts.PostModel

@JsonClass(generateAdapter = true)
data class Children(
    @Json(name = "data")
    val `data`: PostModel,
    @Json(name = "kind")
    val kind: String
)