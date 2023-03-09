package com.zhigaras.reddit.data.remote.response.subreddits

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SubredditsData(
    @Json(name = "kind")
    val kind: String,
    @Json(name = "data")
    val data: SubredditModel
)