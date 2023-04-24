package com.work.thirukkural.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.work.thirukkural.R
import com.work.thirukkural.data.entities.Adhigaram

class FavoriteAdhigaramAdapter(var adhigarams: List<Adhigaram>, val adhigaramClickListener: AdhigaramClickListener)
    : RecyclerView.Adapter<FavoriteAdhigaramAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val largeTextView: TextView
        val nameTextView: TextView
        init {
            largeTextView = view.findViewById(R.id.large_description)
            nameTextView = view.findViewById(R.id.description)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.favorite_adhigaram_item, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener{
            adhigaramClickListener.onAdhigaramClicked(adhigarams[position])
        }
        val color = com.work.thirukkural.utils.getColorToUpdate(adhigarams[position].number ?: 1, holder.itemView.context.resources)
        holder.itemView.setBackgroundColor(color)
        val name = adhigarams[position].name ?: "AAA"
        holder.largeTextView.text = name[0].toString()
        holder.nameTextView.text = name
    }

    override fun getItemCount(): Int {
        return adhigarams.size
    }

}