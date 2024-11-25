package tn.pdm.RecycleConnect.data.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import tn.pdm.RecycleConnect.data.api.RetrofitInstance
import tn.pdm.RecycleConnect.data.models.MedicalRecord
import tn.pdm.RecycleConnect.data.repositories.MedicalRecordRepository
import java.io.File

class MedicalRecordViewModel(private val repository: MedicalRecordRepository) : ViewModel() {

    // LiveData for medical records
    private val _medicalRecords = MutableLiveData<List<MedicalRecord>>()
    val medicalRecords: LiveData<List<MedicalRecord>> get() = _medicalRecords

    // LiveData for a single medical record
    private val _medicalRecord = MutableLiveData<MedicalRecord>()
    val medicalRecord: LiveData<MedicalRecord> get() = _medicalRecord

    // Function to fetch medical records
    fun getAllMedicalRecords() {
        viewModelScope.launch {
            try {
                val medicalRecordsList = repository.getAllMedicalRecords()
                _medicalRecords.postValue(medicalRecordsList)
            } catch (e: Exception) {
                Log.e("MedicalRecordViewModel", "getAllMedicalRecords: ${e.message}")
                // Handle the exception
            }
        }
    }

    // Function to create a medical record
    fun createMedicalRecord(medicalRecord: MedicalRecord) {
        viewModelScope.launch {
            try {
                repository.createMedicalRecord(medicalRecord)
            } catch (e: Exception) {
                Log.e("MedicalRecordViewModel", "createMedicalRecord: ${e.message}")
                // Handle the exception
            }
        }
    }

    // Function to fetch medical record by id
    fun getMedicalRecordById(medicalRecordId: String) {
        viewModelScope.launch {
            try {
                val medicalRecord = repository.getMedicalRecordById(medicalRecordId)
                _medicalRecord.postValue(medicalRecord)
            } catch (e: Exception) {
                Log.e("MedicalRecordViewModel", "getMedicalRecordById: ${e.message}")
                // Handle the exception
            }
        }
    }

    // Add more functions as needed

}
