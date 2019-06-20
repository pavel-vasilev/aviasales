package com.pvasilev.aviasales.di

import com.pvasilev.aviasales.BuildConfig
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Provider

class RetrofitProvider @Inject constructor(
    private val client: OkHttpClient,
    private val moshi: Moshi
) : Provider<Retrofit> {
    override fun get(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_ADDRESS)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
}