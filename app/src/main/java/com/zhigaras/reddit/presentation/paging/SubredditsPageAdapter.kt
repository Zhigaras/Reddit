package com.zhigaras.reddit.presentation.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zhigaras.reddit.R
import com.zhigaras.reddit.databinding.SubredditItemBinding
import com.zhigaras.reddit.domain.model.SubredditEntity
import com.zhigaras.reddit.presentation.UiText

class SubredditsPageAdapter :
    PagingDataAdapter<SubredditEntity, SubredditViewHolder>(DiffUtilCallback()) {
    
    override fun onBindViewHolder(holder: SubredditViewHolder, position: Int) {
        val item = getItem(position) ?: return
        with(holder.binding) {
            name.text = item.displayName
            description.text = item.publicDescription
            subscribers.text = UiText.ResourceString(R.string.subscribed, item.subscribers)
                .asString(holder.itemView.context)
            if (item.userIsSubscriber) {
                followButton.apply {
                    setImageResource(R.drawable.ic_unfollow)
                    setColorFilter(context.getColor(R.color.red))
                }
                
            }else {
                followButton.apply {
                    setImageResource(R.drawable.ic_follow)
                    setColorFilter(context.getColor(R.color.green))
                }
            }
            
            Glide.with(logo.context)
                .load(item.logo)
                .circleCrop()
                .into(logo)
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubredditViewHolder {
        return SubredditViewHolder(
            SubredditItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }
}

class SubredditViewHolder(val binding: SubredditItemBinding) : RecyclerView.ViewHolder(binding.root)

class DiffUtilCallback : DiffUtil.ItemCallback<SubredditEntity>() {
    override fun areItemsTheSame(oldItem: SubredditEntity, newItem: SubredditEntity): Boolean {
        return oldItem.id == newItem.id
    }
    
    override fun areContentsTheSame(oldItem: SubredditEntity, newItem: SubredditEntity): Boolean {
        return oldItem == newItem
    }
}