package com.zhigaras.reddit.data

import com.zhigaras.reddit.data.locale.DataStoreManager
import javax.inject.Inject

interface MainRepository {
    
    suspend fun saveAccessToken(token: String)
    
    suspend fun checkAccessToken(): Boolean
    
    class Base @Inject constructor(
        private val dataStoreManager: DataStoreManager.Base
    ): MainRepository {
        override suspend fun saveAccessToken(token: String) {
            dataStoreManager.saveToken(token)
        }
    
        override suspend fun checkAccessToken(): Boolean {
            return dataStoreManager.checkToken()
        }
    }
}