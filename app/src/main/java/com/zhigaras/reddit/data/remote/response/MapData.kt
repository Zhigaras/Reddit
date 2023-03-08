package com.zhigaras.reddit.data.remote.response

import com.zhigaras.reddit.domain.model.AbstractRedditEntity

interface MapData {
    
    fun map(): AbstractRedditEntity
}