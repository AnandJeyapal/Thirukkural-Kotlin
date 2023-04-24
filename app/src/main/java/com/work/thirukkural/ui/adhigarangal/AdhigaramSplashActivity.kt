package com.work.thirukkural.ui.adhigarangal

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.work.thirukkural.databinding.ActivityAdhigaramSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdhigaramSplashActivity: AppCompatActivity() {
    private lateinit var binding: ActivityAdhigaramSplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdhigaramSplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adhigaramName = intent.getStringExtra("adhigaramName")
        val adhigaramId = intent.getIntExtra("adhigaramId", 1)

        val adhigaramNameText = binding.adhigaramName
        adhigaramNameText.text = adhigaramName
        val container = binding.container
        val color = com.work.thirukkural.utils.getColorToUpdate(adhigaramId, resources)
        container.setBackgroundColor(color)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, AdhigaramDetailActivity::class.java)
            intent.putExtras(getIntent().extras ?: Bundle())
            startActivity(intent)
            finish()
        }, 1000)
    }

}