package ie.setu.nathansfitnesscenter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import ie.setu.nathansfitnesscenter.databinding.ActivityViewBinding

class ViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewBinding
    private lateinit var databaseReference : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener{
            val searchContactNumber: String = binding.searchContactNumber.text.toString()
            if (searchContactNumber.isNotEmpty()){
                readData(searchContactNumber)
            }else{
                Toast.makeText(this,"PLease enter the contact number",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(contactNumber : String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Trainee Information")
        databaseReference.child(contactNumber).get().addOnSuccessListener {
            if (it.exists()){
                val traineeName = it.child("traineeName").value
                val eirCode = it.child("eirCode").value
                val gardianName = it.child("gardianName").value

                Toast.makeText(this,"Results Found",Toast.LENGTH_SHORT).show()
                binding.searchContactNumber.text.clear()

                binding.readTraineeName.text = traineeName.toString()
                binding.readEirCode.text = eirCode.toString()
                binding.readGardianName.text = gardianName.toString()
            } else {
                Toast.makeText(this,"Contact number does not exist",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show()
        }
    }
}