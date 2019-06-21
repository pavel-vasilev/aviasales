package com.pvasilev.aviasales.di

import com.pvasilev.aviasales.data.repository.AutocompleteService
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import toothpick.config.Module

class AppModule : Module() {
    init {
        bind(OkHttpClient::class.java).toProvider(OkHttpClientProvider::class.java)
        bind(Moshi::class.java).toProvider(MoshiProvider::class.java)
        bind(Retrofit::class.java).toProvider(RetrofitProvider::class.java)
        bind(AutocompleteService::class.java).toProvider(AutocompleteServiceProvider::class.java)
    }
}