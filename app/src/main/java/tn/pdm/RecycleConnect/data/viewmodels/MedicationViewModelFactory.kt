import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tn.pdm.RecycleConnect.data.repositories.MedicationRepository
import tn.pdm.RecycleConnect.data.viewmodels.MedicationViewModel

class MedicationViewModelFactory(
    private val repository: MedicationRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MedicationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MedicationViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}