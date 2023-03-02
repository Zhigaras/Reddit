package com.zhigaras.reddit.di

import com.zhigaras.reddit.data.RedditApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {
    
    @Provides
    @Singleton
    fun providesRetrofit(): RedditApi {
        return Retrofit.Builder()
            .baseUrl(RedditApi.BASE_URL)
            .client(OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().also {
                    it.level = HttpLoggingInterceptor.Level.BODY
                }).build()
            )
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(RedditApi::class.java)
    }
}