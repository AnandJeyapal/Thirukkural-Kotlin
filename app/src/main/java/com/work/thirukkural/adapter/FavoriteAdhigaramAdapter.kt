package com.work.thirukkural.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.work.thirukkural.data.entities.Adhigaram
import com.work.thirukkural.databinding.FavoriteAdhigaramItemBinding

class FavoriteAdhigaramAdapter(var adhigarams: List<Adhigaram>, val adhigaramClickListener: AdhigaramClickListener)
    : RecyclerView.Adapter<FavoriteAdhigaramAdapter.FavoriteAdhigaramViewHolder>() {

    class FavoriteAdhigaramViewHolder(val binding: FavoriteAdhigaramItemBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdhigaramViewHolder {
        val binding = FavoriteAdhigaramItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteAdhigaramViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteAdhigaramViewHolder, position: Int) {
        with(holder) {
            with(adhigarams[position]) {
                val name = this.name ?: "AAA"
                binding.largeDescription.text = name[0].toString()
                binding.description.text = name
                val color = com.work.thirukkural.utils.getColorToUpdate(number ?: 1, binding.root.context.resources)
                binding.root.setBackgroundColor(color)
                binding.root.setOnClickListener {
                    adhigaramClickListener.onAdhigaramClicked(this)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return adhigarams.size
    }

}