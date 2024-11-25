package tn.pdm.RecycleConnect.data.models


import java.util.Date

data class MedicalRecord(
    val _id: String? = null,
    val patient: String,
    val doctor: String,
    val diagnoses: List<String>,
    val immunizations: List<Immunization>,
    val allergies: List<Allergy>,
    val medicalNotes: String
) {
    data class Immunization(
        val name: String,
        val date: Date
    )

    data class Allergy(
        val substance: String,
        val reaction: String,
        val pinned: Boolean?
    )
    constructor() : this("", "","", emptyList(), emptyList(), emptyList(), "")

}
