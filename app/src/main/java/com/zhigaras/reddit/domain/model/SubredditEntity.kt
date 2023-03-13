package com.zhigaras.reddit.domain.model

data class SubredditEntity(
    val id: String,
    val displayNamePrefixed: String,
    val displayName: String,
    val subscribers: String,
    var userIsSubscriber: IsSubscriber,
    val publicDescription: String,
    val bannerImage: String,
    val bannerColor: String,
    val subredditIcon: String
): AbstractRedditEntity()

data class IsSubscriber(
    val isSubscribed: Boolean,
    val isLoading: Boolean
)
