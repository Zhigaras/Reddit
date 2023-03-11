package com.zhigaras.reddit.domain.model

data class SubredditEntity(
    val id: String,
    val displayNamePrefixed: String,
    val displayName: String,
    val subscribers: Int,
    val userIsSubscriber: Boolean,
    val publicDescription: String,
    val bannerImage: String,
    val bannerColor: String,
    val subredditIcon: String
): AbstractRedditEntity()
