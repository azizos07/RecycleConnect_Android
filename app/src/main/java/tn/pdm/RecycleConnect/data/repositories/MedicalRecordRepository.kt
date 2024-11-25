// MedicalRecordRepository.kt
package tn.pdm.RecycleConnect.data.repositories

import android.util.Log
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import tn.pdm.RecycleConnect.data.api.RetrofitInstance
import tn.pdm.RecycleConnect.data.models.MedicalRecord
import java.io.File

class MedicalRecordRepository() {

    suspend fun getAllMedicalRecords(): List<MedicalRecord> {
        val response = RetrofitInstance.apimedicalrecord.getAllMedicalRecords()
        if (response.isSuccessful) {
            // If the network call is successful, return the body
            return response.body() ?: listOf()
        } else {
            // If the network call failed, throw an exception or handle the error
            throw Exception(response.errorBody()?.string() ?: "Unknown error")
        }
    }

    suspend fun createMedicalRecord(medicalRecord: MedicalRecord) {
        try {
            val response = RetrofitInstance.apimedicalrecord.createMedicalRecord(medicalRecord)
            if (response.isSuccessful && response.body() != null) {
                // Handle the successful response here
            } else {
                // Handle unsuccessful response or error here
                Log.e("MedicalRecordRepository", "Server responded with: ${response.code()}")
            }
        } catch(e: Exception) {
            // Handle exceptions like network issues here
            Log.e("MedicalRecordRepository", "Error creating medical record", e)
        }
    }

    // Function to fetch medical record by id
    suspend fun getMedicalRecordById(medicalRecordId: String): MedicalRecord {
        val response = RetrofitInstance.apimedicalrecord.getMedicalRecordById(medicalRecordId)
        if (response.isSuccessful) {
            // Assuming response.body() contains the MedicalRecord object
            response.body()?.let {
                return it
            } ?: throw RuntimeException("Response body is null") // Or handle this case as desired
        } else {
            // throw an exception or alternatively, return a default instance or a null.
            // Depending on your use case, you may want to handle errors differently.
            throw RuntimeException("Error retrieving the record: ${response.errorBody()?.string()}")
        }
    }

    // Add more repository methods as needed
}
