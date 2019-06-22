package com.pvasilev.aviasales.domain

import com.google.maps.android.SphericalUtil
import io.reactivex.Observable
import javax.inject.Inject

class GetPlaneRotationUseCase @Inject constructor(private val getPlanePosition: GetPlanePositionUseCase) {
    operator fun invoke(params: GetPlanePositionUseCase.Params): Observable<Float> {
        return getPlanePosition(params).buffer(2)
            .map { ((SphericalUtil.computeHeading(it[0], it[1])) - 90).toFloat() }
    }
}