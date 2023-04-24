package com.work.thirukkural.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.work.thirukkural.R
import com.work.thirukkural.data.entities.Adhigaram

class AdhigaramAdapter(var adhigarams: List<Adhigaram>, val adhigaramClickListener: AdhigaramClickListener) : RecyclerView.Adapter<AdhigaramAdapter.ViewHolder>() {



    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val numberTextView: TextView
        val nameTextView: TextView
        init {
            numberTextView = view.findViewById(R.id.adhigaram_number)
            nameTextView = view.findViewById(R.id.adhigaram_description)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_adhigaram, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener{
            adhigaramClickListener.onAdhigaramClicked(adhigarams[position])
        }
        holder.numberTextView.text = adhigarams[position].number.toString()
        holder.nameTextView.text = adhigarams[position].name
    }

    override fun getItemCount(): Int {
        return adhigarams.size
    }

}