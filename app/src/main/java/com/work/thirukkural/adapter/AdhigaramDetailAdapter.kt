package com.work.thirukkural.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.work.thirukkural.data.entities.Kural
import com.work.thirukkural.databinding.RowKuralBinding

class AdhigaramDetailAdapter(var kurals: List<Kural>, val kuralClickListener: KuralClickListener)
    : RecyclerView.Adapter<AdhigaramDetailAdapter.AdhigaramDetailViewHolder>() {


    class AdhigaramDetailViewHolder(val rowKuralBinding: RowKuralBinding) : RecyclerView.ViewHolder(rowKuralBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdhigaramDetailViewHolder {
        val binding = RowKuralBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdhigaramDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdhigaramDetailViewHolder, position: Int) {
        with(holder) {
            with(kurals[position]) {
                rowKuralBinding.kuralTextView.text = kural
                rowKuralBinding.root.setOnClickListener {
                    kuralClickListener.onKuralClicked(this)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return kurals.size
    }

}