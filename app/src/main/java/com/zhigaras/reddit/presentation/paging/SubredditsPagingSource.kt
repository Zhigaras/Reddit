package com.zhigaras.reddit.presentation.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zhigaras.reddit.data.MainRepository
import com.zhigaras.reddit.domain.model.SubredditEntity
import javax.inject.Inject

class SubredditsPagingSource @Inject constructor(
    private val mainRepository: MainRepository,
    private val query: String
) : PagingSource<String, SubredditEntity>() {
    
    override fun getRefreshKey(state: PagingState<String, SubredditEntity>): String? = null
    
    override val keyReuseSupported = true
    
    override suspend fun load(params: LoadParams<String>): LoadResult<String, SubredditEntity> {

        val after = params.key ?: ""
        return kotlin.runCatching {
            mainRepository.loadSubreddits(type = query, count = params.loadSize, afterKey = after)
        }.fold(
            onSuccess = {
                LoadResult.Page(
                    data = it.body()?.data?.children?.map { it.data.toSubredditEntity() }
                        ?: emptyList(),
                    prevKey = null,
                    nextKey = it.body()?.data?.after
                )
            },
            onFailure = { LoadResult.Error(it) }
        )
    }
}