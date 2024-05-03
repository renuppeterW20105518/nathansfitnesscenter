package ie.setu.nathansfitnesscenter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import ie.setu.nathansfitnesscenter.databinding.ActivityDeleteBinding

class DeleteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.deleteButton.setOnClickListener {
            val contactNumber = binding.deleteContactNumber.text.toString()
            if (contactNumber.isNotEmpty()) {
                deleteDate(contactNumber)
            } else {
                Toast.makeText(this, "Please enter the contact number", Toast.LENGTH_SHORT).show()
            }
        }

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun deleteDate(contactNumber: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Trainee Information")
        databaseReference.child(contactNumber).removeValue().addOnSuccessListener {
            binding.deleteContactNumber.text.clear()
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Unable to delete", Toast.LENGTH_SHORT).show()
        }
    }
}