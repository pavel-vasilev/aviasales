package com.pvasilev.aviasales.data.repository

import com.pvasilev.aviasales.data.models.City
import com.serjltt.moshi.adapters.Wrapped
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface AutocompleteService {
    @GET("autocomplete")
    @Wrapped(path = ["cities"])
    fun getCities(@Query("term") term: String, @Query("lang") lang: String = "en"): Single<List<City>>
}