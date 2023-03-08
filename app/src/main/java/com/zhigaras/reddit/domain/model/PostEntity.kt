package com.zhigaras.reddit.domain.model

data class PostEntity(
    val id: String,
    val author: String,
    val date: String,
    val title: String,
    val subreddit: String,
    val score: String,
    val numComments: Int,
    val saved: Boolean,
    val selfText: String,
    val selfUrl: String,
    val isVideo: Boolean
)