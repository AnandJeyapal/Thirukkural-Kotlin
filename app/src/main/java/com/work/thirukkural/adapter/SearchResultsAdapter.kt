package com.work.thirukkural.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.work.thirukkural.R
import com.work.thirukkural.data.entities.Adhigaram
import com.work.thirukkural.data.entities.Kural
import com.work.thirukkural.databinding.RowSearchResultBinding

class SearchResultsAdapter(var adhigarams: List<Adhigaram>, var kurals: List<Kural>, val adhigaramClickListener: AdhigaramClickListener, val kuralClickListener: KuralClickListener) : RecyclerView.Adapter<SearchResultsAdapter.SearchResultViewHolder>() {


    class SearchResultViewHolder(val rowSearchResultBinding: RowSearchResultBinding) : RecyclerView.ViewHolder(rowSearchResultBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val binding = RowSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchResultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        var headerStr = ""
        if(position == 0) {
            headerStr = holder.itemView.context.getString(R.string.menu_adhigarangal)
            if(adhigarams.isEmpty()) {
                headerStr = holder.itemView.context.getString(R.string.kural_result_header)
            }
        } else if(position - adhigarams.size == 0) {
            headerStr = holder.itemView.context.getString(R.string.kural_result_header)
        }
        if(headerStr.isNotEmpty()) {
            holder.rowSearchResultBinding.header.text = headerStr;
        }
        holder.rowSearchResultBinding.header.visibility = if (headerStr.isEmpty()) View.GONE else View.VISIBLE

        if (position < adhigarams.size) {
            holder.rowSearchResultBinding.searchTextView.text = adhigarams[position].name
            holder.itemView.setOnClickListener {
                adhigaramClickListener.onAdhigaramClicked(adhigarams[position])
            }
        } else {
            val kuralPosition = position - adhigarams.size;
            holder.rowSearchResultBinding.searchTextView.text = kurals[kuralPosition].kural
            holder.itemView.setOnClickListener {
                kuralClickListener.onKuralClicked(kurals[kuralPosition])
            }
        }

    }

    override fun getItemCount(): Int {
        return adhigarams.size + kurals.size
    }


}