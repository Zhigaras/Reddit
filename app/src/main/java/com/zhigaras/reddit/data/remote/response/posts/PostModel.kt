package com.zhigaras.reddit.data.remote.response.posts


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.zhigaras.reddit.data.remote.response.MapData
import com.zhigaras.reddit.domain.model.PostEntity
import java.text.SimpleDateFormat
import java.util.*

@JsonClass(generateAdapter = true)
data class PostModel(
//    @Json(name = "all_awardings")
//    val allAwardings: List<Any>,
//    @Json(name = "allow_live_comments")
//    val allowLiveComments: Boolean,
//    @Json(name = "approved_at_utc")
//    val approvedAtUtc: Any,
//    @Json(name = "approved_by")
//    val approvedBy: Any,
//    @Json(name = "archived")
//    val archived: Boolean,
    @Json(name = "author")
    val author: String,
//    @Json(name = "author_flair_background_color")
//    val authorFlairBackgroundColor: Any,
//    @Json(name = "author_flair_css_class")
//    val authorFlairCssClass: Any,
//    @Json(name = "author_flair_richtext")
//    val authorFlairRichtext: List<Any>,
//    @Json(name = "author_flair_template_id")
//    val authorFlairTemplateId: Any,
//    @Json(name = "author_flair_text")
//    val authorFlairText: Any,
//    @Json(name = "author_flair_text_color")
//    val authorFlairTextColor: Any,
//    @Json(name = "author_flair_type")
//    val authorFlairType: String,
//    @Json(name = "author_fullname")
//    val authorFullname: String,
//    @Json(name = "author_is_blocked")
//    val authorIsBlocked: Boolean,
//    @Json(name = "author_patreon_flair")
//    val authorPatreonFlair: Boolean,
//    @Json(name = "author_premium")
//    val authorPremium: Boolean,
//    @Json(name = "awarders")
//    val awarders: List<Any>,
//    @Json(name = "banned_at_utc")
//    val bannedAtUtc: Any,
//    @Json(name = "banned_by")
//    val bannedBy: Any,
//    @Json(name = "can_gild")
//    val canGild: Boolean,
//    @Json(name = "can_mod_post")
//    val canModPost: Boolean,
//    @Json(name = "category")
//    val category: Any,
//    @Json(name = "clicked")
//    val clicked: Boolean,
//    @Json(name = "content_categories")
//    val contentCategories: Any,
//    @Json(name = "contest_mode")
//    val contestMode: Boolean,
    @Json(name = "created")
    val created: Int,
//    @Json(name = "crosspost_parent")
//    val crosspostParent: String,
//    @Json(name = "crosspost_parent_list")
//    val crosspostParentList: List<CrosspostParent>,
//    @Json(name = "discussion_type")
//    val discussionType: Any,
//    @Json(name = "distinguished")
//    val distinguished: Any,
//    @Json(name = "domain")
//    val domain: String,
//    @Json(name = "downs")
//    val downs: Int,
//    @Json(name = "edited")
//    val edited: Int,
//    @Json(name = "gallery_data")
//    val galleryData: GalleryData,
//    @Json(name = "gilded")
//    val gilded: Int,
//    @Json(name = "gildings")
//    val gildings: GildingsX,
//    @Json(name = "hidden")
//    val hidden: Boolean,
//    @Json(name = "hide_score")
//    val hideScore: Boolean,
    @Json(name = "id")
    val id: String,
//    @Json(name = "is_created_from_ads_ui")
//    val isCreatedFromAdsUi: Boolean,
//    @Json(name = "is_crosspostable")
//    val isCrosspostable: Boolean,
//    @Json(name = "is_gallery")
//    val isGallery: Boolean,
//    @Json(name = "is_meta")
//    val isMeta: Boolean,
//    @Json(name = "is_original_content")
//    val isOriginalContent: Boolean,
//    @Json(name = "is_reddit_media_domain")
//    val isRedditMediaDomain: Boolean,
//    @Json(name = "is_robot_indexable")
//    val isRobotIndexable: Boolean,
//    @Json(name = "is_self")
//    val isSelf: Boolean,
    @Json(name = "is_video")
    val isVideo: Boolean,
    @Json(name = "likes")
    val likes: Boolean?,
//    @Json(name = "link_flair_background_color")
//    val linkFlairBackgroundColor: String,
//    @Json(name = "link_flair_css_class")
//    val linkFlairCssClass: Any,
//    @Json(name = "link_flair_richtext")
//    val linkFlairRichtext: List<Any>,
//    @Json(name = "link_flair_text")
//    val linkFlairText: Any,
//    @Json(name = "link_flair_text_color")
//    val linkFlairTextColor: String,
//    @Json(name = "link_flair_type")
//    val linkFlairType: String,
//    @Json(name = "locked")
//    val locked: Boolean,
//    @Json(name = "media")
//    val media: Media,
//    @Json(name = "media_embed")
//    val mediaEmbed: MediaEmbedX,
//    @Json(name = "media_only")
//    val mediaOnly: Boolean,
//    @Json(name = "mod_note")
//    val modNote: Any,
//    @Json(name = "mod_reason_by")
//    val modReasonBy: Any,
//    @Json(name = "mod_reason_title")
//    val modReasonTitle: Any,
//    @Json(name = "mod_reports")
//    val modReports: List<Any>,
//    @Json(name = "name")
//    val name: String,
//    @Json(name = "no_follow")
//    val noFollow: Boolean,
    @Json(name = "num_comments")
    val numComments: Int,
//    @Json(name = "num_crossposts")
//    val numCrossposts: Int,
//    @Json(name = "num_reports")
//    val numReports: Any,
//    @Json(name = "over_18")
//    val over18: Boolean,
//    @Json(name = "parent_whitelist_status")
//    val parentWhitelistStatus: String,
//    @Json(name = "permalink")
//    val permalink: String,
//    @Json(name = "pinned")
//    val pinned: Boolean,
//    @Json(name = "post_hint")
//    val postHint: String,
//    @Json(name = "preview")
//    val preview: Preview,
//    @Json(name = "pwls")
//    val pwls: Int,
//    @Json(name = "quarantine")
//    val quarantine: Boolean,
//    @Json(name = "removal_reason")
//    val removalReason: Any,
//    @Json(name = "removed_by")
//    val removedBy: Any,
//    @Json(name = "removed_by_category")
//    val removedByCategory: Any,
//    @Json(name = "report_reasons")
//    val reportReasons: Any,
    @Json(name = "saved")
    val saved: Boolean,
    @Json(name = "score")
    val score: Int,
//    @Json(name = "secure_media")
//    val secureMedia: SecureMedia,
//    @Json(name = "secure_media_embed")
//    val secureMediaEmbed: SecureMediaEmbedX,
    @Json(name = "selftext")
    val selftext: String,
//    @Json(name = "selftext_html")
//    val selftextHtml: String,
//    @Json(name = "send_replies")
//    val sendReplies: Boolean,
//    @Json(name = "spoiler")
//    val spoiler: Boolean,
//    @Json(name = "stickied")
//    val stickied: Boolean,
//    @Json(name = "subreddit")
//    val subreddit: String,
//    @Json(name = "subreddit_id")
//    val subredditId: String,
    @Json(name = "subreddit_name_prefixed")
    val subredditNamePrefixed: String,
//    @Json(name = "subreddit_subscribers")
//    val subredditSubscribers: Int,
//    @Json(name = "subreddit_type")
//    val subredditType: String,
//    @Json(name = "suggested_sort")
//    val suggestedSort: Any,
//    @Json(name = "thumbnail")
//    val thumbnail: String,
//    @Json(name = "thumbnail_height")
//    val thumbnailHeight: Int,
//    @Json(name = "thumbnail_width")
//    val thumbnailWidth: Int,
    @Json(name = "title")
    val title: String,
//    @Json(name = "top_awarded_type")
//    val topAwardedType: Any,
//    @Json(name = "total_awards_received")
//    val totalAwardsReceived: Int,
//    @Json(name = "treatment_tags")
//    val treatmentTags: List<Any>,
//    @Json(name = "ups")
//    val ups: Int,
//    @Json(name = "upvote_ratio")
//    val upvoteRatio: Double,
    @Json(name = "url")
    val url: String,
//    @Json(name = "url_overridden_by_dest")
//    val urlOverriddenByDest: String,
//    @Json(name = "user_reports")
//    val userReports: List<Any>,
//    @Json(name = "view_count")
//    val viewCount: Any,
//    @Json(name = "visited")
//    val visited: Boolean,
//    @Json(name = "whitelist_status")
//    val whitelistStatus: String,
//    @Json(name = "wls")
//    val wls: Int
) : MapData {
    
    override fun map(): PostEntity {
        val post = PostEntity(
            subreddit = subredditNamePrefixed,
            author = author,
            date = SimpleDateFormat(
                "dd/MM/yyyy HH:mm:ss",
                Locale.getDefault()
            ).format(Date(created.toLong())),
            title = title,
            score = when (score) {
                in 0..999 -> score.toString()
                in 1_000..999_999 -> "${score / 1_000}K"
                else -> "${score / 1_000_000}M"
            },
            numComments = numComments,
            saved = saved,
            selfUrl = url,
            id = id,
            isLikedByUser = likes
        )
        if (url.endsWith(".jpg") || url.endsWith(".png")) return post.toImagePostEntity(url)
        if (selftext.isNotBlank()) return post.toTextPostEntity(selftext)
        return post
    }
}