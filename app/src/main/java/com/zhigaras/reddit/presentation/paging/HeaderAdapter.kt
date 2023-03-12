package com.zhigaras.reddit.presentation.paging

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.zhigaras.reddit.R
import com.zhigaras.reddit.databinding.SubredditHeaderLayoutBinding
import com.zhigaras.reddit.domain.model.SubredditEntity
import com.zhigaras.reddit.presentation.UiText

class HeaderAdapter : Adapter<HeaderAdapter.HeaderViewHolder>() {
    
    class HeaderViewHolder(val binding: SubredditHeaderLayoutBinding) : ViewHolder(binding.root)
    
    private lateinit var data: SubredditEntity
    
    fun setData(subreddit: SubredditEntity) {
        this.data = subreddit
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        return HeaderViewHolder(
            SubredditHeaderLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }
    
    override fun getItemCount() = 1
    
    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        
        val context = holder.binding.root.context
        val bannerUrl = data.bannerImage
        val bannerColor = data.bannerColor
        val icon = data.subredditIcon
        val name = data.displayName
        val subs = data.subscribers
        
        with(holder.binding) {
            
            if (bannerColor.isNotBlank()) {
                banner.setBackgroundColor(Color.parseColor(bannerColor))
            }
            if (bannerUrl.isNotBlank()) {
                banner.setBackgroundColor(context.getColor(R.color.transparent))
                Glide.with(context)
                    .load(bannerUrl)
                    .into(banner)
            }
            if (icon.isNotBlank()) {
                Glide.with(context)
                    .load(icon)
                    .into(logo)
            } else {
                logo.setImageResource(R.drawable.reddit_placeholder)
            }
            if (name.isNotBlank()) {
                subredditName.text = name
            }
            if (subs.isNotBlank()) {
                subscribers.text = UiText.ResourceString(
                    R.string.subscribed,
                    subs
                ).asString(context)
            }
            joinButton.text = if (data.userIsSubscriber) UiText.ResourceString(R.string.leave)
                .asString(context)
            else UiText.ResourceString(R.string.join).asString(context)
        }
    }
}