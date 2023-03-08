package com.zhigaras.reddit.data.remote.response.subreddits

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommonSubredditsResponse(
    @Json(name = "data")
    val commonData: CommonSubredditsData,
    @Json(name = "kind")
    val kind: String
)