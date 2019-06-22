package com.pvasilev.aviasales.presentation.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.pvasilev.aviasales.R
import com.pvasilev.aviasales.presentation.OnBackPressedListener
import com.pvasilev.aviasales.presentation.addListener
import com.pvasilev.aviasales.presentation.inject
import kotlinx.android.synthetic.main.fragment_location.*

class LocationFragment : BaseMvRxFragment(), OnBackPressedListener {
    val viewModelFactory: LocationViewModel.Factory by inject("AppScope")

    private val viewModel: LocationViewModel by fragmentViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_location, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tv_from.setOnClickListener {
            viewModel.onLocationFromClicked()
        }
        tv_to.setOnClickListener {
            viewModel.onLocationToClicked()
        }
        btn_swap.setOnClickListener {
            viewModel.onSwapClicked()
        }
        btn_search.setOnClickListener {
            viewModel.onSearchClicked()
        }
    }

    override fun invalidate() = withState(viewModel) { state ->
        if (state.cityFrom != null && state.cityTo != null &&
            tv_from.text.isNotEmpty() && tv_to.text.isNotEmpty() &&
            tv_from.text != state.cityFrom && tv_to.text != state.cityTo
        ) {
            val anim1 = createBlinkAnimation {
                tv_from.text = state.cityFrom
            }
            tv_from.startAnimation(anim1)
            val anim2 = createBlinkAnimation {
                tv_to.text = state.cityTo
            }
            tv_to.startAnimation(anim2)
        } else {
            tv_from.text = state.cityFrom ?: resources.getString(R.string.choose_departure)
            tv_to.text = state.cityTo ?: resources.getString(R.string.choose_destination)
        }
        btn_search.isEnabled = state.locationFrom != null && state.locationTo != null
    }

    override fun onBackPressed(): Boolean {
        viewModel.onBackPressed()
        return true
    }

    private fun createBlinkAnimation(onRepeat: (Animation) -> Unit) = AlphaAnimation(1.0F, 0.0F).apply {
        repeatCount = 1
        repeatMode = Animation.REVERSE
        duration = 300
        addListener(onRepeat = onRepeat)
    }
}