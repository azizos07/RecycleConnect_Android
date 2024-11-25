package tn.pdm.RecycleConnect.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tn.pdm.RecycleConnect.data.models.MedicalRecord
import tn.pdm.RecycleConnect.databinding.SingleItemMedicalRecordBinding

class MedicalRecordAdapter(
    var medicalRecordsList: List<MedicalRecord>,
    private val onItemClick: (MedicalRecord) -> Unit
) : RecyclerView.Adapter<MedicalRecordAdapter.MedicalRecordViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicalRecordViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SingleItemMedicalRecordBinding.inflate(inflater, parent, false)
        return MedicalRecordViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: MedicalRecordViewHolder, position: Int) {
        val medicalRecord = medicalRecordsList[position]
        holder.bind(medicalRecord)
        Log.d("MedicalRecordAdapter", "onBindViewHolder: MedicalRecord at position $position bound.")
    }

    override fun getItemCount(): Int {
        return medicalRecordsList.size
    }

    class MedicalRecordViewHolder(
        private val binding: SingleItemMedicalRecordBinding,
        private val onItemClick: (MedicalRecord) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(medicalRecord: MedicalRecord) {
            Log.d("MedicalRecordAdapter", "Binding medical record: $medicalRecord")

            // Populate the views in the layout with data from the MedicalRecord object
           
            binding.textDiagnoses.text = medicalRecord.diagnoses.joinToString(separator = "\n") { it }
            binding.textImmunizations.text = medicalRecord.immunizations.joinToString(separator = "\n") {
                "${it.name} on ${it.date}"
            }
            binding.textAllergies.text = medicalRecord.allergies.joinToString(separator = "\n") {
                "${it.substance} - ${it.reaction}"
            }
            binding.textMedicalNotes.text = medicalRecord.medicalNotes

            // Set click listener on the item view and pass the medical record to the click listener
            binding.root.setOnClickListener {
                Log.d("MedicalRecordAdapter", "Item clicked. Medical Record ID: ${medicalRecord._id}")
                onItemClick(medicalRecord)
            }
        }
    }
}