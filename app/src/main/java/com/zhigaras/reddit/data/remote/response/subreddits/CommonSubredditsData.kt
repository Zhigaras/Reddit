package com.zhigaras.reddit.data.remote.response.subreddits

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommonSubredditsData(
    @Json(name = "after")
    val after: String?,
    @Json(name = "before")
    val before: String?,
    @Json(name = "children")
    val data: List<SubredditsData>,
    @Json(name = "dist")
    val dist: Int
)