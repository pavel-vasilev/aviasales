package com.pvasilev.aviasales.di

import com.pvasilev.aviasales.data.repository.AutocompleteService
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Provider

class AutocompleteServiceProvider @Inject constructor(private val retrofit: Retrofit) : Provider<AutocompleteService> {
    override fun get(): AutocompleteService {
        return retrofit.create(AutocompleteService::class.java)
    }
}