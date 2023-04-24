package com.work.thirukkural.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.work.thirukkural.R
import com.work.thirukkural.data.entities.Kural

class AdhigaramDetailAdapter(var kurals: List<Kural>, val kuralClickListener: KuralClickListener)
    : RecyclerView.Adapter<AdhigaramDetailAdapter.ViewHolder>() {



    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView
        init {
            nameTextView = view.findViewById(R.id.kural_text_view)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_kural, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener{
            kuralClickListener.onKuralClicked(kurals[position])
        }
        holder.nameTextView.text = kurals[position].kural
    }

    override fun getItemCount(): Int {
        return kurals.size
    }

}