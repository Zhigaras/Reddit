package com.zhigaras.reddit.data.remote.response.subreddits

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SubredditsResponse(
    @Json(name = "data")
    val `data`: Data,
    @Json(name = "kind")
    val kind: String
)