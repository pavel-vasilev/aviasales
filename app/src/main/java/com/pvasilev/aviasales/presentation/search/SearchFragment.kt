package com.pvasilev.aviasales.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.mvrx.BaseMvRxFragment
import com.pvasilev.aviasales.R
import com.pvasilev.aviasales.presentation.CitiesAdapter
import com.pvasilev.aviasales.presentation.DividerItemDecoration
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : BaseMvRxFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_search, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView.adapter = CitiesAdapter()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(DividerItemDecoration())
    }

    override fun invalidate() {
    }
}