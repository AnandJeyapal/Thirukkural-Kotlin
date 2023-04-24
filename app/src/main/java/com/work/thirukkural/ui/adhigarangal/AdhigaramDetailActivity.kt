package com.work.thirukkural.ui.adhigarangal

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.work.thirukkural.R
import com.work.thirukkural.adapter.AdhigaramDetailAdapter
import com.work.thirukkural.adapter.KuralClickListener
import com.work.thirukkural.data.entities.Kural
import com.work.thirukkural.databinding.ActivityAdhigaramDetailBinding
import com.work.thirukkural.ui.kural.KuralActivity
import com.work.thirukkural.utils.getColorToUpdate
import com.work.thirukkural.utils.getDarkColorToUpdate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdhigaramDetailActivity : AppCompatActivity(), KuralClickListener {
    private lateinit var binding: ActivityAdhigaramDetailBinding
    private lateinit var markFavorite: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdhigaramDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val toolbar = binding.toolbar
        toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        val adhigaramName = intent.getStringExtra("adhigaramName")
        val adhigaramId = intent.getIntExtra("adhigaramId", 1)
        val start = intent.getIntExtra("start", 1)
        val end = intent.getIntExtra("end", 10)


        supportActionBar?.title = adhigaramName

        val adhigaramDetailViewModel by viewModels<AdhigaramDetailViewModel>()

        val collapsingToolbarLayout = binding.collapsingToolbar
        val color = getColorToUpdate(adhigaramId, resources)
        collapsingToolbarLayout.setContentScrimColor(color)

        val container = binding.primaryColorView
        container.setBackgroundColor(color)

        val kuralList = binding.kuralList
        kuralList.layoutManager = LinearLayoutManager(this)
        val adapter = AdhigaramDetailAdapter(emptyList(), this)
        kuralList.adapter = adapter

        markFavorite = binding.favouriteButton
        markFavorite.setOnClickListener {
            markFavorite.startAnimation(
                AnimationUtils.loadAnimation(applicationContext, R.anim.rotate_button)
            )
            adhigaramDetailViewModel.onFavoriteClicked(adhigaramId) }
        markFavorite.backgroundTintList = ColorStateList.valueOf(color)

        window.statusBarColor = getDarkColorToUpdate(adhigaramId, resources)

        adhigaramDetailViewModel.fetchKurals(start, end)
        adhigaramDetailViewModel.fetchAdhigaram(adhigaramId)

        adhigaramDetailViewModel.adhigaram.observe(this) {
            updateFavorite(it.favorite)
        }

        adhigaramDetailViewModel.statusMessage.observe(this) {
            if (it.isNotBlank()) {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }

        adhigaramDetailViewModel.kurals.observe(this) {
            adapter.kurals = it
            adapter.notifyDataSetChanged()
        }

    }

    private fun updateFavorite(favorite: Int? = 0) {
        markFavorite.setImageResource(if (favorite == 1) R.drawable.ic_star else R.drawable.ic_not_favorite)
    }

    override fun onKuralClicked(kural: Kural) {
        val intent = Intent(this, KuralActivity::class.java)
        val extras = bundleOf("kuralId" to kural.id)
        intent.putExtras(extras)
        startActivity(intent)
    }

}