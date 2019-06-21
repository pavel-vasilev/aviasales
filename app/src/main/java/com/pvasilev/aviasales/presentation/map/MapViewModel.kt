package com.pvasilev.aviasales.presentation.map

import com.airbnb.mvrx.FragmentViewModelContext
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.pvasilev.aviasales.domain.GetPlanePositionUseCase
import com.pvasilev.aviasales.domain.GetPointsOnPathUseCase
import com.pvasilev.aviasales.presentation.base.BaseMvRxViewModel
import com.pvasilev.aviasales.presentation.base.ViewModelFactory
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import ru.terrakok.cicerone.Router

class MapViewModel @AssistedInject constructor(
    @Assisted initialState: MapState,
    private val router: Router,
    getPlanePosition: GetPlanePositionUseCase,
    getPointsOnPath: GetPointsOnPathUseCase
) : BaseMvRxViewModel<MapState>(initialState) {

    init {
        val pointsOnPath = getPointsOnPath(GetPointsOnPathUseCase.Params(initialState.locationFrom, initialState.locationTo, 100))
        setState {
            copy(pointsOnPath = pointsOnPath)
        }
        getPlanePosition(GetPlanePositionUseCase.Params(initialState.locationFrom, initialState.locationTo))
            .subscribe {
                setState {
                    copy(planeLocation = it)
                }
            }
            .disposeOnClear()
    }

    fun onBackPressed() {
        router.exit()
    }

    @AssistedInject.Factory
    interface Factory : ViewModelFactory<MapViewModel, MapState>

    companion object : MvRxViewModelFactory<MapViewModel, MapState> {
        override fun create(viewModelContext: ViewModelContext, state: MapState): MapViewModel {
            val factory = (viewModelContext as FragmentViewModelContext).fragment<MapFragment>().viewModelFactory
            return factory.create(state)
        }
    }
}