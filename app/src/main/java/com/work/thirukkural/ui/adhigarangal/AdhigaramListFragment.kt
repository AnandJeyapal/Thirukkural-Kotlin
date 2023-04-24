package com.work.thirukkural.ui.adhigarangal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.work.thirukkural.R
import com.work.thirukkural.adapter.AdhigaramPageAdapter
import com.work.thirukkural.databinding.FragmentAdhigarangalBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdhigaramListFragment : Fragment() {

    private var _binding: FragmentAdhigarangalBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAdhigarangalBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val tabLayout = binding.tabLayout
        initTabLayout(tabLayout)

        val adhigaramPager = binding.adhigaramViewPager
        initPager(adhigaramPager)
        val tabTitles = arrayOf(R.string.aram, R.string.porul, R.string.inbam)
        TabLayoutMediator(tabLayout, adhigaramPager) { tab, position ->
            tab.text = getString(tabTitles[position])
        }.attach()
        return root
    }

    private fun initPager(adhigaramPager: ViewPager2) {
        val adapter = AdhigaramPageAdapter(requireActivity().supportFragmentManager, lifecycle)
        adhigaramPager.adapter = adapter
    }

    private fun initTabLayout(tabLayout: TabLayout) {
        val aramTab = tabLayout.newTab()
        aramTab.text = getString(R.string.aram)
        val porulTab = tabLayout.newTab()
        porulTab.text = getString(R.string.porul)
        val inbamTab = tabLayout.newTab()
        inbamTab.text = getString(R.string.inbam)
        tabLayout.addTab(aramTab)
        tabLayout.addTab(porulTab)
        tabLayout.addTab(inbamTab)
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}