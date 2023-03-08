package com.zhigaras.reddit.data.remote.response.posts.temp


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RedditVideo(
    @Json(name = "bitrate_kbps")
    val bitrateKbps: Int,
    @Json(name = "dash_url")
    val dashUrl: String,
    @Json(name = "duration")
    val duration: Int,
    @Json(name = "fallback_url")
    val fallbackUrl: String,
    @Json(name = "height")
    val height: Int,
    @Json(name = "hls_url")
    val hlsUrl: String,
    @Json(name = "is_gif")
    val isGif: Boolean,
    @Json(name = "scrubber_media_url")
    val scrubberMediaUrl: String,
    @Json(name = "transcoding_status")
    val transcodingStatus: String,
    @Json(name = "width")
    val width: Int
)