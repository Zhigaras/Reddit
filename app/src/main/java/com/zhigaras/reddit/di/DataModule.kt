package com.zhigaras.reddit.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.zhigaras.reddit.data.locale.DataStoreManager
import com.zhigaras.reddit.data.remote.AuthInterceptor
import com.zhigaras.reddit.data.remote.RedditApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.openid.appauth.AuthorizationService
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
    fun providesPreferencesDataStore(@ApplicationContext app: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { app.preferencesDataStoreFile(DataStoreManager.Base.PREFERENCES_STORE_NAME) }
        )
    }
    
    @Provides
    @Singleton
    fun providesOkhttpClient(dataStoreManager: DataStoreManager.Base): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(dataStoreManager))
            .addInterceptor(HttpLoggingInterceptor().also {
                it.level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }
    
    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
//            .add(
//                PolymorphicJsonAdapterFactory.of(TestDto::class.java, "kind")
//                    .withSubtype(SubredditsData::class.java, "t5")
//                    .withSubtype(PostsData::class.java, "t3")
//            )
            .add(KotlinJsonAdapterFactory())
            .build()
    }
    
    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): RedditApi {
        return Retrofit.Builder()
            .baseUrl(RedditApi.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(RedditApi::class.java)
    }
    
    @Provides
    fun providesAuthorizationService(@ApplicationContext app: Context): AuthorizationService {
        return AuthorizationService(app)
    }
}