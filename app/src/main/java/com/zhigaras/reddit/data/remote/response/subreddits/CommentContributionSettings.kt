package com.zhigaras.reddit.data.remote.response.subreddits

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommentContributionSettings(
    @Json(name = "allowed_media_types")
    val allowedMediaTypes: List<String>
)