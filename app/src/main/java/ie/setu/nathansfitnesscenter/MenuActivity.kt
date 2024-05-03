package ie.setu.nathansfitnesscenter

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import ie.setu.nathansfitnesscenter.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainUpload.setOnClickListener{
            val intent = Intent(this@MenuActivity,UploadActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.mainInfo.setOnClickListener{
            val intentInfo = Intent(this@MenuActivity,ViewActivity::class.java)
            startActivity(intentInfo)
            finish()
        }

        binding.mainUpdate.setOnClickListener{
            val intentUpdate = Intent(this@MenuActivity,UpdateActivity::class.java)
            startActivity(intentUpdate)
            finish()
        }

        binding.mainDelete.setOnClickListener{
            val intentDelete = Intent(this@MenuActivity,DeleteActivity::class.java)
            startActivity(intentDelete)
            finish()
        }
    }
}