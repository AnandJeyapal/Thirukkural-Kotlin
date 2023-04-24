package com.work.thirukkural.adapter

import com.work.thirukkural.data.entities.Kural

interface KuralClickListener {
    fun onKuralClicked(kural: Kural)
}