package com.pvasilev.aviasales.presentation.map

import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.pvasilev.aviasales.data.repository.TicketRepository
import com.pvasilev.aviasales.domain.GetPlanePositionUseCase
import com.pvasilev.aviasales.presentation.base.BaseMvRxViewModel

class MapViewModel(
    initialState: MapState,
    getPlanePosition: GetPlanePositionUseCase
) : BaseMvRxViewModel<MapState>(initialState) {

    init {
        getPlanePosition(GetPlanePositionUseCase.Params(initialState.locationFrom, initialState.locationTo))
            .subscribe {
                setState {
                    copy(planeLocation = it)
                }
            }
            .disposeOnClear()
    }

    companion object : MvRxViewModelFactory<MapViewModel, MapState> {
        override fun create(viewModelContext: ViewModelContext, state: MapState): MapViewModel {
            return MapViewModel(
                state,
                GetPlanePositionUseCase(TicketRepository())
            )
        }
    }
}