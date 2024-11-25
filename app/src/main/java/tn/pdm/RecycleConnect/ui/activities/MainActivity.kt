package tn.pdm.RecycleConnect.ui.activities

import MedicationFragment
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import tn.pdm.RecycleConnect.R
import tn.pdm.RecycleConnect.databinding.ActivityMainBinding
import tn.pdm.RecycleConnect.ui.fragments.MedicalRecordFragment

class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding

    //scheduleNewsScraping
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = binding.toolbarid.bottombar
        binding.MyMedicalRecord.setOnClickListener {
            navigateTo(AddMedicalRecordActivity::class.java)
        }




        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    // Handle Home item click navigate to MainActivity
                    navigateTo(MainActivity::class.java)
                    true
                }

                R.id.navigation_medication -> {
                    changeFragment(MedicationFragment(), "")
                    true
                }
                R.id.navigation_profile -> {
                    // navigate to AddEventActivity
                    navigateTo(AddMedicalRecordActivity::class.java)
                    true
                }
                R.id.navigation_medical_record-> {
                    // navigate to fragment NewsFragment
                    changeFragment(MedicalRecordFragment(), "")
                    true
                }
                else -> false
            }
        }

    }
    //change fragment
    private fun changeFragment(fragment: Fragment, name: String) {

        if (name.isEmpty())
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        else
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(name)
                .commit()

    }
    private fun navigateTo(destinationActivity: Class<*>) {
        val intent = Intent(this@MainActivity, destinationActivity)
        startActivity(intent)
    }


}