package com.putragandad.challenge2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.materialswitch.MaterialSwitch
import com.putragandad.challenge2.databinding.ActivityMainBinding
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var tipRateId: Int = 0 // tip rate id (radio button)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // get radio button checked based on id
        binding.radioGroupService.setOnCheckedChangeListener { group, checkedId ->
            tipRateId = checkedId
        }

        // listen to press event for calculate tip button
        binding.btnCalculateTip.setOnClickListener {
            val costServiceInput = binding.etFillableInputCost.text.toString() // get cost of service value

            if(costServiceInput.isNotEmpty()) {
                if(binding.radioGroupService.checkedRadioButtonId == -1) {
                    Toast.makeText(this,
                        getString(R.string.tip_percentage_empty_text), Toast.LENGTH_SHORT).show()
                } else {
                    val tip = calculateTip(costServiceInput.toInt(), tipRateId)
                    printFinalTip(costServiceInput.toInt(), tip , binding.resultCost, binding.resultTip, binding.switchRoundTip)
                }
            } else {
                Toast.makeText(this, getString(R.string.empty_input_cost_toast), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun calculateTip(cost: Int, tipRateId: Int): Double {
        val tipPercentage = when(tipRateId) {
            1 -> 0.2
            2 -> 0.18
            3 -> 0.15
            else -> 0.0
        }

        return cost * tipPercentage
    }

    private fun printFinalTip(costService: Int, tip: Double, tvCost: TextView, tvTip: TextView, switchRoundTip: MaterialSwitch) {
        tvCost.text = "$${costService}"
        tvTip.text = if(switchRoundTip.isChecked) "$${tip.roundToInt().toDouble()}" else "$${tip}"

        Toast.makeText(this,
            getString(R.string.tip_calculated_success_toast_text), Toast.LENGTH_SHORT).show()
    }

}