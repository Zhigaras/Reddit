package com.zhigaras.reddit.domain

import android.content.Intent
import android.util.Log
import androidx.core.net.toUri
import net.openid.appauth.*
import kotlin.coroutines.suspendCoroutine

object AppAuth {
    
    private val serviceConfig = AuthorizationServiceConfiguration(
        AuthConfig.AUTH_URI.toUri(),
        AuthConfig.TOKEN_URI.toUri(),
        null,
        AuthConfig.LOGOUT_URL.toUri()
    )
    
    fun getAuthorizationRequest(): AuthorizationRequest =
        AuthorizationRequest.Builder(
            serviceConfig,
            AuthConfig.CLIENT_ID,
            AuthConfig.RESPONSE_TYPE,
            AuthConfig.REDIRECT_URL.toUri()
        ).setScopes(AuthConfig.SCOPES)
            .setAdditionalParameters(mapOf("duration" to "permanent"))
            .setState(AuthConfig.STATE)
            .build()
    
    fun getLogoutRequest(token: String?): EndSessionRequest {
        return EndSessionRequest.Builder(serviceConfig)
            .setPostLogoutRedirectUri(AuthConfig.REDIRECT_URL.toUri())
            .setIdTokenHint(token)
            .build()
    }
    
    fun prepareTokenRequest(intent: Intent): TokenRequest {
        val code = intent.dataString?.substringAfter("code=")?.substringBefore('#')
        Log.d("AAA code", code.toString())
        return TokenRequest.Builder(serviceConfig, AuthConfig.CLIENT_ID)
            .setGrantType(AuthConfig.GRANT_TYPE_CODE)
            .setAuthorizationCode(code)
            .setRedirectUri(AuthConfig.REDIRECT_URL.toUri())
            .build()
    }
    
    suspend fun performTokenRequestSuspend(
        authService: AuthorizationService,
        tokenRequest: TokenRequest,
    ): String {
        return suspendCoroutine { continuation ->
            authService.performTokenRequest(
                tokenRequest,
                ClientSecretBasic(AuthConfig.CLIENT_SECRET)
            ) { response, ex ->
                when {
                    response != null -> {
                        val token = response.accessToken.orEmpty()
                        continuation.resumeWith(Result.success(token))
                    }
                    ex != null -> {
                        continuation.resumeWith(Result.failure(ex))
                    }
                    else -> error("unreachable")
                }
            }
        }
    }
    
    object AuthConfig {
        const val AUTH_URI = "https://www.reddit.com/api/v1/authorize.compact"
        const val TOKEN_URI = "https://www.reddit.com/api/v1/access_token"
        const val RESPONSE_TYPE = ResponseTypeValues.CODE
        const val SCOPES =
            "identity edit flair history modconfig modflair modlog modposts modwiki mysubreddits" +
                    " privatemessages read report save submit subscribe vote wikiedit wikiread"
        const val CLIENT_ID = "9b0TycXlwOmCMLWMoInMfg"
        const val CLIENT_SECRET = ""
        const val REDIRECT_URL = "com.zhigaras.reddit://reddit"
        const val LOGOUT_URL = ""
        const val STATE = "com.zhigaras.reddit:state"
        const val GRANT_TYPE_CODE = "authorization_code"
        const val GRANT_TYPE_REFRESH = "refresh_token"
    }
}