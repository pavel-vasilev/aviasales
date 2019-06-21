package com.pvasilev.aviasales.domain

import com.pvasilev.aviasales.data.models.City
import com.pvasilev.aviasales.data.repository.CityRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetCitiesUseCase(private val repository: CityRepository) {
    operator fun invoke(params: Params): Single<List<City>> {
        return repository.getCities(params.term)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    data class Params(val term: String)
}