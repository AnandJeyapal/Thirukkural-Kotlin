package com.work.thirukkural.ui.kural

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.work.thirukkural.R
import com.work.thirukkural.databinding.ActivityKuralBinding
import com.work.thirukkural.ui.dialog.ShareKuralDialogFragment
import com.work.thirukkural.ui.dialog.ShareKuralDialogListener
import com.work.thirukkural.utils.shareKural
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KuralActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKuralBinding
    private lateinit var markFavorite: FloatingActionButton

    private val kuralViewModel by viewModels<KuralViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKuralBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val kuralId = intent.getIntExtra("kuralId", 1)

        val kuralText: TextView = binding.kuralPage.kuralView
        val kuralNumber: TextView = binding.kuralPage.kuralNumber
        val firstExpTextView: TextView = binding.kuralPage.firstExpView
        val secondExpTextView: TextView = binding.kuralPage.secondExpView
        val thirdExpTextView: TextView = binding.kuralPage.thirdExpView

        markFavorite = binding.kuralPage.favoriteButton
        markFavorite.setOnClickListener {
            markFavorite.startAnimation(
                AnimationUtils.loadAnimation(applicationContext, R.anim.rotate_button)
            )
            kuralViewModel.onFavoriteClicked(kuralId)
        }

        kuralViewModel.kural.observe(this) {
            if (it != null) {
                kuralNumber.text = it.id.toString()
                kuralText.text = it.kural
                firstExpTextView.text = it.first_exp
                secondExpTextView.text = it.second_exp
                thirdExpTextView.text = it.third_exp
                updateFavorite(it.favorite)
            }
        }

        kuralViewModel.statusMessage.observe(this) {
            if (it.isNotBlank()) {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }

        kuralViewModel.fetchKural(kuralId)
    }

    private fun updateFavorite(favorite: Int? = 0) {
        markFavorite.setImageResource(if (favorite == 1) R.drawable.ic_star else R.drawable.ic_not_favorite)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_kural_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_share_kural -> showShareDialog()
            else -> super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showShareDialog() {
        ShareKuralDialogFragment(object: ShareKuralDialogListener{
            override fun handlePositiveAction(selectedItems: List<Int>) {
                val kural =kuralViewModel.kural.value
                if(kural != null)
                shareKural(kural, this@KuralActivity, selectedItems)
            }

        }).show(supportFragmentManager, "Share Dialog")
    }

}