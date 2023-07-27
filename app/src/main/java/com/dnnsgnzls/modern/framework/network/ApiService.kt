package com.dnnsgnzls.modern.framework.network

import com.dnnsgnzls.modern.BuildConfig
import com.dnnsgnzls.modern.framework.constants.Constants
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    private fun getRetrofit(): Retrofit {
        val clientInterceptor = createClientInterceptor()
        val loggerInterceptor = createLoggerInterceptor()

        val client = OkHttpClient
            .Builder()
            .addInterceptor(clientInterceptor)
            .addInterceptor(loggerInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    private fun createClientInterceptor() = Interceptor { chain ->
        val apiKey = BuildConfig.API_KEY

        var request: Request = chain.request()
        val url: HttpUrl = request.url.newBuilder()
            .addQueryParameter("key", apiKey)
            .build()

        request = request.newBuilder().url(url).build()
        chain.proceed(request)
    }

    private fun createLoggerInterceptor(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG) logger.level = HttpLoggingInterceptor.Level.BASIC
        else logger.level = HttpLoggingInterceptor.Level.NONE

        return logger
    }

    val api: RawgApi = getRetrofit().create(RawgApi::class.java)
}