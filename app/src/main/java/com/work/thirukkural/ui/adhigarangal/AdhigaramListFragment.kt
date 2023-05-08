package com.work.thirukkural.ui.adhigarangal

import android.os.Bundle
import android.text.InputType
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.work.thirukkural.R
import com.work.thirukkural.adapter.AdhigaramPageAdapter
import com.work.thirukkural.databinding.FragmentAdhigarangalBinding
import com.work.thirukkural.ui.paal.PaalFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdhigaramListFragment : Fragment() {

    private lateinit var adhigaramPager: ViewPager2
    private lateinit var adhigaramPageAdapter: AdhigaramPageAdapter
    private lateinit var searchView: SearchView
    private var _binding: FragmentAdhigarangalBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    companion object {
        const val ARAM_FRAGMENT = 0
        const val PORUL_FRAGMENT = 1
        const val INBAM_FRAGMENT = 2
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAdhigarangalBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val tabLayout = binding.tabLayout
        initTabLayout(tabLayout)

        // TODO (ajeyapal) fix back pressed

        adhigaramPager = binding.adhigaramViewPager
        initPager(adhigaramPager)
        val tabTitles = arrayOf(R.string.aram, R.string.porul, R.string.inbam)
        TabLayoutMediator(tabLayout, adhigaramPager) { tab, position ->
            tab.text = getString(tabTitles[position])
        }.attach()

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.menu_adhigaram_list, menu)
                val searchItem = menu?.findItem(R.id.action_search)
                searchView = searchItem?.actionView as SearchView
                searchView.queryHint = "அதிகார எண்"
                searchView.inputType = InputType.TYPE_CLASS_NUMBER
                searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return false
                    }

                    override fun onQueryTextChange(query: String?): Boolean {
                        if(!query.isNullOrEmpty()) {
                            doSearch(query)
                        } else {
                            resetPage();
                        }
                        return false
                    }
                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }
        },viewLifecycleOwner, Lifecycle.State.RESUMED)
        return root
    }

    private fun doSearch(query: String) {
        val adhigaramNumber = query.toInt()
        val currentPage = adhigaramPager.currentItem
        if(adhigaramNumber <= 133) {
            if(adhigaramNumber <= 38 && currentPage != ARAM_FRAGMENT) {
                adhigaramPager.currentItem = ARAM_FRAGMENT
            } else if(adhigaramNumber in 39..109 && currentPage != PORUL_FRAGMENT) {
                adhigaramPager.currentItem = PORUL_FRAGMENT
            } else if(adhigaramNumber > 109 && currentPage != INBAM_FRAGMENT) {
                adhigaramPager.currentItem = INBAM_FRAGMENT
            }
            updateAdhigarams(query, adhigaramPager.currentItem)
        }
    }

    private fun resetPage() {
        val id = adhigaramPageAdapter.getItemId(adhigaramPager.currentItem)
        val paalFragment = childFragmentManager.findFragmentByTag("f$id") as PaalFragment
        paalFragment.resetAdapter()
    }

    private fun updateAdhigarams(adhigaramNumber: String, part: Int) {
        val id = adhigaramPageAdapter.getItemId(part)
        var paalFragment = childFragmentManager.findFragmentByTag("f$id") as PaalFragment
        paalFragment.doSearch(adhigaramNumber)
    }

    private fun initPager(adhigaramPager: ViewPager2) {
        adhigaramPageAdapter = AdhigaramPageAdapter(childFragmentManager, lifecycle)
        adhigaramPager.adapter = adhigaramPageAdapter
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