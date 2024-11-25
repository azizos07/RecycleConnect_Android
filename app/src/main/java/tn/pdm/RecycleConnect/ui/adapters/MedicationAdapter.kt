package tn.pdm.RecycleConnect.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tn.pdm.RecycleConnect.data.models.Medication
import tn.pdm.RecycleConnect.databinding.SingleItemMedicationBinding

class MedicationAdapter(
    var medicationsList: List<Medication>,
    private val onItemClick: (Medication) -> Unit
) : RecyclerView.Adapter<MedicationAdapter.MedicationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SingleItemMedicationBinding.inflate(inflater, parent, false)
        return MedicationViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: MedicationViewHolder, position: Int) {
        val medication = medicationsList[position]
        holder.bind(medication)
    }

    override fun getItemCount(): Int {
        return medicationsList.size
    }

    class MedicationViewHolder(
        private val binding: SingleItemMedicationBinding,
        private val onItemClick: (Medication) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(medication: Medication) {
            // Populate the views in the layout with data from the Medication object
            binding.textMedicationName.text = medication.name

            // Set click listener on the item view and pass the medication to the click listener
            binding.root.setOnClickListener {
                onItemClick(medication)
            }
        }
    }
}