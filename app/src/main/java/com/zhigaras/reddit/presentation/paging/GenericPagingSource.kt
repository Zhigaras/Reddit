package com.zhigaras.reddit.presentation.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zhigaras.reddit.data.remote.response.subreddits.CommonSubredditsResponse
import com.zhigaras.reddit.data.remote.response.subreddits.SubredditsData
import retrofit2.Response
import javax.inject.Inject

class GenericPagingSource @Inject constructor(
    private val query: String,
    private val apiRequest: suspend (String, String) -> Response<CommonSubredditsResponse>
) : PagingSource<String, SubredditsData>() {
    
    override val keyReuseSupported = true
    
    override fun getRefreshKey(state: PagingState<String, SubredditsData>): String? = null
    
    override suspend fun load(params: LoadParams<String>): LoadResult<String, SubredditsData> {

        val after = params.key ?: ""
        return kotlin.runCatching {
            apiRequest(query, after)
        }.fold(
            onSuccess = {
                LoadResult.Page(
                    data = it.body()?.commonData?.data
                        ?: emptyList(),
                    prevKey = null,
                    nextKey = it.body()?.commonData?.after
                )
            },
            onFailure = { throw it }
        )
    }
}