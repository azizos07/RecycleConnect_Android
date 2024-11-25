package tn.pdm.RecycleConnect.data.repositories

import android.util.Log
import retrofit2.Response
import retrofit2.http.GET
import tn.pdm.RecycleConnect.data.api.RetrofitInstance
import tn.pdm.RecycleConnect.data.models.Medication

class MedicationRepository {

    suspend fun getAllMedications(): List<Medication> {
        return try {
            val response = RetrofitInstance.apiMedication.getAllMedications()
            if (response.isSuccessful) {
                // If the network call is successful, return the body
                response.body() ?: emptyList()
            } else {
                // If the network call failed, handle the error response
                Log.e("MedicationRepository", "Response not successful")
                emptyList()
            }
        } catch (e: Exception) {
            // Handle exceptions like network issues here
            Log.e("MedicationRepository", "Error fetching medications", e)
            emptyList()
        }
    }

    // The Retrofit interface
    interface ApiMedication {
        @GET("medication/medications")
        suspend fun getAllMedications(): Response<List<Medication>>
    }
}