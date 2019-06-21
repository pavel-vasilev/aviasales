package com.pvasilev.aviasales.presentation.search

import com.pvasilev.aviasales.domain.GetCitiesUseCase
import com.pvasilev.aviasales.presentation.base.BaseMvRxViewModel
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class SearchViewModel(
    initialState: SearchState,
    private val getCities: GetCitiesUseCase
) : BaseMvRxViewModel<SearchState>(initialState) {
    private val term = PublishSubject.create<String>()

    init {
        term.debounce(300, TimeUnit.MILLISECONDS)
            .flatMapSingle { getCities(GetCitiesUseCase.Params(it)) }
            .subscribe()
            .disposeOnClear()
    }

    fun onTermChanged(term: String) {
        this.term.onNext(term)
    }
}