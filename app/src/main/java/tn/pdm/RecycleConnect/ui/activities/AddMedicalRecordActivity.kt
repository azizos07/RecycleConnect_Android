package tn.pdm.RecycleConnect.ui.activities

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import tn.pdm.RecycleConnect.R
import tn.pdm.RecycleConnect.data.models.MedicalRecord
import tn.pdm.RecycleConnect.data.models.MedicalRecord.*
import tn.pdm.RecycleConnect.data.repositories.MedicalRecordRepository
import tn.pdm.RecycleConnect.data.viewmodels.MedicalRecordViewModel
import tn.pdm.RecycleConnect.data.viewmodels.MedicalRecordViewModelFactory
import tn.pdm.RecycleConnect.databinding.ActivityAddMedicalrecordBinding
import java.text.SimpleDateFormat
import java.util.*

class AddMedicalRecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddMedicalrecordBinding
    private lateinit var viewModel: MedicalRecordViewModel
    lateinit var tvImmunizationDate: TextView
    lateinit var tvAllergyPin: TextView
    lateinit var btnShowImmunizationDatePicker: Button
    private val calendar = Calendar.getInstance()

    private val repository = MedicalRecordRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMedicalrecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize UI elements
        tvImmunizationDate = findViewById(R.id.editTextImmunizationDate)
        tvAllergyPin = findViewById(R.id.textViewAllergyPin)
        btnShowImmunizationDatePicker = findViewById(R.id.btnShowImmunizationDatePicker)

        // Instantiate the MedicalRecordViewModel
        viewModel = ViewModelProvider(this, MedicalRecordViewModelFactory(repository))
            .get(MedicalRecordViewModel::class.java)

        // DatePicker for Immunization
        btnShowImmunizationDatePicker.setOnClickListener {
            showDatePicker(tvImmunizationDate)
        }

        // Binding buttonCreateMedicalRecord to coroutine
        binding.buttonCreateMedicalRecord.setOnClickListener {
            // Create a MedicalRecord object
            val immunizationName = binding.editTextImmunizationName.text.toString()
            val immunizationDate = tvImmunizationDate.text.toString()
            val allergySubstance = binding.editTextAllergySubstance.text.toString()
            val allergyReaction = binding.editTextAllergyReaction.text.toString()
            val pinned = tvAllergyPin.text == "Pinned" // Check if allergy is pinned

            val medicalNotes = binding.editTextMedicalNotes.text.toString()

            val medicalRecord = MedicalRecord(
                patient = "654be4835b03eb51de9bb9d4", // Replace it with the patient's ID or name
                doctor = "654be5b15b03eb51de9bb9db", // Replace it with the doctor's ID or name
                diagnoses = emptyList(), // Add diagnoses if needed
                immunizations = listOf(Immunization(immunizationName, parseDate(immunizationDate))),
                allergies = listOf(Allergy(allergySubstance, allergyReaction, pinned)),
                medicalNotes = medicalNotes
            )

            // Launch a coroutine to call the suspend function
            viewModel.createMedicalRecord(medicalRecord)

            // The navigation logic should really be reacting to a successful operation signal from the ViewModel,
            // like a LiveData<Event> or a shared Flow. But temporarily left here.
            val intent = Intent(this@AddMedicalRecordActivity, MainActivity::class.java)
            startActivity(intent)
            finish() // Finish current activity to prevent going back to it
        }
    }

    private fun showDatePicker(textView: TextView) {
        val datePicker = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val selectedCalendar = Calendar.getInstance()
                selectedCalendar.set(year, month, dayOfMonth)

                // Check if the selected date is in the past
                if (selectedCalendar.timeInMillis < System.currentTimeMillis()) {
                    // Show a message or handle it as you prefer
                    // For now, just log a message
                    Log.d("AddMedicalRecordActivity", "Selected date is in the past.")
                } else {
                    textView.text = formatDate(selectedCalendar.time)
                }
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }

    private fun parseDate(dateString: String): Date {
        return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(dateString) ?: Date()
    }

    private fun formatDate(date: Date): String {
        return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date)
    }
}
