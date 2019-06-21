package com.pvasilev.aviasales.data.repository

import com.pvasilev.aviasales.data.models.City
import io.reactivex.Single

class CityRepository (private val autocompleteService: AutocompleteService) {
    fun getCities(term: String): Single<List<City>> {
        return autocompleteService.getCities(term)
    }
}