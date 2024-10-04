package com.zainco.trellapremierleague.di

import android.content.Context
import com.zainco.trellapremierleague.BuildConfig
import com.zainco.trellapremierleague.base.network.NetworkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        networkManager: NetworkManager,
    ): OkHttpClient {
        val httpLogging = HttpLoggingInterceptor()
        httpLogging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        if (BuildConfig.DEBUG)
            httpClient.addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        httpClient.addInterceptor(Interceptor { chain ->
            val originalResponse = chain.proceed(chain.request())
            if (networkManager.isNetworkConnected()) {
                val maxAge = 60
                return@Interceptor originalResponse.newBuilder()
                    .header("Cache-Control", "public, max-age=$maxAge")
                    .build()
            } else {
                val maxStale = 60 * 60 * 24 * 1

                return@Interceptor originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                    .build()
            }
        })

            .connectTimeout(5, TimeUnit.MINUTES)
            .callTimeout(5, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
        httpClient.addInterceptor(httpLogging)
        val httpCacheDirectory = File(context.cacheDir, "responses")
        val cacheSize: Long = 10 * 1024 * 1024
        val cache = Cache(httpCacheDirectory, cacheSize)
        httpClient.cache(cache)
        return httpClient.build()
    }

}