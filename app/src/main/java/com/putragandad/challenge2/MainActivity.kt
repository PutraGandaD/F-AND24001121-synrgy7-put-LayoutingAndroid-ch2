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
    private var costService: Int = 0 // cost service (converted to Int)
    private var tipRateId: Int = 0 // tip rate id (radio button)
    private var roundTipState: Boolean = false // round tip state (switch)

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

            checkRoundTipState(binding.switchRoundTip)

            if(costServiceInput.isNotEmpty()) {
                convertToInt(costServiceInput)
                printFinalTip(costService, calculateTip(costService, tipRateId, roundTipState), binding.resultCost, binding.resultTip)
            } else {
                Toast.makeText(this, getString(R.string.empty_input_cost_toast), Toast.LENGTH_SHORT).show()
            }
        }
    }

    enum class TipValue(val id: Int, val value: Double) {
        AMAZING(1, 0.2),
        GOOD(2, 0.18),
        OK(3, 0.15)
    }

    private fun convertToInt(cost: String) {
        costService = cost.toInt()
    }

    private fun checkRoundTipState(switch: MaterialSwitch) {
        roundTipState = switch.isChecked
    }

    private fun calculateTip(cost: Int, tipRateId: Int , round: Boolean): Double {
        var calculatedTip = 0.0

        when(tipRateId) {
            TipValue.AMAZING.id -> {
                calculatedTip = cost * TipValue.AMAZING.value
            }
            TipValue.GOOD.id -> {
                calculatedTip = cost * TipValue.GOOD.value
            }
            TipValue.OK.id -> {
                calculatedTip = cost * TipValue.OK.value
            }
        }

        return if(round) calculatedTip.roundToInt().toDouble() else calculatedTip
    }

    private fun printFinalTip(costService: Int, tip: Double, tvCost: TextView, tvTip: TextView) {
        tvCost.setText("$${costService}")
        tvTip.setText("$${tip}")

        Toast.makeText(this,
            getString(R.string.tip_calculated_success_toast_text), Toast.LENGTH_SHORT).show()
    }

}