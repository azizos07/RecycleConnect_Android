package tn.pdm.RecycleConnect.data.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tn.pdm.RecycleConnect.data.repositories.MedicalRecordRepository

class MedicalRecordViewModelFactory(private val repository: MedicalRecordRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MedicalRecordViewModel::class.java)) {
            return MedicalRecordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
