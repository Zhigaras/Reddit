package com.zhigaras.reddit.data.locale.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.zhigaras.reddit.domain.model.SubredditEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CachedSubredditsDao {
    
    @Insert(onConflict = REPLACE)
    suspend fun saveSubreddit(subreddit: SubredditEntity)
    
    @Query("SELECT * FROM subreddits")
    fun showAll(): Flow<SubredditEntity>
    
    @Query("SELECT * FROM subreddits WHERE displayName LIKE :subredditName")
    fun getSubreddit(subredditName: String): Flow<SubredditEntity>
    
    @Query("DELETE FROM subreddits")
    suspend fun clearAll()
    
    @Update
    suspend fun updateSubreddit(subreddit: SubredditEntity)
}