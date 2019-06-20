package com.pvasilev.aviasales.data.repository

import android.animation.ValueAnimator
import io.reactivex.Observable

class TicketRepository {
    fun getTicketsProgress(): Observable<Float> {
        return Observable.create { emitter ->
            val animator = ValueAnimator.ofFloat(0.0F, 1.0F)
            animator.duration = 20_000
            animator.addUpdateListener {
                emitter.onNext(it.animatedFraction)
            }
            emitter.setCancellable {
                animator.cancel()
            }
            animator.start()
        }
    }
}