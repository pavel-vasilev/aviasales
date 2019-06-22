package com.pvasilev.aviasales.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.pvasilev.aviasales.R
import com.pvasilev.aviasales.presentation.*
import com.pvasilev.aviasales.presentation.adapter.CitiesAdapter
import com.pvasilev.aviasales.presentation.adapter.DividerItemDecoration
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : BaseMvRxFragment(), OnBackPressedListener {
    val viewModelFactory: SearchViewModel.Factory by inject("SearchScope")

    private val searchViewModel: SearchViewModel by fragmentViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_search, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        toolbar.inflateMenu(R.menu.search_menu)
        val menuItem = toolbar.menu.findItem(R.id.action_search)
        val searchView = menuItem.actionView as SearchView
        searchView.maxWidth = Int.MAX_VALUE
        searchView.queryHint = resources.getString(R.string.enter_city)
        menuItem.expandActionView()
        menuItem.setOnActionCollapseListener {
            searchViewModel.onBackPressed()
        }
        searchView.setOnQueryTextChangeListener {
            searchViewModel.onTermChanged(it)
        }
        recyclerView.adapter = CitiesAdapter {
            searchViewModel.onCitySelected(it)
        }
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(DividerItemDecoration(resources.displayMetrics.density * 16))
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