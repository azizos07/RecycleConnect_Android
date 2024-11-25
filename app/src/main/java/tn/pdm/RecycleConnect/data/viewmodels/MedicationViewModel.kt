// MedicationViewModel.kt
package tn.pdm.RecycleConnect.data.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tn.pdm.RecycleConnect.data.models.Medication
import tn.pdm.RecycleConnect.data.repositories.MedicationRepository

class MedicationViewModel(private val repository: MedicationRepository) : ViewModel() {

    // LiveData for medications
    private val _medications = MutableLiveData<List<Medication>>()
    val medications: LiveData<List<Medication>> get() = _medications



    // Function to fetch medications
    fun getAllMedications() {
        viewModelScope.launch {
            try {
                val medicationsList = repository.getAllMedications()
                _medications.postValue(medicationsList)
            } catch (e: Exception) {
                Log.e("MedicationViewModel", "getAllMedications: ${e.message}")
                // Handle the exception
            }
        }
    }

    // Add more functions as needed for other CRUD operations if they are defined in the repository
}