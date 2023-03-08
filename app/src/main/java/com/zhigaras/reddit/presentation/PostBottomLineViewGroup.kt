package com.zhigaras.reddit.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.zhigaras.reddit.databinding.PostBottomLineViewBinding

class PostBottomLineViewGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    private var binding: PostBottomLineViewBinding

    init {
        binding = PostBottomLineViewBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
    }

}