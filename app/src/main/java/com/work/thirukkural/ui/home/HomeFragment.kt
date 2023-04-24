package com.work.thirukkural.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.work.thirukkural.MainActivity
import com.work.thirukkural.R
import com.work.thirukkural.databinding.FragmentHomeBinding
import com.work.thirukkural.utils.shareMoreContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val moreAboutThirukural = binding.homeCard.moreButton
        val moreAboutSalamon = binding.spCard.moreButton

        val shareAboutThirukural = binding.homeCard.shareAboutThirukkural
        val shareAboutPerumaigal = binding.spCard.shareAboutSalamon

        moreAboutThirukural.setOnClickListener {
            val action = HomeFragmentDirections.homeToMore(1, getString(R.string.app_name))
            findNavController().navigate(action)
        }
        moreAboutSalamon.setOnClickListener {
            val action = HomeFragmentDirections.homeToMore(2, getString(R.string.thirukkural_second))
            findNavController().navigate(action)
        }

        shareAboutThirukural.setOnClickListener {
            shareMoreContent(1, requireContext())
        }
        shareAboutPerumaigal.setOnClickListener {
            shareMoreContent(2, requireContext())
        }
        val fab = binding.homeButton
        fab.setOnClickListener { (requireActivity() as MainActivity).openNavDrawer(it) }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}