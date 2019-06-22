package com.pvasilev.aviasales.presentation.map

import com.airbnb.mvrx.FragmentViewModelContext
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.pvasilev.aviasales.domain.GetPlaneOnMapUseCase
import com.pvasilev.aviasales.domain.GetPointsOnPathUseCase
import com.pvasilev.aviasales.presentation.base.BaseMvRxViewModel
import com.pvasilev.aviasales.presentation.base.ViewModelFactory
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import ru.terrakok.cicerone.Router

class MapViewModel @AssistedInject constructor(
    @Assisted initialState: MapState,
    private val router: Router,
    getPlaneOnMap: GetPlaneOnMapUseCase,
    getPointsOnPath: GetPointsOnPathUseCase
) : BaseMvRxViewModel<MapState>(initialState) {

    init {
        val pointsOnPath = getPointsOnPath(GetPointsOnPathUseCase.Params(initialState.locationFrom, initialState.locationTo, 100))
        setState {
            copy(pointsOnPath = pointsOnPath)
        }
        getPlaneOnMap(GetPlaneOnMapUseCase.Params(initialState.locationFrom, initialState.locationTo))
            .subscribe { (location, rotation) ->
                setState {
                    copy(planeLocation = location, planeRotation = rotation)
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