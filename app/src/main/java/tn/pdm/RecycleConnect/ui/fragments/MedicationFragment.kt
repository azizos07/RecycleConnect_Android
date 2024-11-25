@file:Suppress("DEPRECATION")

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import tn.pdm.RecycleConnect.R
import tn.pdm.RecycleConnect.databinding.FragmentMedicationBinding
import tn.pdm.RecycleConnect.ui.adapters.MedicationAdapter
import tn.pdm.RecycleConnect.data.models.Medication
import tn.pdm.RecycleConnect.data.repositories.MedicationRepository
import tn.pdm.RecycleConnect.data.viewmodels.MedicationViewModel

const val TAG_Medication = "MedicationFragment"

class MedicationFragment : Fragment() {

    private var _binding: FragmentMedicationBinding? = null
    private val binding get() = _binding!!

    private lateinit var medicationAdapter: MedicationAdapter
    private var medicationsList: List<Medication> = listOf()
    private lateinit var viewModel: MedicationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = MedicationViewModelFactory(MedicationRepository())
        viewModel = ViewModelProvider(this, factory).get(MedicationViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMedicationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRecyclerView()
        setupObservers()
        setupSearch()
    }

    private fun initializeRecyclerView() {
        medicationAdapter = MedicationAdapter(medicationsList) { medication ->
            // Handle click on medication. e.g., navigate to a detail screen for the medication
        }

        binding.rvMedications.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = medicationAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun setupObservers() {
        // Observe the LiveData of medications from the ViewModel
        viewModel.medications.observe(viewLifecycleOwner) { medications ->
            updateMedicationList(medications)
        }

        // Assuming getAllMedications triggers the Medication fetch in the ViewModel
        viewModel.getAllMedications()
    }

    private fun setupSearch() {
        binding.medicationSearchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filterMedications(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Nothing to do here
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Nothing to do here
            }
        })
    }

    private fun updateMedicationList(medications: List<Medication>) {
        this.medicationsList = medications
        medicationAdapter.medicationsList = medications
        medicationAdapter.notifyDataSetChanged()
    }

    private fun filterMedications(query: String) {
        val filteredMedicationList = medicationsList.filter {
            it.name.contains(query, ignoreCase = true)
        }
        medicationAdapter.medicationsList = filteredMedicationList
        medicationAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}