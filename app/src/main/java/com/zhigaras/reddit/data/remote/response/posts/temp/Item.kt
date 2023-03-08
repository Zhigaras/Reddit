package com.zhigaras.reddit.data.remote.response.posts.temp


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Item(
    @Json(name = "caption")
    val caption: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "media_id")
    val mediaId: String
)