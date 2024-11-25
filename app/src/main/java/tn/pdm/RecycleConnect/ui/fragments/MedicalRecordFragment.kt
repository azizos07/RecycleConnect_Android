@file:Suppress("DEPRECATION")

package tn.pdm.RecycleConnect.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import tn.pdm.RecycleConnect.data.repositories.MedicalRecordRepository
import tn.pdm.RecycleConnect.data.viewmodels.MedicalRecordViewModel
import tn.pdm.RecycleConnect.data.viewmodels.MedicalRecordViewModelFactory
import tn.pdm.RecycleConnect.databinding.FragmentMedicalRecordBinding
import tn.pdm.RecycleConnect.ui.adapters.MedicalRecordAdapter

const val TAG_Medical = "MedicalRecordFragment"

class MedicalRecordFragment : Fragment() {

    private lateinit var binding: FragmentMedicalRecordBinding
    private lateinit var viewModel: MedicalRecordViewModel
    private val repository = MedicalRecordRepository()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMedicalRecordBinding.inflate(layoutInflater)

        // Instantiate the MedicalRecordViewModel
        viewModel = ViewModelProvider(this, MedicalRecordViewModelFactory(repository))
            .get(MedicalRecordViewModel::class.java)

        val adapter = MedicalRecordAdapter(emptyList()) { clickedMedicalRecord ->
            clickedMedicalRecord._id?.let { medicalRecordId ->
                Log.d(TAG_Medical, "Item clicked. Medical Record ID: $medicalRecordId")

                // Handle the item click, navigate to the details activity with the clicked medical record data
                // Example: val intent = Intent(requireContext(), DetailsActivity::class.java)
                // intent.putExtra("medicalRecordId", medicalRecordId)
                // startActivity(intent)
            }
        }

        binding.rvMedicalRecords.adapter = adapter

        // Observe the LiveData from the ViewModel
        viewModel.medicalRecords.observe(viewLifecycleOwner, Observer { medicalRecords ->
            Log.d(TAG_Medical, "onChanged: Medical Records size - ${medicalRecords.size}")
            adapter.medicalRecordsList = medicalRecords
            adapter.notifyDataSetChanged()
        })

        viewModel.getAllMedicalRecords()
        setHasOptionsMenu(true)
        binding.rvMedicalRecords.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        return binding.root
    }

}
