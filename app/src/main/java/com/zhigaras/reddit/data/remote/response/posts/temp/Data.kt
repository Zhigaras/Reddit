package com.zhigaras.reddit.data.remote.response.posts.temp

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "after")
    val after: String,
    @Json(name = "before")
    val before: Any,
    @Json(name = "children")
    val children: List<Children>,
    @Json(name = "dist")
    val dist: Int,
    @Json(name = "geo_filter")
    val geoFilter: Any,
    @Json(name = "modhash")
    val modhash: Any
)