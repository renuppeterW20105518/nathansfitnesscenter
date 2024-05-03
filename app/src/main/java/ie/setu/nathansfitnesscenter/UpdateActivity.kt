package ie.setu.nathansfitnesscenter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import ie.setu.nathansfitnesscenter.databinding.ActivityUpdateBinding
import ie.setu.nathansfitnesscenter.databinding.ActivityUploadBinding

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateButtom.setOnClickListener {
            val referContactNumber = binding.referContactNumber.text.toString()
            val updateTraineeName = binding.updateTraineeName.text.toString()
            val updateEirCode = binding.updateEirCode.text.toString()
            val updateGardianName = binding.updateGardianName.text.toString()

            updateData(referContactNumber, updateTraineeName, updateEirCode, updateGardianName)
        }

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun updateData(contactNumber: String, traineeName: String, eirCode: String, gardianName: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Trainee Information")
        val traineeData = mapOf<String,String>("traineeName" to traineeName,"eirCode" to eirCode,"gardianName" to gardianName)
        databaseReference.child(contactNumber).updateChildren(traineeData).addOnSuccessListener {
            binding.referContactNumber.text.clear()
            binding.updateTraineeName.text.clear()
            binding.updateEirCode.text.clear()
            binding.updateGardianName.text.clear()

            Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener{
            Toast.makeText(this, "Failed to Update", Toast.LENGTH_SHORT).show()
        }
    }
}