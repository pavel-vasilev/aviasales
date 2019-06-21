package com.pvasilev.aviasales.presentation.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.pvasilev.aviasales.R
import kotlinx.android.synthetic.main.fragment_location.*

class LocationFragment : BaseMvRxFragment() {
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
        btn_search.setOnClickListener {
            viewModel.onSearchClicked()
        }
    }

    override fun invalidate() = withState(viewModel) { state ->
        tv_from.text = state.cityFrom ?: resources.getString(R.string.choose_departure)
        tv_to.text = state.cityTo ?: resources.getString(R.string.choose_destination)
        btn_search.isEnabled = state.locationFrom != null && state.locationTo != null
    }
}