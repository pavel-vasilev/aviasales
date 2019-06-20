package com.pvasilev.aviasales.domain

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.SphericalUtil
import com.pvasilev.aviasales.data.repository.TicketRepository
import io.reactivex.Observable

class GetPlanePositionUseCase(private val repository: TicketRepository) {
    operator fun invoke(params: Params): Observable<LatLng> {
        val (from, to) = params
        return repository.getTicketsProgress()
            .map { SphericalUtil.interpolate(from, to, it.toDouble()) }
    }

    data class Params(val from: LatLng, val to: LatLng)
}