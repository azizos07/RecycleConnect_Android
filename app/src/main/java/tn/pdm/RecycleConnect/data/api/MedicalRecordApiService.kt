// MedicalRecordApiService.kt
package tn.pdm.RecycleConnect.data.api

import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Multipart
import retrofit2.http.Part


import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Path
import tn.pdm.RecycleConnect.data.models.MedicalRecord

interface MedicalRecordApiService {

    @GET("medicalRecord/medicalRecordAll")
    suspend fun getAllMedicalRecords(): Response<List<MedicalRecord>>

    @POST("medicalRecord/")
    suspend fun createMedicalRecord(@Body medicalRecord: MedicalRecord): Response<MedicalRecord>

    @GET("medicalRecord/byid/{id}")
    suspend fun getMedicalRecordById(@Path("id") medicalRecordId: String): Response<MedicalRecord>

    // Add more endpoints as needed

}

