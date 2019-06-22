package com.pvasilev.aviasales.presentation.search

import com.airbnb.mvrx.FragmentViewModelContext
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.pvasilev.aviasales.data.models.City
import com.pvasilev.aviasales.domain.GetCitiesUseCase
import com.pvasilev.aviasales.presentation.base.BaseMvRxViewModel
import com.pvasilev.aviasales.presentation.base.ViewModelFactory
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import io.reactivex.subjects.PublishSubject
import ru.terrakok.cicerone.Router
import toothpick.Toothpick
import java.util.concurrent.TimeUnit

class SearchViewModel @AssistedInject constructor(
    @Assisted initialState: SearchState,
    private val router: Router,
    private val getCities: GetCitiesUseCase,
    private val onCitySelectedListener: OnCitySelectedListener
) : BaseMvRxViewModel<SearchState>(initialState) {
    private val term = PublishSubject.create<String>()

    init {
        term.debounce(300, TimeUnit.MILLISECONDS)
            .subscribe {
                getCities(GetCitiesUseCase.Params(it)).execute {
                    copy(cities = it() ?: cities, request = it)
                }
            }
            .disposeOnClear()
    }

    override fun onCleared() {
        super.onCleared()
        Toothpick.closeScope("SearchScope")
    }

    fun onTermChanged(term: String) {
        this.term.onNext(term)
    }

    fun onCitySelected(city: City) {
        onCitySelectedListener.onCitySelected(city)
        onBackPressed()
    }

    fun onBackPressed() {
        router.exit()
    }

    @AssistedInject.Factory
    interface Factory : ViewModelFactory<SearchViewModel, SearchState>

    companion object : MvRxViewModelFactory<SearchViewModel, SearchState> {
        override fun create(viewModelContext: ViewModelContext, state: SearchState): SearchViewModel {
            val factory = (viewModelContext as FragmentViewModelContext).fragment<SearchFragment>().viewModelFactory
            return factory.create(state)
        }
    }
}