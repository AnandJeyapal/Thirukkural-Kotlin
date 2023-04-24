package com.work.thirukkural.ui.favorites.ui.kurals

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.work.thirukkural.adapter.AdhigaramDetailAdapter
import com.work.thirukkural.adapter.KuralClickListener
import com.work.thirukkural.data.entities.Kural
import com.work.thirukkural.databinding.FragmentFavoriteKuralsBinding
import com.work.thirukkural.ui.kural.KuralActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteKuralsFragment : Fragment(), KuralClickListener {

    private var _binding: FragmentFavoriteKuralsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val favoriteKuralsViewModel by viewModels<FavoriteKuralsViewModel>()

        _binding = FragmentFavoriteKuralsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val favoriteKuralsList = binding.favoriteKurals
        favoriteKuralsList.layoutManager = LinearLayoutManager(requireActivity())
        val adapter = AdhigaramDetailAdapter(emptyList(), this)
        favoriteKuralsList.adapter = adapter

        favoriteKuralsViewModel.favoriteKurals.observe(viewLifecycleOwner) {
            adapter.kurals = it
            adapter.notifyDataSetChanged()
        }
        favoriteKuralsViewModel.fetchFavoriteKurals()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onKuralClicked(kural: Kural) {
        val intent = Intent(requireActivity(), KuralActivity::class.java)
        val extras = bundleOf("kuralId" to kural.id)
        intent.putExtras(extras)
        startActivity(intent)
    }
}