package com.pvasilev.aviasales.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.pvasilev.aviasales.R
import com.pvasilev.aviasales.presentation.CitiesAdapter
import com.pvasilev.aviasales.presentation.DividerItemDecoration
import com.pvasilev.aviasales.presentation.OnBackPressedListener
import com.pvasilev.aviasales.presentation.inject
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : BaseMvRxFragment(), OnBackPressedListener {
    val viewModelFactory: SearchViewModel.Factory by inject("SearchScope")

    private val searchViewModel: SearchViewModel by fragmentViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_search, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView.adapter = CitiesAdapter {
            searchViewModel.onCitySelected(it)
        }
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(DividerItemDecoration())
    }

    override fun invalidate() = withState(searchViewModel) { state ->
        val cities = state.cities
        if (cities is Success) {
            (recyclerView.adapter as CitiesAdapter).submitList(cities())
        }
    }

    override fun onBackPressed(): Boolean {
        searchViewModel.onBackPressed()
        return true
    }
}