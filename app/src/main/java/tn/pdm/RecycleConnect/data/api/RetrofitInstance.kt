package tn.pdm.RecycleConnect.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tn.pdm.RecycleConnect.data.models.MedicalRecord
import tn.pdm.RecycleConnect.data.repositories.MedicationRepository

object RetrofitInstance {

    private const val BASE_URL = " http://192.168.5.231:9091/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }


    val apimedicalrecord: MedicalRecordApiService by lazy {
        retrofit.create(MedicalRecordApiService::class.java)
    }
    val apiMedication: MedicationRepository.ApiMedication by lazy {
        retrofit.create(MedicationRepository.ApiMedication::class.java)
    }
}
