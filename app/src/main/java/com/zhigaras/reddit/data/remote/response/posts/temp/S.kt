package com.zhigaras.reddit.data.remote.response.posts.temp


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class S(
    @Json(name = "u")
    val u: String,
    @Json(name = "x")
    val x: Int,
    @Json(name = "y")
    val y: Int
)