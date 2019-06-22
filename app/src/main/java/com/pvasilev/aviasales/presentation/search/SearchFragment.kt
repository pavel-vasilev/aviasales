package com.pvasilev.aviasales.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.mvrx.*
import com.pvasilev.aviasales.R
import com.pvasilev.aviasales.presentation.OnBackPressedListener
import com.pvasilev.aviasales.presentation.adapter.CitiesAdapter
import com.pvasilev.aviasales.presentation.adapter.DividerItemDecoration
import com.pvasilev.aviasales.presentation.inject
import com.pvasilev.aviasales.presentation.setOnActionCollapseListener
import com.pvasilev.aviasales.presentation.setOnQueryTextChangeListener
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : BaseMvRxFragment(), OnBackPressedListener {
    val viewModelFactory: SearchViewModel.Factory by inject("SearchScope")

    private val searchViewModel: SearchViewModel by fragmentViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchViewModel.selectSubscribe(SearchState::request, UniqueOnly("request")) {
            if (it is Fail) {
                Toast.makeText(requireContext(), R.string.error_network, Toast.LENGTH_SHORT).show()
            }
        }
    }

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
        (recyclerView.adapter as CitiesAdapter).submitList(state.cities)
    }

    override fun onBackPressed(): Boolean {
        searchViewModel.onBackPressed()
        return true
    }
}