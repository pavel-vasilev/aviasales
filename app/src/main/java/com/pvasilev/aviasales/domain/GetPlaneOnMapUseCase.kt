package com.pvasilev.aviasales.domain

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.SphericalUtil
import com.pvasilev.aviasales.data.repository.TicketRepository
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class GetPlaneOnMapUseCase @Inject constructor(private val repository: TicketRepository) {
    operator fun invoke(params: Params): Observable<Pair<LatLng, Float>> {
        val (from, to) = params
        val location = repository.getTicketsProgress()
            .map { SphericalUtil.interpolate(from, to, it.toDouble()) }
        val rotation = location.buffer(2)
            .map { ((SphericalUtil.computeHeading(it[0], it[1])) - 90).toFloat() }
        return Observable.combineLatest(
            location,
            rotation,
            BiFunction<LatLng, Float, Pair<LatLng, Float>> { t1, t2 -> Pair(t1, t2) }
        )
    }

    data class Params(val from: LatLng, val to: LatLng)
}