package com.work.thirukkural.ui.favorites.ui.adhigarams

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.work.thirukkural.R
import com.work.thirukkural.adapter.AdhigaramClickListener
import com.work.thirukkural.adapter.FavoriteAdhigaramAdapter
import com.work.thirukkural.data.entities.Adhigaram
import com.work.thirukkural.databinding.FragmentFavoriteAdhigaramsBinding
import com.work.thirukkural.ui.adhigarangal.AdhigaramSplashActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteAdhigaramsFragment : Fragment(), AdhigaramClickListener {

    private var _binding: FragmentFavoriteAdhigaramsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val favoriteAdhigaramsViewModel by viewModels<FavoriteAdhigaramsViewModel>()

        _binding = FragmentFavoriteAdhigaramsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val favoriteAdhigaramsGrid = binding.favoriteAdhigarams
        favoriteAdhigaramsGrid.layoutManager = GridLayoutManager(requireActivity(), 2)
        val adapter = FavoriteAdhigaramAdapter(emptyList(), this)
        favoriteAdhigaramsGrid.adapter = adapter
//        favoriteAdhigaramsGrid.addItemDecoration(decoration)

        favoriteAdhigaramsViewModel.favoriteAdhigarams.observe(viewLifecycleOwner) {
            adapter.adhigarams = it
            adapter.notifyDataSetChanged()
        }
        favoriteAdhigaramsViewModel.fetchFavoriteAdhigarams()
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
}