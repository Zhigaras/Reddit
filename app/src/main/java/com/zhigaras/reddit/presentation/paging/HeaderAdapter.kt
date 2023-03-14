package com.zhigaras.reddit.presentation.paging

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.zhigaras.reddit.R
import com.zhigaras.reddit.databinding.SubredditHeaderLayoutBinding
import com.zhigaras.reddit.domain.ApiResult
import com.zhigaras.reddit.domain.model.IsSubscriber
import com.zhigaras.reddit.domain.model.SubredditEntity
import com.zhigaras.reddit.presentation.UiText

class HeaderAdapter(
    private val onSubscribeClick: (String, Boolean, Int) -> Unit
) : Adapter<HeaderAdapter.HeaderViewHolder>() {
    
    class HeaderViewHolder(val binding: SubredditHeaderLayoutBinding) : ViewHolder(binding.root)
    
    private lateinit var data: SubredditEntity
    
    @SuppressLint("NotifyDataSetChanged")
    fun setData(subreddit: SubredditEntity) {
        this.data = subreddit
        notifyDataSetChanged()
    }
    
    @SuppressLint("NotifyDataSetChanged")
    fun updateSubscribeInfo(subscribeResult: ApiResult<Int>) {
        Log.d("AAA", "start ${data.userIsSubscriber}")
        if (subscribeResult is ApiResult.Loading) {
            this.data.userIsSubscriber.isLoading = true
            Log.d("AAA", "loading ${data.userIsSubscriber}")
        } else {
            this.data.userIsSubscriber = IsSubscriber(!data.userIsSubscriber.isSubscribed, false)
            Log.d("AAA", "success ${data.userIsSubscriber}")
        }
        notifyDataSetChanged()
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
        
        if (this::data.isInitialized) {
            
            val context = holder.binding.root.context
            val bannerUrl = data.bannerImage
            val bannerColor = data.bannerColor
            val icon = data.subredditIcon
            val name = data.displayName
            val subs = data.subscribers
            val isSubscribed = data.userIsSubscriber.isSubscribed
            val isLoading = data.userIsSubscriber.isLoading
            
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
                joinButton.apply {
                    isEnabled = !isLoading
                    text =
                        if (isSubscribed) UiText.ResourceString(R.string.leave)
                            .asString(context)
                        else UiText.ResourceString(R.string.join).asString(context)
                    setOnClickListener {
                        onSubscribeClick(name, isSubscribed, position)
                    }
                }
            }
        }
    }
}