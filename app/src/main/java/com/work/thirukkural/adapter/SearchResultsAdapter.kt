package com.work.thirukkural.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.work.thirukkural.R
import com.work.thirukkural.data.entities.Adhigaram
import com.work.thirukkural.data.entities.Kural

class SearchResultsAdapter(var adhigarams: List<Adhigaram>, var kurals: List<Kural>, val adhigaramClickListener: AdhigaramClickListener, val kuralClickListener: KuralClickListener) : RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>() {



    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val headerTextView: TextView
        val searchResultTextView: TextView
        init {
            headerTextView = view.findViewById(R.id.header)
            searchResultTextView = view.findViewById(R.id.search_text_view)
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_search_result, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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
            holder.headerTextView.text = headerStr;
        }
        holder.headerTextView.visibility = if (headerStr.isEmpty()) View.GONE else View.VISIBLE

        if (position < adhigarams.size) {
            holder.searchResultTextView.text = adhigarams[position].name
            holder.itemView.setOnClickListener {
                adhigaramClickListener.onAdhigaramClicked(adhigarams[position])
            }
        } else {
            val kuralPosition = position - adhigarams.size;
            holder.searchResultTextView.text = kurals[kuralPosition].kural
            holder.itemView.setOnClickListener {
                kuralClickListener.onKuralClicked(kurals[kuralPosition])
            }
        }

    }

    override fun getItemCount(): Int {
        return adhigarams.size + kurals.size
    }


}