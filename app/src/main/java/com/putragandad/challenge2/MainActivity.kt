package com.putragandad.challenge2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.putragandad.challenge2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Convert value from input to Int
        var costServiceFinalValue: Int? = null

        binding.btnCalculateTip.setOnClickListener {
            val costServiceInput = binding.etFillableInputCost.text.toString()
            if(costServiceInput.isNotEmpty()) {
                costServiceFinalValue = costServiceInput.toInt()
            } else {
                Toast.makeText(this, getString(R.string.empty_input_cost_toast), Toast.LENGTH_SHORT).show()
            }
            Log.d("RB1", "$costServiceFinalValue")
        }
    }


}