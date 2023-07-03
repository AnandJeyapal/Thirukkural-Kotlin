package com.work.thirukkural.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.work.thirukkural.adapter.AdhigaramClickListener
import com.work.thirukkural.adapter.KuralClickListener
import com.work.thirukkural.adapter.SearchResultsAdapter
import com.work.thirukkural.data.entities.Adhigaram
import com.work.thirukkural.data.entities.Kural
import com.work.thirukkural.databinding.FragmentSearchBinding
import com.work.thirukkural.ui.adhigarangal.AdhigaramSplashActivity
import com.work.thirukkural.ui.kural.KuralActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchResultsFragment : Fragment(), AdhigaramClickListener, KuralClickListener {

    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val searchResultListView = binding.searchResultsList
        searchResultListView.layoutManager = LinearLayoutManager(context)

        val adapter = SearchResultsAdapter(emptyList(), emptyList(), this, this)
        searchResultListView.adapter = adapter

        val searchResultViewModel by viewModels<SearchResultViewModel>()
        searchResultViewModel.adhigarams.observe(viewLifecycleOwner) {
            if(it != null) {
                adapter.adhigarams = it
                adapter.notifyDataSetChanged()
            }
        }
        searchResultViewModel.kurals.observe(viewLifecycleOwner) {
            if(it != null) {
                adapter.kurals = it
                adapter.notifyDataSetChanged()
            }
        }

        val query = arguments?.getString("query", "")
        if(query != null) {
            searchResultViewModel.fetchAdhigarams(query)
            searchResultViewModel.fetchKurals(query)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAdhigaramClicked(adhigaram: Adhigaram) {
        val bundle = bundleOf("adhigaramId" to adhigaram.number, "adhigaramName" to adhigaram.name, "start" to adhigaram.start,
            "end" to adhigaram.end)
        val intent = Intent(requireActivity(), AdhigaramSplashActivity::class.java)
        intent.putExtras(bundle)
        requireActivity().startActivity(intent)
    }

    override fun onKuralClicked(kural: Kural) {
        val intent = Intent(requireActivity(), KuralActivity::class.java)
        val extras = bundleOf("kuralId" to kural.id)
        intent.putExtras(extras)
        startActivity(intent)
    }
}