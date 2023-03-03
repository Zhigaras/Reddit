package com.zhigaras.reddit.presentation.viewModels

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhigaras.reddit.domain.AppAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationService
import net.openid.appauth.TokenRequest
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    @ApplicationContext app: Context,
    
    ) : ViewModel() {
    
    private val authService = AuthorizationService(app)
    
    fun handleAuthResponseIntent(intent: Intent) {
        val exception = AuthorizationException.fromIntent(intent)
        val tokenExchangeRequest = AppAuth.prepareTokenRequest(intent)
        
        viewModelScope.launch {
            if (exception != null) {
                Log.d("AAA", exception.message ?: "authResponse handle exception")
            } else {
                onAuthCodeReceived(tokenExchangeRequest)
            }
        }
    }
    
    suspend fun onAuthCodeReceived(tokenExchangeRequest: TokenRequest) {
        runCatching {
            AppAuth.performTokenRequestSuspend(
                authService = authService,
                tokenRequest = tokenExchangeRequest
            )
        }.onSuccess {
//            mainRepository.saveAccessToken(it)
            Log.d("AAA token", it)
//            _authSuccessEventChannel.send(Unit)
        }.onFailure {
            Log.d("AAA auth code receive", it.message ?: "authCode receiving exception")
//            _toastEventChannel.send(R.string.auth_cancel)
        }
    }
    
    fun prepareAuthPageIntent(openAuthPage: (Intent) -> Unit) {
        val customTabsIntent = CustomTabsIntent.Builder().build()
        val authRequest = AppAuth.getAuthorizationRequest()
        
        val authPageIntent = authService.getAuthorizationRequestIntent(
            authRequest,
            customTabsIntent
        )
        openAuthPage(authPageIntent)
    }
}