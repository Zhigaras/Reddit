package com.zhigaras.reddit.presentation.screens.subreddits

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.zhigaras.reddit.R
import com.zhigaras.reddit.databinding.PostItemBinding
import com.zhigaras.reddit.domain.model.PostEntity

class PostViewGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : CardView(context, attrs, defStyle) {
    
    private var binding: PostItemBinding
    
    init {
        binding = PostItemBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
    }
    
    fun setupView(
        post: PostEntity,
        onPostClick: (String) -> Unit,
        onCommentsClick: (String) -> Unit,
        onVoteButtonsClick: (Int) -> Unit
    ) {
        fillView(post)
        setupShareClickListener(post)
        setupOnRootClickListener(post.id, onPostClick)
        setupOnCommentsClickListener(post.id, onCommentsClick)
        setupVoteButtonsClickListener(post.isLikedByUser, onVoteButtonsClick)
    }
    
    fun fillView(post: PostEntity) {
        with(binding) {
            postScore.text = post.score
            commentsNumber.text = post.numComments.toString()
            if (post.isLikedByUser == true) voteUpButton.setColorFilter(context.getColor(R.color.green))
            if (post.isLikedByUser == false) voteDownButton.setColorFilter(context.getColor(R.color.red))
            subreddit.text = post.subreddit
            author.text = post.author
            title.text = post.title
        }
    }
    
    fun setupShareClickListener(post: PostEntity) {
        binding.shareButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND).also {
                it.putExtra(Intent.EXTRA_TEXT, post.selfUrl)
                it.type = "text/plain"
            }
            try {
                context.startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    fun setupOnRootClickListener(postId: String, onPostClick: (String) -> Unit) {
        binding.root.setOnClickListener {
            onPostClick(postId)
        }
    }
    
    fun setupOnCommentsClickListener(postId: String, onCommentsClick: (String) -> Unit) {
        binding.openCommentsButton.setOnClickListener {
            onCommentsClick(postId)
        }
    }
    
    fun setupVoteButtonsClickListener(isLikedByUser: Boolean?, onVoteButtonsClick: (Int) -> Unit) {
        binding.voteUpButton.setOnClickListener {
            if (isLikedByUser == true) onVoteButtonsClick(0)
            else onVoteButtonsClick(1)
        } //TODO
        binding.voteDownButton.setOnClickListener {
            if (isLikedByUser == false) onVoteButtonsClick(0)
            else onVoteButtonsClick(-1)
        }
    }
}