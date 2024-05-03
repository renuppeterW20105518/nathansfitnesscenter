package ie.setu.nathansfitnesscenter

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import ie.setu.nathansfitnesscenter.databinding.ActivityMenuBinding
import ie.setu.nathansfitnesscenter.databinding.ActivityUploadBinding

class UploadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButtom.setOnClickListener {
            val traineeName = binding.uploadTraineeName.text.toString()
            val contactNumber = binding.contactNumber.text.toString()
            val eirCode = binding.eirCode.text.toString()
            val gardianName = binding.gardianName.text.toString()

            databaseReference = FirebaseDatabase.getInstance().getReference("Trainee Information")
            val traineeData = TraineeData(traineeName, contactNumber, eirCode, gardianName)
            databaseReference.child(contactNumber).setValue(traineeData).addOnSuccessListener {
                binding.uploadTraineeName.text.clear()
                binding.contactNumber.text.clear()
                binding.eirCode.text.clear()
                binding.gardianName.text.clear()

                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@UploadActivity, MenuActivity::class.java)
                startActivity(intent)
            }.addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}