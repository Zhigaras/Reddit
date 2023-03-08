package com.zhigaras.reddit.data.remote.response.posts.temp


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Image(
    @Json(name = "id")
    val id: String,
    @Json(name = "resolutions")
    val resolutions: List<Resolution>,
    @Json(name = "source")
    val source: Source,
    @Json(name = "variants")
    val variants: Variants
)