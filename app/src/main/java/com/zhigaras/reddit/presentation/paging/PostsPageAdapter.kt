package com.zhigaras.reddit.presentation.paging

import android.app.ActionBar.LayoutParams
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zhigaras.reddit.domain.model.PostEntity
import com.zhigaras.reddit.presentation.screens.subreddits.PostViewGroup

class PostsPageAdapter(
    private val onPostClick: (String) -> Unit,
    private val onCommentsClick: (String) -> Unit,
    private val onVoteButtonsClick: (Int) -> Unit
) : PagingDataAdapter<PostEntity, PostViewHolder>(PostsDiffUtilCallback()) {
    
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        
        val item = getItem(position) ?: return
        
        val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        
        holder.viewGroup.layoutParams = layoutParams
        
        holder.viewGroup.setupView(
            post = item,
            onPostClick = onPostClick,
            onCommentsClick = onCommentsClick,
            onVoteButtonsClick = onVoteButtonsClick
        )
        
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(PostViewGroup(parent.context))
    }
}

class PostViewHolder(val viewGroup: PostViewGroup) : RecyclerView.ViewHolder(viewGroup.rootView)

class PostsDiffUtilCallback : DiffUtil.ItemCallback<PostEntity>() {
    override fun areItemsTheSame(oldItem: PostEntity, newItem: PostEntity): Boolean {
        return oldItem.id == newItem.id
    }
    
    override fun areContentsTheSame(oldItem: PostEntity, newItem: PostEntity): Boolean {
        return oldItem == newItem
    }
}