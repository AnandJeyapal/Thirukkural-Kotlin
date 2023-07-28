package com.work.thirukkural.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.work.thirukkural.data.entities.Adhigaram
import com.work.thirukkural.databinding.RowAdhigaramBinding

class AdhigaramAdapter(var adhigarams: List<Adhigaram>, val adhigaramClickListener: AdhigaramClickListener) : RecyclerView.Adapter<AdhigaramAdapter.AdhigaramViewHolder>() {


    class AdhigaramViewHolder(val rowAdhigaramBinding: RowAdhigaramBinding) : RecyclerView.ViewHolder(rowAdhigaramBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdhigaramViewHolder {
        val binding = RowAdhigaramBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdhigaramViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdhigaramViewHolder, position: Int) {
        with(holder) {
            with(adhigarams[position]) {
                rowAdhigaramBinding.adhigaramNumber.text = number.toString()
                rowAdhigaramBinding.adhigaramDescription.text= name
                rowAdhigaramBinding.root.setOnClickListener { adhigaramClickListener.onAdhigaramClicked(this) }
            }
        }
    }

    override fun getItemCount(): Int {
        return adhigarams.size
    }

}