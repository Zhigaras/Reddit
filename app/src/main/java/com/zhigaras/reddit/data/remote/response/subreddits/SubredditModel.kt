package com.zhigaras.reddit.data.remote.response.subreddits


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.zhigaras.reddit.data.remote.response.MapData
import com.zhigaras.reddit.domain.formatDecimalSeparator
import com.zhigaras.reddit.domain.model.SubredditEntity

@JsonClass(generateAdapter = true)
data class SubredditModel(
//    @Json(name = "accept_followers")
//    val acceptFollowers: Boolean,
//    @Json(name = "accounts_active")
//    val accountsActive: Any,
//    @Json(name = "accounts_active_is_fuzzed")
//    val accountsActiveIsFuzzed: Boolean,
//    @Json(name = "active_user_count")
//    val activeUserCount: Any,
//    @Json(name = "advertiser_category")
//    val advertiserCategory: String,
//    @Json(name = "all_original_content")
//    val allOriginalContent: Boolean,
//    @Json(name = "allow_chat_post_creation")
//    val allowChatPostCreation: Boolean,
//    @Json(name = "allow_discovery")
//    val allowDiscovery: Boolean,
//    @Json(name = "allow_galleries")
//    val allowGalleries: Boolean,
//    @Json(name = "allow_images")
//    val allowImages: Boolean,
//    @Json(name = "allow_polls")
//    val allowPolls: Boolean,
//    @Json(name = "allow_prediction_contributors")
//    val allowPredictionContributors: Boolean,
//    @Json(name = "allow_predictions")
//    val allowPredictions: Boolean,
//    @Json(name = "allow_predictions_tournament")
//    val allowPredictionsTournament: Boolean,
//    @Json(name = "allow_talks")
//    val allowTalks: Boolean,
//    @Json(name = "allow_videogifs")
//    val allowVideogifs: Boolean,
//    @Json(name = "allow_videos")
//    val allowVideos: Boolean,
//    @Json(name = "allowed_media_in_comments")
//    val allowedMediaInComments: List<String>,
    @Json(name = "banner_background_color")
    val bannerBackgroundColor: String,
//    @Json(name = "banner_background_image")
//    val bannerBackgroundImage: String,
    @Json(name = "banner_img")
    val bannerImg: String?,
//    @Json(name = "banner_size")
//    val bannerSize: Any,
//    @Json(name = "can_assign_link_flair")
//    val canAssignLinkFlair: Boolean,
//    @Json(name = "can_assign_user_flair")
//    val canAssignUserFlair: Boolean,
//    @Json(name = "collapse_deleted_comments")
//    val collapseDeletedComments: Boolean,
//    @Json(name = "comment_contribution_settings")
//    val commentContributionSettings: CommentContributionSettings,
//    @Json(name = "comment_score_hide_mins")
//    val commentScoreHideMins: Int,
//    @Json(name = "community_icon")
//    val communityIcon: String,
//    @Json(name = "community_reviewed")
//    val communityReviewed: Boolean,
//    @Json(name = "created")
//    val created: Int,
//    @Json(name = "created_utc")
//    val createdUtc: Int,
//    @Json(name = "description")
//    val description: String,
//    @Json(name = "description_html")
//    val descriptionHtml: Any,
//    @Json(name = "disable_contributor_requests")
//    val disableContributorRequests: Boolean,
    @Json(name = "display_name")
    val displayName: String,
    @Json(name = "display_name_prefixed")
    val displayNamePrefixed: String,
//    @Json(name = "emojis_custom_size")
//    val emojisCustomSize: Any,
//    @Json(name = "emojis_enabled")
//    val emojisEnabled: Boolean,
//    @Json(name = "free_form_reports")
//    val freeFormReports: Boolean,
//    @Json(name = "has_menu_widget")
//    val hasMenuWidget: Boolean,
//    @Json(name = "header_img")
//    val headerImg: Any,
//    @Json(name = "header_size")
//    val headerSize: Any,
//    @Json(name = "header_title")
//    val headerTitle: String,
//    @Json(name = "hide_ads")
//    val hideAds: Boolean,
    @Json(name = "icon_img")
    val iconImg: String,
//    @Json(name = "icon_size")
//    val iconSize: Any,
    @Json(name = "id")
    val id: String,
//    @Json(name = "is_chat_post_feature_enabled")
//    val isChatPostFeatureEnabled: Boolean,
//    @Json(name = "is_crosspostable_subreddit")
//    val isCrosspostableSubreddit: Boolean,
//    @Json(name = "is_enrolled_in_new_modmail")
//    val isEnrolledInNewModmail: Any,
//    @Json(name = "key_color")
//    val keyColor: String,
//    @Json(name = "lang")
//    val lang: String,
//    @Json(name = "link_flair_enabled")
//    val linkFlairEnabled: Boolean,
//    @Json(name = "link_flair_position")
//    val linkFlairPosition: String,
    @Json(name = "mobile_banner_image")
    val mobileBannerImage: String,
//    @Json(name = "name")
//    val name: String,
//    @Json(name = "notification_level")
//    val notificationLevel: Any,
//    @Json(name = "original_content_tag_enabled")
//    val originalContentTagEnabled: Boolean,
//    @Json(name = "over18")
//    val over18: Boolean,
//    @Json(name = "prediction_leaderboard_entry_type")
//    val predictionLeaderboardEntryType: String,
//    @Json(name = "primary_color")
//    val primaryColor: String,
    @Json(name = "public_description")
    val publicDescription: String,
//    @Json(name = "public_description_html")
//    val publicDescriptionHtml: String,
//    @Json(name = "public_traffic")
//    val publicTraffic: Boolean,
//    @Json(name = "quarantine")
//    val quarantine: Boolean,
//    @Json(name = "restrict_commenting")
//    val restrictCommenting: Boolean,
//    @Json(name = "restrict_posting")
//    val restrictPosting: Boolean,
//    @Json(name = "should_archive_posts")
//    val shouldArchivePosts: Boolean,
//    @Json(name = "should_show_media_in_comments_setting")
//    val shouldShowMediaInCommentsSetting: Boolean,
//    @Json(name = "show_media")
//    val showMedia: Boolean,
//    @Json(name = "show_media_preview")
//    val showMediaPreview: Boolean,
//    @Json(name = "spoilers_enabled")
//    val spoilersEnabled: Boolean,
//    @Json(name = "submission_type")
//    val submissionType: String,
//    @Json(name = "submit_link_label")
//    val submitLinkLabel: String,
//    @Json(name = "submit_text")
//    val submitText: String,
//    @Json(name = "submit_text_html")
//    val submitTextHtml: Any,
//    @Json(name = "submit_text_label")
//    val submitTextLabel: String,
//    @Json(name = "subreddit_type")
//    val subredditType: String,
    @Json(name = "subscribers")
    val subscribers: Int,
//    @Json(name = "suggested_comment_sort")
//    val suggestedCommentSort: Any,
//    @Json(name = "title")
//    val title: String,
//    @Json(name = "url")
//    val url: String,
//    @Json(name = "user_can_flair_in_sr")
//    val userCanFlairInSr: Any,
//    @Json(name = "user_flair_background_color")
//    val userFlairBackgroundColor: Any,
//    @Json(name = "user_flair_css_class")
//    val userFlairCssClass: Any,
//    @Json(name = "user_flair_enabled_in_sr")
//    val userFlairEnabledInSr: Boolean,
//    @Json(name = "user_flair_position")
//    val userFlairPosition: String,
//    @Json(name = "user_flair_richtext")
//    val userFlairRichtext: List<Any>,
//    @Json(name = "user_flair_template_id")
//    val userFlairTemplateId: Any,
//    @Json(name = "user_flair_text")
//    val userFlairText: Any,
//    @Json(name = "user_flair_text_color")
//    val userFlairTextColor: Any,
//    @Json(name = "user_flair_type")
//    val userFlairType: String,
//    @Json(name = "user_has_favorited")
//    val userHasFavorited: Boolean,
//    @Json(name = "user_is_banned")
//    val userIsBanned: Boolean,
//    @Json(name = "user_is_contributor")
//    val userIsContributor: Boolean,
//    @Json(name = "user_is_moderator")
//    val userIsModerator: Boolean,
//    @Json(name = "user_is_muted")
//    val userIsMuted: Boolean,
    @Json(name = "user_is_subscriber")
    val userIsSubscriber: Boolean,
//    @Json(name = "user_sr_flair_enabled")
//    val userSrFlairEnabled: Any,
//    @Json(name = "user_sr_theme_enabled")
//    val userSrThemeEnabled: Boolean,
//    @Json(name = "videostream_links_count")
//    val videostreamLinksCount: Int,
//    @Json(name = "whitelist_status")
//    val whitelistStatus: Any,
//    @Json(name = "wiki_enabled")
//    val wikiEnabled: Any,
//    @Json(name = "wls")
//    val wls: Any
) : MapData {
    
    override fun map(): SubredditEntity {
        return SubredditEntity(
            id = id,
            displayNamePrefixed = displayNamePrefixed,
            displayName = displayName,
            subscribers = subscribers.formatDecimalSeparator(),
            userIsSubscriber = userIsSubscriber,
            publicDescription = publicDescription,
            bannerImage = bannerImg ?: "",
            bannerColor = bannerBackgroundColor,
            subredditIcon = iconImg
        )
    }
}