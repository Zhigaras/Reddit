package com.zhigaras.reddit.presentation.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zhigaras.reddit.data.remote.RedditApi
import com.zhigaras.reddit.data.remote.response.subreddits.SubredditsData
import javax.inject.Inject

class GenericPagingSource @Inject constructor(
    private val redditApi: RedditApi,
    private val query: String
) : PagingSource<String, SubredditsData>() {
    
    override val keyReuseSupported = true
    
    override fun getRefreshKey(state: PagingState<String, SubredditsData>): String? = null
    
    override suspend fun load(params: LoadParams<String>): LoadResult<String, SubredditsData> {

        val after = params.key ?: ""
        return kotlin.runCatching {
            redditApi.loadSubreddits(type = query, count = params.loadSize, afterKey = after)
        }.fold(
            onSuccess = {
                LoadResult.Page(
                    data = it.body()?.commonData?.data
                        ?: emptyList(),
                    prevKey = null,
                    nextKey = it.body()?.commonData?.after
                )
            },
            onFailure = { LoadResult.Error(it) }
        )
    }
}